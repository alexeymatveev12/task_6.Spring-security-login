package task5.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//@Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    SpringSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
//    @Bean
//    public UserDetailsService getUserDetailsService(){
//        return new UserDetailsServiceImpl();
//    }


//    @Autowired
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

///&&&&&&&&&&&&&&&&&?????????????????????????????????????????????????????
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        User.UserBuilder users = User.withDefaultPasswordEncoder();

 //       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // auth.userDetailsService()
 //       auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
 //       auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");

        auth.inMemoryAuthentication().withUser(users.username("user").password("user").roles("USER"));
        auth.inMemoryAuthentication().withUser(users.username("admin").password("admin").roles("ADMIN"));

        //   UserBuilder users = User.withDefaultPasswordEncoder();


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // включаем защиту от CSRF атак
        http.csrf()
                .disable()
                // указываем правила запросов
                // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                .antMatchers("/resources/**", "/**").permitAll()
                .anyRequest().permitAll()
                .and();


        http
                .authorizeRequests()//авторизуем запросы
                .antMatchers("/welcome").permitAll()
               // .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
              //  .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/user/**").access("hasRole('USER')")

           //     .anyRequest().anonymous() //для того чтобы /login не был доступен для авторизованных пользователей
                .and()

                .formLogin()
                .loginPage("/login")
  //              .successHandler(myAuthenticationSuccessHandler()) //стратегия перенаправления после /login
         //  .loginProcessingUrl("/authenticateTheUser")// как работает?
                .failureUrl("/login?error=true")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("username")
                .passwordParameter("password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutUrl("/logout")
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);
    }
}

