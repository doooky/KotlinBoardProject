package kdh.kotlinBoardProject.dto.category

import kdh.kotlinBoardProject.entity.User

class CategoryDto(
    var idx: Long?,
    var categoryName: String?,
    var description: String?,
    var user: User?
)