package kdh.kotlinBoardProject.dto.boardComment

class CreateBoardCommentDto(
    var content: String? = null,
    var userIdx: Long? = null,
    var boardIdx: Long? = null
)