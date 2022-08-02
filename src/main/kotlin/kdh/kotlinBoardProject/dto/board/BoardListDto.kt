package kdh.kotlinBoardProject.dto.board

import kdh.kotlinBoardProject.entity.Board
import java.time.LocalDateTime

class BoardListDto(
    var idx: Long,
    var title: String,
    var content: String,
    var createdAt: LocalDateTime
)