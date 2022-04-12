package teksystem.casestudyDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import teksystem.casestudyDemo.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /*
     * The indentations are not needed, however, coming back to this without indentations
     * would make know and following along a lot more difficult
     *
     * This is boiler page code
     * */
    // @formatter:off
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                //This is for people who are not logged in
                .antMatchers("/pub/**", "/error/**", "/login/**", "/index").permitAll()
                //For the people who are not logged in
                .antMatchers("/admin/**", "/cart/**", "/user/**").authenticated()
                .and()
            .formLogin()
                //This is the url for the requestMapping in the controller, since page name is loginForm.
                //but the requestionMapping(URL) in the controller is login/login
                .loginPage("/login/login")
                //This is the link that is used in the forms
                .loginProcessingUrl("/login/loginSubmit")
                .defaultSuccessUrl("/index")
                .and()
            .logout()
                .invalidateHttpSession(true)
                //make logout in login directory
                .logoutUrl("/login/logout")
                //Goes to index when logout is successful
                .logoutSuccessUrl("/index")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/error/404");
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
