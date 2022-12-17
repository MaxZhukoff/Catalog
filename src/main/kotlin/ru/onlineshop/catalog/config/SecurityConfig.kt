package ru.onlineshop.catalog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
class SecurityConfig(
    val jwtRequestFilter: JwtRequestFilter
) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        return http
            .csrf().disable()
            .authorizeHttpRequests()
            .anyRequest().authenticated()
            .and()
            .addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}