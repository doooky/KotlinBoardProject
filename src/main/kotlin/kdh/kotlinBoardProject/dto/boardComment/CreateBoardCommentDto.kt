package kdh.kotlinBoardProject.dto.boardComment

class CreateBoardCommentDto(
    var content: String,
    var userIdx: Long,
    var boardIdx: Long
)