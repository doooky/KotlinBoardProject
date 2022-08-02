package kdh.kotlinBoardProject.dto.boardComment

import kdh.kotlinBoardProject.dto.board.BoardListDto
import kdh.kotlinBoardProject.dto.user.UserResponseDto
import kdh.kotlinBoardProject.entity.BoardComment

class BoardCommentDto(boardComment: BoardComment?) {
    var idx: Long?
    var content: String?
    var user: UserResponseDto? = null
    var board: BoardListDto? = null

    init {
        idx = boardComment?.idx
        content = boardComment?.content
        //user = UserResponseDto(boardComment?.user)
        // board = BoardListDto(boardComment?.board)
    }
}