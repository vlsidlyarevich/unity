package com.github.vlsidlyarevich.unity.web.security.config;

import com.github.vlsidlyarevich.unity.web.security.filter.AuthenticationTokenFilter;
import com.github.vlsidlyarevich.unity.web.security.service.TokenAuthenticationService;
import com.github.vlsidlyarevich.unity.web.security.service.TokenService;
import com.github.vlsidlyarevich.unity.web.security.social.adapter.SocialSignInAdapter;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] CORS_ALLOWED_METHODS = {"POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH"};
    private static final String[] CORS_ALLOW_HEADERS = {"x-auth-token", "content-type", "x-requested-with", "accept",
            "origin", "access-control-request-method", "access-control-request-headers",
            "Authorization", "Cache-Control"};

    private final Environment environment;
    private final TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/auth").permitAll()
                .antMatchers("/api/v1/authenticate").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/login**", "/signin/**",
                        "/authenticate/**", "/connect/**", "/social/authenticate").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(
                        new AuthenticationTokenFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
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

        providerSignInController.setSignUpUrl("/social/authenticate");
        providerSignInController.setApplicationUrl(environment.getProperty("spring.application.url"));

        return providerSignInController;
    }

    @Bean
    public ProviderSignInUtils getProviderSignInUtils(final ConnectionFactoryLocator connectionFactoryLocator,
                                                      final UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList(CORS_ALLOWED_METHODS));
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(Arrays.asList(CORS_ALLOW_HEADERS));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
