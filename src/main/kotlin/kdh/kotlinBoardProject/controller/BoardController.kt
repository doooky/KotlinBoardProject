package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.dto.board.BoardDto
import kdh.kotlinBoardProject.dto.board.BoardListDto
import kdh.kotlinBoardProject.dto.board.CreateBoardDto
import kdh.kotlinBoardProject.dto.board.UpdateBoardDto
import kdh.kotlinBoardProject.entity.Board
import kdh.kotlinBoardProject.mapper.BoardMapper
import kdh.kotlinBoardProject.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/board")
class BoardController(
    private val boardService: BoardService
    ) {

    @GetMapping("list/{categoryIdx}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getBoardList(
            @PathVariable(value = "categoryIdx") categoryIdx: Long,
            @RequestParam(value = "size", defaultValue = "10") size: Int,
            @RequestParam(value = "page", defaultValue = "0") page: Int): Page<BoardListDto>? {
        return boardService!!.getBoardList(categoryIdx, size, page)
    }

    @GetMapping("list/{categoryIdx}/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getBoardListByTitle(
            @PathVariable(value = "categoryIdx") categoryIdx: Long,
            @RequestParam(value = "size", defaultValue = "10") size: Int,
            @RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam title: String?): Page<BoardListDto>? {
        return boardService!!.getBoardListByTitle(categoryIdx, size, page, title)
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getBoard(@PathVariable(value = "id") id: Long): ResponseEntity<BoardDto> {
        return ResponseEntity.ok(boardService!!.getBoard(id))
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun createBoard(@RequestBody dto: CreateBoardDto): ResponseEntity<BoardDto> {
        return ResponseEntity.ok(boardService!!.createBoard(dto))
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun updateBoard(@PathVariable(value = "id") id: Long, @RequestBody dto: UpdateBoardDto): ResponseEntity<BoardDto> {
        return ResponseEntity.ok(boardService!!.updateBoard(id, dto))
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun deleteBoard(@PathVariable id: Long): ResponseEntity<BoardDto> {
        return ResponseEntity.ok(boardService!!.deleteBoard(id))
    }
}