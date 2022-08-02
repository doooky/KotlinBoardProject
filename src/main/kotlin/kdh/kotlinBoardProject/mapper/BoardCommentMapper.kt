package kdh.kotlinBoardProject.mapper

import kdh.kotlinBoardProject.dto.boardComment.BoardCommentDto
import kdh.kotlinBoardProject.dto.boardComment.UpdateBoardCommentDto
import kdh.kotlinBoardProject.entity.BoardComment
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BoardCommentMapper {
    fun toDto(boardComment: BoardComment?): BoardCommentDto

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun update(dto: UpdateBoardCommentDto, @MappingTarget boardComment: BoardComment)
}