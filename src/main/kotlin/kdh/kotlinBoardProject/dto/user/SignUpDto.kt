package kdh.kotlinBoardProject.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import kdh.kotlinBoardProject.entity.Authority
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.constraints.Size


class SignUpDto(
    @get:Size(min = 3, max = 50)
    var id: String,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @get:Size(min = 3, max = 50)
    var pw: String,
    @get:Size(min = 3, max = 50)
    var name: String,
    var activated: Boolean?,
    var authorities: Set<Authority>?
){
    init {
        this.activated = true
        this.authorities = setOf(Authority(authorityName = "ROLE_USER"))
    }
}