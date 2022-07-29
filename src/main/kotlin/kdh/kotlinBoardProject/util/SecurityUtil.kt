package kdh.kotlinBoardProject.util

import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

open class SecurityUtil {
    private val log = LoggerFactory.getLogger(javaClass)

    open fun currentUsername(): Optional<String> {
        val authentication = SecurityContextHolder.getContext().authentication;

        if(authentication == null){
            this.log.debug("Security Context에 인증 정보가 없습니다.")
            return Optional.empty()
        }

        var userName:String? = null
        if(authentication.principal is UserDetails){
            val springSecurityUser:UserDetails? = authentication.principal as UserDetails
            userName = springSecurityUser?.username
        } else if(authentication.principal is String){
            userName = authentication.principal as String
        }

        return Optional.ofNullable(userName)


    }



}