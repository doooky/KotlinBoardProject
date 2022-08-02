package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.dto.user.SignUpDto
import kdh.kotlinBoardProject.dto.user.UserRequestDto
import kdh.kotlinBoardProject.dto.user.UserResponseDto
import kdh.kotlinBoardProject.entity.User
import kdh.kotlinBoardProject.mapper.UserMapper
import kdh.kotlinBoardProject.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
    private val userMapper: UserMapper
    ) {

    @PostMapping("/signup")
    fun signup(
        @RequestBody userRequestDto: UserRequestDto
    ): ResponseEntity<UserResponseDto?> {
        return ResponseEntity.ok(userService!!.signup(userMapper.requestToSignUpDto(userRequestDto)))
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/")
    fun myUserInfo(): ResponseEntity<User?>{
        return ResponseEntity.ok(userService!!.myUserWithAuthorities())
    }

    @GetMapping("{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String): ResponseEntity<User> {
        return ResponseEntity.ok(userService!!.getUserWithAuthorities(username)!!)
    }
}