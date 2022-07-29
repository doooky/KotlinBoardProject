package kdh.kotlinBoardProject.repository

import kdh.kotlinBoardProject.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category?, Long?> {
    override fun findAll(): List<Category?>
    fun findOneByCategoryName(categoryName: String?): Optional<Category?>?
    fun findOneByIdx(idx: Long?): Category?
}