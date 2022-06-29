package net.codejava.controller;

import java.util.List;

import net.codejava.jwt.TokenManager;
import net.codejava.model.User;
import net.codejava.payload.JwtResponseModel;
import net.codejava.repository.UserRepository;
import net.codejava.websecurity.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("")
	public String viewHomePage() {
		return "html/kanbanboard";
	}

	@GetMapping("/loginPage")
	public String login() {

		return "html/Log-in";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		user.setPassword(passwordEncoder.encode("1234"));
		user.setEmail("noah@ubs.com");
		user.setFirstName("noah");
		user.setLastName("B");



		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "html/users";
	}
	@PostMapping(value = "/logincheck",
				consumes = MediaType.ALL_VALUE)
	public ResponseEntity<JwtResponseModel> login(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
		/*
			//@RequestBody LoginRequest payload

		System.out.println(email);
		// prevent non-ubs employees
		if (email.split("@")[1].equals("ubs.com"))
			return "Email is not @ubs email.";

		// prevent sql injection
		if (email.contains("'") || email.contains("#"))
			return "Email contains invalid characters";

		if (!userRepo.existsByEmail(email))
			return "Email does not exist";


		User userdata = userRepo.findByEmail(email);
		System.out.println(userdata.getEmail());
		boolean correctPassword = bCryptPasswordEncoder.matches(password, userdata.getPassword());

		if (correctPassword)
			// if user already has session in database, retrieve and assign. Else create new and add db entry....
			return "Success!";
		}
		return "Incorrect credentials";
	}*/
		System.out.println(email);
		System.out.println(password);
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email,
							password)
			);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		final String jwtToken = tokenManager.generateJwtToken(userDetails);
		return ResponseEntity.ok(new JwtResponseModel(jwtToken));
	}



}


