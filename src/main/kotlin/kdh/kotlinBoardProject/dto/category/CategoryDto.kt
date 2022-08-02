package kdh.kotlinBoardProject.dto.category

import kdh.kotlinBoardProject.dto.user.UserResponseDto
import kdh.kotlinBoardProject.entity.Category


class CategoryDto(category: Category) {
    var categoryIdx: Long?
    var categoryName: String?
    var description: String?
    var user: UserResponseDto? = null

    init {
        categoryIdx = category.idx
        categoryName = category.categoryName
        description = category.description
        //user = UserResponseDto(category.user)
    }
}