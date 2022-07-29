package kdh.kotlinBoardProject.jwt

import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class JwtFilter(tokenProvider: TokenProvider) : GenericFilterBean() {
    private val tokenProvider: TokenProvider
    private val log = LoggerFactory.getLogger(javaClass)

    init {
        this.tokenProvider = tokenProvider;
    }

    // 실제 필터링 로직은 doFilter 내부에 작성 jwt 토큰의 인증 정보를 SecurityContext에 저장하는 역할
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val httpServletRequest = servletRequest as HttpServletRequest
        val jwt = resolveToken(httpServletRequest)
        val requestURI = httpServletRequest.requestURI
        if (StringUtils.hasText(jwt) && tokenProvider!!.validateToken(jwt)) {
            val authentication = tokenProvider.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
            this.log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication!!.name, requestURI)
        } else {
            this.log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI)
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }

    // request header에서 토큰 정보를 꺼내오는 메소드
    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}