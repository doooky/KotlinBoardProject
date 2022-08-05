package kdh.kotlinBoardProject.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import kdh.kotlinBoardProject.entity.Category
import kdh.kotlinBoardProject.entity.QCategory.category
import kdh.kotlinBoardProject.entity.QUser.user
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class QCategoryRepository(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory
) {
    fun selectAll(): List<Category> {
        return jpaQueryFactory
            .selectFrom(category)
            .innerJoin(category.user, user)
            .fetch()
    }

    fun select(idx:Long): Category? {
        return jpaQueryFactory
            .selectFrom(category)
            .innerJoin(category.user, user)
            .where(category.idx.eq(idx)).fetchOne()
    }

    fun selectByCategoryName(categoryName: String): List<Category>? {
        return jpaQueryFactory
            .selectFrom(category)
            .innerJoin(category.user, user)
            .where(category.categoryName.contains(categoryName)).fetch()
    }
}