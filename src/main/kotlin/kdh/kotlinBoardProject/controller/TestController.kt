package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController(private val userService:UserService) {

    @GetMapping("hello")
    fun test(@RequestParam str: String): ResponseEntity<String> {
        return ResponseEntity.ok(str);
    }
}