package uz.pdp.springbootdemo.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.springbootdemo.filter.CustomAuthenticationFilter;
import uz.pdp.springbootdemo.filter.CustomAuthorizationFilter;
import uz.pdp.springbootdemo.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
        ;

//                .inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("mirzaasror")
//                .password("123")
//                .authorities("ROLE_RAHBAR", "CAR_QUSHA_OLADI") // CAN_ADD_CAR
////                ====================
//                .and()
//                .withUser("oybek")
//                .password("222")
//                .roles("ADMIN")
////                ====================
//                .and()
//                .withUser("afzal")
//                .password("333")
//                .roles( "USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
//               .antMatchers(HttpMethod.GET);
//               .antMatchers(HttpMethod.GET, "/cars");
                .antMatchers(HttpMethod.GET, "/", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
//                .antMatchers("/cars/get-form")
//                .hasAnyAuthority("ROLE_ADMIN", "CAR_QUSHA_OLADI")
                .anyRequest()
                .authenticated();
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http
//                .formLogin()
//                .loginPage("/auth").permitAll()
//                .loginProcessingUrl("/login")
//                .permitAll();
//               .httpBasic();
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(customAuthenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter(userService), UsernamePasswordAuthenticationFilter.class);


    }
}
