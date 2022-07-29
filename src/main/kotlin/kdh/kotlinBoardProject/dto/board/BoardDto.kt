package kdh.kotlinBoardProject.dto.board

import kdh.kotlinBoardProject.dto.category.CategoryListDto
import kdh.kotlinBoardProject.dto.user.UserRequestDto
import kdh.kotlinBoardProject.entity.Board
import java.time.LocalDateTime

class BoardDto(board: Board?) {
    var idx: Long?
    var title: String?
    var content: String?
    var createdAt: LocalDateTime?
    var user: UserRequestDto
    var category: CategoryListDto

    init {
        idx = board?.idx
        title = board?.title
        content = board?.content
        createdAt = board?.createdAt
        user = UserRequestDto(board?.user)
        this.category = CategoryListDto(board?.category)
    }
}