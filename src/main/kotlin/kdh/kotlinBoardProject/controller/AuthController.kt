package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.dto.user.LoginDto
import kdh.kotlinBoardProject.dto.user.TokenDto
import kdh.kotlinBoardProject.jwt.TokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthController(tokenProvider: TokenProvider, authenticationManagerBuilder: AuthenticationManagerBuilder){
    private val tokenProvider: TokenProvider
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
    init{
        this.tokenProvider = tokenProvider
        this.authenticationManagerBuilder = authenticationManagerBuilder
    }

    @PostMapping("/authenticate")
    fun authorize(@RequestBody loginDto: @Valid LoginDto?): ResponseEntity<TokenDto> {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto?.username, loginDto?.password)
        val authentication = authenticationManagerBuilder!!.`object`.authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = tokenProvider!!.createToken(authentication)
        return ResponseEntity.ok<TokenDto>(TokenDto(jwt))
    }
}