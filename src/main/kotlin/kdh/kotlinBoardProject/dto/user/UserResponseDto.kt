package kdh.kotlinBoardProject.dto.user

import kdh.kotlinBoardProject.entity.User

class UserResponseDto (
    var user: User?
){
    var idx: Long? = user!!.idx
    var id: String? = user!!.id
    var name: String? = user!!.name
}