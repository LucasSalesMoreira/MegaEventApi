package app.megaeventapi.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class BasicAuth {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf()
            .ignoringRequestMatchers("/**")
        return http.build()
    }
}