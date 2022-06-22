package com.kanbanboard.config;

import com.kanbanboard.dto.AppUserDetails;
import com.kanbanboard.repository.UserKanbanboardRepository;
import com.kanbanboard.repository.UserRepository;
import com.kanbanboard.service.AppUserDetailsService;
import org.apache.commons.dbcp2.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.List;

/**
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⣼⡟⡀⠀⠀⠀⠀⠁⡴⠋⠉⠉⠙⡧⡄⣆⠀⠀⠀⠀⠀⣠⣯⡇⠀⢀⣴⡶⣿⢍⣄⢄⡀⠀⠀⠀⣶⡆⠀⠀⢀⣤⡖⡀⠀⠀⠀⠀
⠀⠈⣿⡿⠿⣿⣷⣆⠀⣟⣏⣀⡀⣀⣠⣇⣷⢻⡆⠀⠀⠀⡼⣿⠋⣀⣦⡋⠯⠉⠀⠁⠃⠷⣽⠀⠀⠀⣿⡇⡠⡿⠉⠁⠀⠀⠀⠀⠀⠀
⠀⠐⣿⠅⠀⠈⠻⣿⡄⣯⡟⠛⠛⠛⠛⠛⠋⠉⣷⠀⠀⡟⡽⠁⢀⢕⡷⣷⣶⣶⣶⣶⡶⠛⠂⠀⠀⠀⣿⡏⠋⠀⠀⠀⠀⠀⠀⠀
⠀⠀⣿⡇⠀⠀⠀⣿⡇⠉⠳⣤⣀⣀⣀⣄⡀⠀⠽⡇⣿⠏⠀⠀⠘⣿⡇⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⡿⡇⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠉⠀⠀⠀⠀⠱⠇⠀⠀⠈⠙⠛⠛⠉⠁⠀⠀⠿⠃⠀⠀⠀⠀⠙⠛⣿⣓⣶⣦⣤⣤⡄⠀⠀⠀⠀⣷⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣤⣤⣄⠀⠀⠀⠀⣀⠤⡤⡄⡀⠀⠂⠀⣶⣆⣀⡀⠀⠀⠀⠉⠉⠛⠓⠛⠃⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣸⠏⡏⠛⢝⣧⠀⠀⣿⠏⠉⠑⠫⣝⡇⠀⠀⡿⡟⠻⠟⣟⣧⡀⠠⣾⣿⠷⢶⣦⣄⠀⠀⢠⡾⠯⣿⣆⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣿⢆⡃⠀⡠⡟⡄⠀⡧⠃⠀⠀⠀⠠⡇⠀⠀⣿⡇⠀⠀⠩⡏⡇⠀⣿⣿⠀⠀⠉⢻⣷⡄⢿⡇⠀⠸⣿⡀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠣⣕⢟⣛⠟⣧⡇⠀⣻⣏⣆⣢⣔⠋⠁⠀⠀⠛⠿⠀⠀⠀⠃⠃⠀⢻⡿⠀⠀⠀⠈⣿⢿⠈⠛⠿⠟⢹⣷⡀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⣀⡀⠀⠉⣁⡮⡿⠁⠀⠈⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠈⠃⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠑⠭⠭⠿⠛⠓⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠢⡄⠀⡆⢀⠀⠀⠀⠀⡄⠀⠀⢀⡠⠄⠄⠄⡀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠛⠛⢿⡆⠀⠀⠀⠯⠋⠀⠀⠩⣿⠀⠀⢀⢾⠇⠁⣀⣥⠶⠓⠻⢯⡲⡌⡱⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡁⠀⠀⠀⡸⣿⠀⠀⠀⣄⠀⠀⠀⠈⣮⠢⠀⣮⠏⠀⢨⣯⣧⣆⣒⣨⣡⣯⠏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠈⠙⠦⣤⡤⠟⢝⡄⠀⠀⠧⠅⠀⠀⠀⣟⡄⡟⠅⠀⠀⠀⡏⣇⠈⠙⠓⠓⠁⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢟⡅⠀⠀⠘⡇⠀⠀⠀⠈⠿⠃⠀⠀⠀⠀⠉⠻⣿⣦⣤⣤⣔⣯⡍⠁⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⡶⣄⢀⠀⠀⢀⡟⡑⠁⠀⠀⠀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠋⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠋⠭⠬⠋⠋⠉⠀⠀⠀⠀⠀⠀⠀⢀⡀⡀⡀⠀⠀⠀⠀⠀⢀⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠲⡦⡀⠀⠀⢀⡷⠋⣴⠃⠁⠁⡇⠻⣄⠀⠀⠀⣸⢸⠹⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⡦⣰⠏⠁⠀⢿⣀⢀⣴⠇⠀⠈⢯⣆⡦⣯⡋⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣽⠋⠀⠀⠀⠑⠛⠋⠁⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⢯⠁⠀⠀⠀⢠⡆⠀⠀⠀⡄⠀⠀⠀⣾⡿⠿⠧⢿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⣿⡏⠀⠀⠀⣿⡆⠀⠀⣿⣷⠁⡰⠿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠯⣿⣆⡀⣖⡟⠀⠀⠀⢹⣿⡿⠇⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠀⠀⠀⠀⠸⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

    //@Autowired
    //private DataSource dataSource;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Set up simplified security settings requiring all requests to be authenticated
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/**").permitAll()
                .antMatchers("/page/board/{id}")
                .access("@appSecurityConfig.checkUserId(authentication,#id)")
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/Pics/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .antMatchers("/php/**").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginProcessingUrl("/api/login") // link to submit username-password
                .loginPage("/html/login.html")
                //.usernameParameter("email")
                //.passwordParameter("password")
                .defaultSuccessUrl("/html/index.html")
                .failureUrl("/403")
                .permitAll()
                .and()
                .rememberMe()
//                .key("rem-me-key")
//                .rememberMeCookieName("remember-me-cookie")
//                .rememberMeParameter("remember-me")  // remember-me field name in form.
//                .tokenRepository(this.persistentTokenRepository())
//                .tokenValiditySeconds(1*24*60*60)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;


        ;
    }

    //@Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(getDataSource());
        return tokenRepository;
    }

    @Autowired
    UserKanbanboardRepository userKanbanboardRepository;

    @Autowired
    UserRepository userRepository;

    public boolean checkUserId(Authentication authentication, int id) {

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println(currentUserName);

        }
        //List allKanbanboards = userKanbanboardRepository.findByKanbanboardID_Id(id);



        return id==1    ;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Tell Spring to ignore securing the handshake endpoint. This allows the handshake to take place unauthenticated
        web.ignoring().antMatchers("/board/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Create an AuthenticationManager bean to Authenticate users in the ChannelInterceptor
    @Bean
    public AuthenticationManager authManager() throws Exception {

        return this.authenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder(10);

    }

}
