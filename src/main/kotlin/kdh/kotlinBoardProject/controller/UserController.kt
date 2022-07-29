package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.dto.user.UserDto
import kdh.kotlinBoardProject.entity.User
import kdh.kotlinBoardProject.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(userService: UserService) {
    private val userService: UserService

    init {
        this.userService = userService
    }

    @PostMapping("/signup")
    fun signup(
        @RequestBody userDto: @Valid UserDto?
    ): ResponseEntity<User?> {
        return ResponseEntity.ok(userService!!.signup(userDto))
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/")
    fun myUserInfo(): ResponseEntity<User?>{
        return ResponseEntity.ok(userService!!.myUserWithAuthorities())
    }

    @GetMapping("{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String?): ResponseEntity<User> {
        return ResponseEntity.ok(userService!!.getUserWithAuthorities(username)!!)
    }
}