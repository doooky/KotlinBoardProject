package kdh.kotlinBoardProject.service

import kdh.kotlinBoardProject.dto.category.CategoryDto
import kdh.kotlinBoardProject.dto.category.CategoryListDto
import kdh.kotlinBoardProject.dto.category.DeleteCategoryDto
import kdh.kotlinBoardProject.dto.category.ResponseCategoryDto
import kdh.kotlinBoardProject.entity.Category
import kdh.kotlinBoardProject.exception.CustomException
import kdh.kotlinBoardProject.exception.ErrorCode
import kdh.kotlinBoardProject.mapper.CategoryMapper
import kdh.kotlinBoardProject.repository.CategoryRepository
import kdh.kotlinBoardProject.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val categoryMapper: CategoryMapper
) {

    fun categoryList(): List<CategoryListDto> {
        return categoryRepository!!.findAll().map { categoryMapper.toListDto(it) }
    }

    @Transactional
    fun createCategory(categoryDto: ResponseCategoryDto): CategoryDto {
        checkDuplicateCategory(categoryDto.categoryName)
        val user = userRepository!!.findOneByIdx(categoryDto.userIdx)?:throw CustomException(ErrorCode.MEMBER_NOT_FOUND)
        val category = Category(
            categoryName = categoryDto.categoryName,
            description = categoryDto.description,
            user = user
        )
        return categoryMapper.toDto(categoryRepository!!.save(category))
    }

    @Transactional
    fun updateCategory(idx: Long, dto: ResponseCategoryDto): CategoryDto {
        val category = checkEmptyCategory(idx)
//        category.categoryName = if (dto.categoryName != null) dto.categoryName else category.categoryName
//        category.description = if (dto.description != null) dto.description else category.description
//        category.updatedAt()
//        return categoryMapper.toDto(category)
        categoryMapper.update(dto, category)
        return categoryMapper.toDto(category)
    }

    @Transactional
    fun deleteCategory(idx: Long): DeleteCategoryDto {
        val category = categoryMapper.toDto(checkEmptyCategory(idx))
        categoryRepository!!.deleteById(idx)
        return DeleteCategoryDto("?????? ??????", category)
    }

    private fun checkEmptyCategory(idx: Long): Category {
        return categoryRepository!!.findOneByIdx(idx)?:throw CustomException(ErrorCode.CATEGORY_NOT_FOUND)
    }

    private fun checkDuplicateCategory(categoryName: String) {
        val category = categoryRepository!!.findOneByCategoryName(categoryName)
        if (!category!!.isEmpty) {
            throw CustomException(ErrorCode.DUPLICATE_RESOURCE)
        }
    }
}