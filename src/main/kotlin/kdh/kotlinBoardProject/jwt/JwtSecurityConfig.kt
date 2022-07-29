package kdh.kotlinBoardProject.jwt

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class JwtSecurityConfig(jwtFilter: JwtFilter) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    private val jwtFilter: JwtFilter

    init {
        this.jwtFilter = jwtFilter
    }
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}