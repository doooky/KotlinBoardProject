package kdh.kotlinBoardProject.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors

@Component
class TokenProvider(
        @param:Value("\${jwt.secret}") private val secret: String,
        @Value("\${jwt.token-validity-in-seconds}") tokenValidityInSeconds: Long) : InitializingBean {
    private val tokenValidityInMilliseconds: Long
    private var key: Key? = null
    private val log = LoggerFactory.getLogger(javaClass)

    /*
    * application.yml에서 명시한 정보를 주입받아 생성하는 생성자.
    * */
    init {
        tokenValidityInMilliseconds = tokenValidityInSeconds * 1000
    }

    /*
    * 빈이 생성이 되고 의존성 주입이 되고 난 후에 주입받은 secret 값을 Base64 Decode 해서 key 변수에 할당
    * */
    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    /*
     * 토큰 생성 메소드
     * Authentication 객체
     * Spring Security에서 한 유저의 인증 정보를 가지고 있는 객체,
     * 사용자가 인증 과정을 성공적으로 마치면, Spring Security는 사용자의 정보 및 인증 성공여부를 가지고
     * Authentication 객체를 생성한 후 보관한다.
     */
    fun createToken(authentication: Authentication): String {
        val authorities = authentication.authorities.stream()
                .map { obj: GrantedAuthority -> obj.authority }
                .collect(Collectors.joining(","))
        val now = Date().time
        val validity = Date(now + tokenValidityInMilliseconds)
        return Jwts.builder()
                .setSubject(authentication.name)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact()
    }

    // 토큰에 담겨있는 정보를 이용해 Authentication 객체를 리턴
    fun getAuthentication(token: String?): Authentication {
        val claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
        val authorities: Collection<GrantedAuthority> = Arrays.stream(claims[AUTHORITIES_KEY].toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                .map { role: String? -> SimpleGrantedAuthority(role) }
                .collect(Collectors.toList())

        // claims과 authorities 정보를 활용해 User (org.springframework.security.core.userdetails.User) 객체 생성
        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            this.log.info("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            this.log.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            this.log.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            this.log.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            this.log.info("JWT 토큰이 잘못되었습니다.")
        }
        return false
    }

    companion object {
        private const val AUTHORITIES_KEY = "auth"
    }
}