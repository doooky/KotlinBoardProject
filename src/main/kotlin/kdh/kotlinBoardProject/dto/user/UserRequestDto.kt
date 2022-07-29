package kdh.kotlinBoardProject.dto.user

import kdh.kotlinBoardProject.entity.User

class UserRequestDto(
    user:User?
){
    var id: String?
    var idx: Long?
    var name: String?

    init {
        idx = user?.idx
        id = user?.id
        name = user?.name
    }
}