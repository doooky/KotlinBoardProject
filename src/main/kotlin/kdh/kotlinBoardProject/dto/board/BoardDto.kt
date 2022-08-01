package kdh.kotlinBoardProject.dto.board

import kdh.kotlinBoardProject.dto.category.CategoryListDto
import kdh.kotlinBoardProject.dto.user.UserResponseDto
import kdh.kotlinBoardProject.entity.Board
import java.time.LocalDateTime

class BoardDto(board: Board?) {
    var idx: Long?
    var title: String?
    var content: String?
    var createdAt: LocalDateTime?
    var user: UserResponseDto
    var category: CategoryListDto

    init {
        idx = board!!.idx
        title = board!!.title
        content = board!!.content
        createdAt = board!!.createdAt
        user = UserResponseDto(board!!.user)
        this.category = CategoryListDto(board!!.category)
    }
}