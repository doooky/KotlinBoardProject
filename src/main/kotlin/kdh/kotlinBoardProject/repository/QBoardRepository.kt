package kdh.kotlinBoardProject.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import kdh.kotlinBoardProject.entity.Board
import kdh.kotlinBoardProject.entity.QBoard.board
import kdh.kotlinBoardProject.entity.QCategory.category
import kdh.kotlinBoardProject.entity.QUser.user
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class QBoardRepository(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory
) {
    fun selectAll(categoryIdx:Long, pageable: Pageable): Page<Board> {
        var totalCount:Long? = jpaQueryFactory
            .select(board.count())
            .from(board)
            .innerJoin(board.category, category)
            .innerJoin(board.user, user).fetchOne()

        var result:List<Board> = jpaQueryFactory
            .selectFrom(board)
            .innerJoin(board.category, category)
            .innerJoin(board.user, user)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        return PageImpl(result, pageable, totalCount?:0)

    }

    fun selectAllSearch(categoryIdx:Long, title: String, pageable: Pageable):Page<Board>{
        var totalCount:Long? = jpaQueryFactory
            .select(board.count())
            .from(board)
            .innerJoin(board.category, category)
            .innerJoin(board.user, user).fetchOne()

        var result:List<Board> = jpaQueryFactory
            .selectFrom(board)
            .innerJoin(board.category, category)
            .innerJoin(board.user, user)
            .where(board.title.contains(title))
            .offset(pageable.offset)
            .limit(pageable.pageSize as Long)
            .fetch()

        return PageImpl(result, pageable, totalCount?:0)
    }

    fun selectOne(idx:Long):Board?{
        return jpaQueryFactory.selectFrom(board).where(board.idx.eq(idx)).fetchOne()
    }
}