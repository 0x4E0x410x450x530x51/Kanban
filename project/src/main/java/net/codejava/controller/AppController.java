package net.codejava.controller;

import java.util.List;

import net.codejava.model.User;
import net.codejava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

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
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
			//@RequestBody LoginRequest payload

		System.out.println(email);
		// prevent non-ubs employees
		/*if (email.split("@")[1].equals("ubs.com"))
			return "Email is not @ubs email.";

		// prevent sql injection
		if (email.contains("'") || email.contains("#"))
			return "Email contains invalid characters";

		if (!userRepo.existsByEmail(email))
			return "Email does not exist";
*/

		User userdata = userRepo.findByEmail(email);
		System.out.println(userdata.getEmail());
		//boolean correctPassword = bCryptPasswordEncoder.encode(password).matches(userdata.getPassword());
		boolean correctPassword = bCryptPasswordEncoder.matches(password, userdata.getPassword());




		if (correctPassword) {





			// if user already has session in database, retrieve and assign. Else create new and add db entry....
			return "Success!";
		}
		return "Incorrect credentials";
	}
}