package kdh.kotlinBoardProject.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import kdh.kotlinBoardProject.entity.BoardComment
import kdh.kotlinBoardProject.entity.QBoard.board
import kdh.kotlinBoardProject.entity.QBoardComment.boardComment
import kdh.kotlinBoardProject.entity.QUser.user
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class QBoardCommentRepository(
    val entityManager: EntityManager,
    val jpaQueryFactory: JPAQueryFactory
) {
    fun selectAll(boardIdx: Long, pageable: Pageable): Page<BoardComment>?{

        var totalCount:Long? = jpaQueryFactory
            .select(boardComment.count())
            .from(boardComment)
            .innerJoin(boardComment.board, board)
            .innerJoin(boardComment.user, user).fetchOne()

        var result:List<BoardComment> = jpaQueryFactory
            .selectFrom(boardComment)
            .innerJoin(boardComment.board, board)
            .innerJoin(board.user, user)
            .offset(pageable.offset)
            .limit(pageable.pageSize as Long)
            .fetch()

        return PageImpl(result, pageable, totalCount?:0)
    }

    fun selectOne(idx: Long): BoardComment?{
        return jpaQueryFactory.selectFrom(boardComment).where(boardComment.idx.eq(idx)).fetchOne()
    }
}