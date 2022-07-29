package kdh.kotlinBoardProject.dto.user

import javax.validation.constraints.Size

class LoginDto(
    @get:Size(min = 3, max = 50)
    var username: String?,
    @get:Size(min = 3, max = 100)
    var password: String?,
)