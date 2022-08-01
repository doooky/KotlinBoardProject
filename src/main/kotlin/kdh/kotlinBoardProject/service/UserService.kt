package kdh.kotlinBoardProject.service

import kdh.kotlinBoardProject.dto.user.SignUpDto
import kdh.kotlinBoardProject.entity.User
import kdh.kotlinBoardProject.exception.DuplicateMemberException
import kdh.kotlinBoardProject.mapper.UserMapper
import kdh.kotlinBoardProject.repository.UserRepository
import kdh.kotlinBoardProject.util.SecurityUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder
) {
    private val securityUtil: SecurityUtil = SecurityUtil()

    @Transactional
    fun signup(signUpDto: SignUpDto?): User {
        if(userRepository.findOneWithAuthoritiesById(signUpDto!!.id) != null){
            throw DuplicateMemberException("이미 가입되어 있는 유저입니다.")
        }
        var user = User(
            id = signUpDto.id,
            pw = passwordEncoder!!.encode(signUpDto.pw),
            name = signUpDto.name,
            activated = signUpDto.activated,
            authorities = signUpDto.authorities
        )
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun getUserWithAuthorities(id: String): User? {
        return userRepository!!.findOneWithAuthoritiesById(id)
    }

    @Transactional(readOnly = true)
    fun myUserWithAuthorities(): User?{
        return securityUtil!!.currentUsername().let {userRepository.findOneWithAuthoritiesById(it.get())}
    }

}