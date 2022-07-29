package kdh.kotlinBoardProject.controller

import kdh.kotlinBoardProject.dto.category.CategoryDto
import kdh.kotlinBoardProject.dto.category.CategoryListDto
import kdh.kotlinBoardProject.dto.category.DeleteCategoryDto
import kdh.kotlinBoardProject.dto.category.ResponseCategoryDto
import kdh.kotlinBoardProject.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/category")
class CategoryController(categoryService: CategoryService) {
    private var categoryService: CategoryService? = null

    init {
        this.categoryService = categoryService
    }
    @get:PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @get:GetMapping("/")
    val categoryList: ResponseEntity<List<CategoryListDto?>?>
        get() = ResponseEntity.ok(categoryService!!.categoryList())

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun createCategory(@RequestBody dto: ResponseCategoryDto): ResponseEntity<CategoryDto?> {
        return ResponseEntity.ok(categoryService!!.createCategory(dto))
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun updateCategory(@PathVariable("id") id: Long, @RequestBody dto: ResponseCategoryDto): ResponseEntity<CategoryDto?> {
        return ResponseEntity.ok(categoryService!!.updateCategory(id, dto))
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun deleteCategory(@PathVariable("id") id: Long): ResponseEntity<DeleteCategoryDto?> {
        return ResponseEntity.ok(categoryService!!.deleteCategory(id))
    }
}