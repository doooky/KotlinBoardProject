package kdh.kotlinBoardProject.dto.board

class CreateBoardDto (
    var title: String? = null,
    var content: String? = null,
    var createdUser: Long? = null,
    var categoryIdx: Long? = null
)