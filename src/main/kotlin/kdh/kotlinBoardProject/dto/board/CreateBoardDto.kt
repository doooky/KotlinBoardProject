package kdh.kotlinBoardProject.dto.board

class CreateBoardDto (
    var title: String,
    var content: String,
    var createdUser: Long,
    var categoryIdx: Long
)