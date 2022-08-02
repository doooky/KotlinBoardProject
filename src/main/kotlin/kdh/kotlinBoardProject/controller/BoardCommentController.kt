package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.dto.boardComment.BoardCommentDto
import kdh.kotlinBoardProject.dto.boardComment.CreateBoardCommentDto
import kdh.kotlinBoardProject.dto.boardComment.UpdateBoardCommentDto
import kdh.kotlinBoardProject.service.BoardCommentService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/boardComment")
class BoardCommentController(private val  boardCommentService: BoardCommentService) {
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getBoardCommentList(
            @PathVariable id: Long,
            @RequestParam(value = "size", defaultValue = "10") size: Int,
            @RequestParam(value = "page", defaultValue = "0") page: Int
    ): ResponseEntity<Page<BoardCommentDto>> {
        return ResponseEntity.ok(boardCommentService!!.getBoardCommentList(id, size, page))
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun createBoardComment(@RequestBody dto: CreateBoardCommentDto): ResponseEntity<BoardCommentDto> {
        return ResponseEntity.ok(boardCommentService!!.createBoardComment(dto))
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun updateBoardComment(@PathVariable id: Long, @RequestBody dto: UpdateBoardCommentDto): ResponseEntity<BoardCommentDto> {
        return ResponseEntity.ok(boardCommentService!!.updateBoardComment(id, dto))
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun deleteBoardComment(@PathVariable id: Long): ResponseEntity<BoardCommentDto> {
        return ResponseEntity.ok(boardCommentService!!.deleteBoardComment(id))
    }
}