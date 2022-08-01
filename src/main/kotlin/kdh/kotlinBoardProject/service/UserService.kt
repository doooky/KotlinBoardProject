package kdh.kotlinBoardProject.service

import kdh.kotlinBoardProject.exception.DuplicateMemberException
import kdh.kotlinBoardProject.dto.user.UserDto
import kdh.kotlinBoardProject.entity.Authority
import kdh.kotlinBoardProject.entity.User
import kdh.kotlinBoardProject.repository.UserRepository
import kdh.kotlinBoardProject.util.SecurityUtil

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
open class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    private val securityUtil: SecurityUtil = SecurityUtil()

    @Transactional
    open fun signup(userDto: UserDto?): User {
        userRepository.findOneWithAuthoritiesById(userDto?.username)?: throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")

        val authority = Authority(authorityName = "ROLE_USER")
        val user = User(
            id = userDto?.username,
            pw = passwordEncoder!!.encode(userDto?.password),
            name = userDto?.nickname,
            authorities = setOf(authority),
            activated = true
        )

        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun getUserWithAuthorities(id: String?): User? {
        return userRepository!!.findOneWithAuthoritiesById(id)
    }

    @Transactional(readOnly = true)
    fun myUserWithAuthorities(): User?{
        return securityUtil!!.currentUsername().let {userRepository.findOneWithAuthoritiesById(it.get())}
    }

}