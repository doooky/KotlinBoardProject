package kdh.kotlinBoardProject.dto.board

import kdh.kotlinBoardProject.entity.Board
import java.time.LocalDateTime

class BoardListDto(board: Board?) {
    var idx: Long?
    var title: String?
    var content: String?
    var createdAt: LocalDateTime?

    init {
        idx = board?.idx
        title = board?.title
        content = board?.content
        createdAt = board?.createdAt
    }
}