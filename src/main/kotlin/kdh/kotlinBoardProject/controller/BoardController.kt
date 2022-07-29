package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.dto.board.BoardDto
import kdh.kotlinBoardProject.dto.board.BoardListDto
import kdh.kotlinBoardProject.dto.board.CreateBoardDto
import kdh.kotlinBoardProject.dto.board.UpdateBoardDto
import kdh.kotlinBoardProject.entity.Board
import kdh.kotlinBoardProject.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/board")
class BoardController(boardService: BoardService) {
    private var boardService: BoardService? = null
    init {
        this.boardService = boardService
    }
    @GetMapping("list/{categoryIdx}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getBoardList(
            @PathVariable(value = "categoryIdx") categoryIdx: Long?,
            @RequestParam(value = "size", defaultValue = "10") size: Int,
            @RequestParam(value = "page", defaultValue = "0") page: Int): Page<Board?>? {
        val boardList = boardService!!.getBoardList(categoryIdx, size, page)
        val result = boardList!!.stream()
                .map { o: Board? -> BoardListDto(o) }
                .collect(Collectors.toList())
        return boardList
    }

    @GetMapping("list/{categoryIdx}/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getBoardListByTitle(
            @PathVariable(value = "categoryIdx") categoryIdx: Long?,
            @RequestParam(value = "size", defaultValue = "10") size: Int,
            @RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam title: String?): List<BoardListDto> {
        val boardList = boardService!!.getBoardListByTitle(categoryIdx, size, page, title)
        return boardList!!.stream()
                .map { o: Board? -> BoardListDto(o) }
                .collect(Collectors.toList())
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getBoard(@PathVariable(value = "id") id: Long?): ResponseEntity<BoardDto> {
        val result = BoardDto(boardService!!.getBoard(id))
        return ResponseEntity.ok(result)
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun createBoard(@RequestBody dto: CreateBoardDto): ResponseEntity<BoardDto> {
        val result = BoardDto(boardService!!.createBoard(dto))
        return ResponseEntity.ok(result)
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun updateBoard(@PathVariable(value = "id") id: Long?, @RequestBody dto: UpdateBoardDto): ResponseEntity<BoardDto> {
        val result = BoardDto(boardService!!.updateBoard(id, dto))
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun deleteBoard(@PathVariable id: Long): ResponseEntity<BoardDto> {
        val result = BoardDto(boardService!!.deleteBoard(id))
        return ResponseEntity.ok(result)
    }
}