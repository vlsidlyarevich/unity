package com.github.vlsidlyarevich.unity.web.security.config;

import com.github.vlsidlyarevich.unity.web.security.filter.AuthenticationTokenFilter;
import com.github.vlsidlyarevich.unity.web.security.service.TokenAuthenticationService;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import com.github.vlsidlyarevich.unity.web.security.social.adapter.SocialSignInAdapter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;

@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/auth").permitAll()
                .antMatchers("/api/v1/signup").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/login**", "/signin/**",
                        "/signup/**", "/connect/**", "/social/signup").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                        new AuthenticationTokenFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

    @Bean
    public SignInAdapter signInAdapter(final UserDetailsService userDetailsService,
                                       final TokenService tokenService) {
        return new SocialSignInAdapter(userDetailsService, tokenService);
    }

    @Bean
    public ProviderSignInController providerSignInController(final ConnectionFactoryLocator connectionFactoryLocator,
                                                             final UsersConnectionRepository usersConnectionRepository,
                                                             final SignInAdapter signInAdapter) {
        final ProviderSignInController providerSignInController = new ProviderSignInController(
                connectionFactoryLocator, usersConnectionRepository, signInAdapter);

        providerSignInController.setSignUpUrl("/social/signup");

        return providerSignInController;
    }

    @Bean
    public ProviderSignInUtils getProviderSignInUtils(final ConnectionFactoryLocator connectionFactoryLocator,
                                                      final UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }
}
