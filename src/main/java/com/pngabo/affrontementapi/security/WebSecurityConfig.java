package com.pngabo.affrontementapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();

        //http.httpBasic();

        http.authorizeRequests()
                //region AFFRONTEMENT
                .antMatchers(HttpMethod.POST, "/affrontement/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/affrontement/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/affrontement/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/affrontement/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/affrontement/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/affrontement/**").hasAuthority("USER")
                //enregion

                //region JOUEUR
                .antMatchers(HttpMethod.POST, "/joueur/**").hasAuthority("USER")
                .antMatchers(HttpMethod.PATCH, "/joueur/**").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/joueur/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/joueur/**").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/joueur/**").hasAuthority("USER")
//                .antMatchers(HttpMethod.GET, "/joueur/**").hasAuthority("USER")
                //endregion

                //region LIGUE
                .antMatchers(HttpMethod.POST, "/ligue/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/ligue/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/ligue/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/ligue/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/ligue/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/ligue/**").hasAuthority("USER")
                //endregion
                .anyRequest().permitAll();

        http.addFilterBefore(new JwtAuthorizationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //pour H2
        http.headers()
                .frameOptions()
                .disable();
    }
}
