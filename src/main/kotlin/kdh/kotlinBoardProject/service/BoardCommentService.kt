package kdh.kotlinBoardProject.service

import kdh.kotlinBoardProject.dto.boardComment.BoardCommentDto
import kdh.kotlinBoardProject.dto.boardComment.CreateBoardCommentDto
import kdh.kotlinBoardProject.dto.boardComment.UpdateBoardCommentDto
import kdh.kotlinBoardProject.entity.BoardComment
import kdh.kotlinBoardProject.exception.CustomException
import kdh.kotlinBoardProject.exception.ErrorCode
import kdh.kotlinBoardProject.mapper.BoardCommentMapper
import kdh.kotlinBoardProject.repository.BoardCommentRepository
import kdh.kotlinBoardProject.repository.BoardRepository
import kdh.kotlinBoardProject.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BoardCommentService(
    private val boardCommentRepository: BoardCommentRepository,
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
    private val boardCommentMapper: BoardCommentMapper
) {

    fun getBoardCommentList(boardIdx: Long, size: Int, page: Int): Page<BoardCommentDto>? {
        val pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idx"))
        return boardCommentRepository!!.findByBoardIdx(boardIdx, pageRequest)?.map {
            boardCommentMapper.toDto(it)
        }
    }

    fun createBoardComment(dto: CreateBoardCommentDto): BoardCommentDto {
        val board = boardRepository!!.findOneByIdx(dto.boardIdx)?:throw CustomException(ErrorCode.BOARD_NOT_FOUND)
        val user = userRepository!!.findOneByIdx(dto.userIdx)?:throw CustomException(ErrorCode.MEMBER_NOT_FOUND)
        val boardComment = BoardComment(
            content = dto.content,
            user = user,
            board = board
        )

        return boardCommentMapper.toDto(boardCommentRepository!!.save(boardComment))
    }

    fun updateBoardComment(idx: Long, dto: UpdateBoardCommentDto): BoardCommentDto {
        val boardComment = boardCommentRepository!!.findOneByIdx(idx)?:throw CustomException(ErrorCode.BOARD_COMMENT_NOT_FOUND)
        boardCommentMapper.update(dto, boardComment)
        return boardCommentMapper.toDto(boardComment)
    }

    fun deleteBoardComment(idx: Long): BoardCommentDto {
        val boardComment = boardCommentRepository!!.findOneByIdx(idx)?:throw CustomException(ErrorCode.BOARD_COMMENT_NOT_FOUND)
        boardCommentRepository!!.deleteById(idx)
        return boardCommentMapper.toDto(boardComment)
    }
}