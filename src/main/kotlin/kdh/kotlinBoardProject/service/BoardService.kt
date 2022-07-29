package kdh.kotlinBoardProject.service

import kdh.kotlinBoardProject.dto.board.CreateBoardDto
import kdh.kotlinBoardProject.dto.board.UpdateBoardDto
import kdh.kotlinBoardProject.entity.Board
import kdh.kotlinBoardProject.exception.CustomException
import kdh.kotlinBoardProject.exception.ErrorCode
import kdh.kotlinBoardProject.repository.BoardRepository
import kdh.kotlinBoardProject.repository.CategoryRepository
import kdh.kotlinBoardProject.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BoardService(
    boardRepository: BoardRepository,
    userRepository: UserRepository,
    categoryRepository: CategoryRepository
) {
    private var boardRepository: BoardRepository? = null
    private var userRepository: UserRepository? = null
    private var categoryRepository: CategoryRepository? = null

    init{
        this.boardRepository = boardRepository
        this.userRepository = userRepository
        this.categoryRepository = categoryRepository
    }

    fun getBoardList(categoryIdx: Long, size: Int, page: Int): Page<Board>? {
        val pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"))
        return boardRepository!!.findByCategoryIdx(categoryIdx, pageRequest)
    }

    fun getBoardListByTitle(categoryIdx: Long, size: Int, page: Int, title: String?): Page<Board>? {
        val pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"))
        return boardRepository!!.findByCategoryIdxAndTitleContainingIgnoreCase(categoryIdx, title, pageRequest)
    }

    fun getBoard(idx: Long?): Board? {
        val board = boardRepository!!.findOneByIdx(idx)?:throw CustomException(ErrorCode.CATEGORY_NOT_FOUND)
        return board
    }

    @Transactional
    fun createBoard(dto: CreateBoardDto): Board {
        val category = categoryRepository!!.findOneByIdx(dto.categoryIdx)?:throw CustomException(ErrorCode.CATEGORY_NOT_FOUND)
        val user = userRepository!!.findOneByIdx(dto.createdUser)?:throw CustomException(ErrorCode.MEMBER_NOT_FOUND)
        val board = Board(
            title = dto.title,
            content = dto.content,
            user = user,
            category = category
        )

        return boardRepository!!.save(board)
    }

    @Transactional
    fun updateBoard(idx: Long, dto: UpdateBoardDto): Board {
        val board = boardRepository!!.findByIdOrNull(idx)?:throw CustomException(ErrorCode.BOARD_NOT_FOUND)
        board.title = if (dto.title != null) dto.title else board.title
        board.content = if (dto.content != null) dto.content else board.content
        return board
    }

    @Transactional
    fun deleteBoard(idx: Long): Board {
        val board = boardRepository!!.findOneByIdx(idx)?:throw CustomException(ErrorCode.BOARD_NOT_FOUND)
        boardRepository!!.deleteById(idx)
        return board
    }
}