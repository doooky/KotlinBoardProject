package kdh.kotlinBoardProject.dto.boardComment

import kdh.kotlinBoardProject.dto.board.BoardListDto
import kdh.kotlinBoardProject.dto.user.UserRequestDto
import kdh.kotlinBoardProject.entity.BoardComment

class BoardCommentDto(boardComment: BoardComment?) {
    var idx: Long?
    var content: String?
    var user: UserRequestDto?
    var board: BoardListDto?

    init {
        idx = boardComment?.idx
        content = boardComment?.content
        user = UserRequestDto(boardComment?.user)
        board = BoardListDto(boardComment?.board)
    }
}