package kdh.kotlinBoardProject.dto.category

import kdh.kotlinBoardProject.entity.Category
import java.time.LocalDateTime

class CategoryListDto(category: Category?) {
    var categoryIdx: Long?
    var categoryName: String?
    var description: String?
    var createdAt: LocalDateTime?

    init {
        categoryIdx = category?.idx
        categoryName = category?.categoryName
        description = category?.description
        createdAt = category?.createdAt
    }
}