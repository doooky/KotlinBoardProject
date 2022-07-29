package kdh.kotlinBoardProject.dto.category

import kdh.kotlinBoardProject.entity.Category
import kdh.kotlinBoardProject.dto.user.UserRequestDto


class CategoryDto(category: Category) {
    var categoryIdx: Long?
    var categoryName: String?
    var description: String?
    var user: UserRequestDto

    init {
        categoryIdx = category.idx
        categoryName = category.categoryName
        description = category.description
        user = UserRequestDto(category.user)
    }
}