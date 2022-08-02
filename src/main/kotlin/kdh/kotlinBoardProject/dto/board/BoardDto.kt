package kdh.kotlinBoardProject.dto.board

import kdh.kotlinBoardProject.dto.category.CategoryListDto
import kdh.kotlinBoardProject.dto.user.UserResponseDto
import kdh.kotlinBoardProject.entity.Board
import java.time.LocalDateTime

class BoardDto(
    var idx: Long?,
    var title: String?,
    var content: String?,
    var createdAt: LocalDateTime?,
    var category: CategoryListDto?
)