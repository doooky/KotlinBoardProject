package kdh.kotlinBoardProject.repository

import kdh.kotlinBoardProject.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BoardRepository : JpaRepository<Board?, Long?> {
    override fun findAll(pageable: Pageable): Page<Board?>

    // 대소문자 무시하고 게시물 제목 검색
    fun findByCategoryIdxAndTitleContainingIgnoreCase(categoryIdx: Long, title: String?, pageable: Pageable): Page<Board>?
    fun findByCategoryIdx(categoryIdx: Long, pageable: Pageable): Page<Board>?
    fun findOneByIdx(idx: Long): Board?
}