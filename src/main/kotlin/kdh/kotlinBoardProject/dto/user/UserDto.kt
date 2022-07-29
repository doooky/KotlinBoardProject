package kdh.kotlinBoardProject.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


class UserDto(
    @get:Size(min = 3, max = 50)
    var username: String?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @get:Size(min = 3, max = 50)
    var password: String?,
    @get:Size(min = 3, max = 50)
    var nickname: String?,
)