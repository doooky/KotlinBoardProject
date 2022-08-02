package kdh.kotlinBoardProject.service

import kdh.kotlinBoardProject.dto.board.BoardDto
import kdh.kotlinBoardProject.dto.board.BoardListDto
import kdh.kotlinBoardProject.dto.board.CreateBoardDto
import kdh.kotlinBoardProject.dto.board.UpdateBoardDto
import kdh.kotlinBoardProject.entity.Board
import kdh.kotlinBoardProject.exception.CustomException
import kdh.kotlinBoardProject.exception.ErrorCode
import kdh.kotlinBoardProject.mapper.BoardMapper
import kdh.kotlinBoardProject.repository.BoardRepository
import kdh.kotlinBoardProject.repository.CategoryRepository
import kdh.kotlinBoardProject.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val boardMapper: BoardMapper
) {

    fun getBoardList(categoryIdx: Long, size: Int, page: Int): Page<BoardListDto>? {
        val pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"))
        return boardRepository!!.findByCategoryIdx(categoryIdx, pageRequest)?.map {
            boardMapper.toListDto(it)
        }
    }

    fun getBoardListByTitle(categoryIdx: Long, size: Int, page: Int, title: String?): Page<BoardListDto>? {
        val pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"))
        return boardRepository!!.findByCategoryIdxAndTitleContainingIgnoreCase(categoryIdx, title, pageRequest)?.map {
            boardMapper.toListDto(it)
        }
    }

    fun getBoard(idx: Long): BoardDto? {
        return boardMapper.toDto(boardRepository!!.findOneByIdx(idx)?:throw CustomException(ErrorCode.CATEGORY_NOT_FOUND))
    }

    @Transactional
    fun createBoard(dto: CreateBoardDto): BoardDto {
        val category = categoryRepository!!.findOneByIdx(dto.categoryIdx)?:throw CustomException(ErrorCode.CATEGORY_NOT_FOUND)
        val user = userRepository!!.findOneByIdx(dto.createdUser)?:throw CustomException(ErrorCode.MEMBER_NOT_FOUND)
        val board = Board(
            title = dto.title,
            content = dto.content,
            user = user,
            category = category
        )

        return boardMapper.toDto(boardRepository!!.save(board))
    }

    @Transactional
    fun updateBoard(idx: Long, dto: UpdateBoardDto): BoardDto {
        val board = boardRepository!!.findByIdOrNull(idx)?:throw CustomException(ErrorCode.BOARD_NOT_FOUND)
        boardMapper.update(dto, board)
        return boardMapper.toDto(board)
    }

    @Transactional
    fun deleteBoard(idx: Long): BoardDto {
        val board = boardRepository!!.findOneByIdx(idx)?:throw CustomException(ErrorCode.BOARD_NOT_FOUND)
        boardRepository!!.deleteById(idx)
        return boardMapper.toDto(board)
    }
}