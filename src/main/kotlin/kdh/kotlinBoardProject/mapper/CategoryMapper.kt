package kdh.kotlinBoardProject.mapper

import kdh.kotlinBoardProject.dto.category.CategoryDto
import kdh.kotlinBoardProject.dto.category.CategoryListDto
import kdh.kotlinBoardProject.dto.category.ResponseCategoryDto
import kdh.kotlinBoardProject.dto.user.SignUpDto
import kdh.kotlinBoardProject.dto.user.UserResponseDto
import kdh.kotlinBoardProject.entity.Category
import kdh.kotlinBoardProject.entity.User
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CategoryMapper {
    @Mappings(
        Mapping(source = "idx", target = "categoryIdx")
    )
    fun toListDto(category: Category?): CategoryListDto
    @Mappings(
        Mapping(source = "idx", target = "categoryIdx")
    )
    fun toDto(category: Category?): CategoryDto
    fun toDomain(categoryDto: CategoryDto): Category

    // null value 는 dto -> entity 업데이트시에 적용하지 않는다.
    // @MappingTarget 으로 데이터 매핑 방향 설정
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun update(responseCategoryDto: ResponseCategoryDto, @MappingTarget category: Category)
}