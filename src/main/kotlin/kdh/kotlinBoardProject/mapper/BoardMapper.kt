package kdh.kotlinBoardProject.mapper

import kdh.kotlinBoardProject.dto.board.BoardDto
import kdh.kotlinBoardProject.dto.board.BoardListDto
import kdh.kotlinBoardProject.dto.board.UpdateBoardDto
import kdh.kotlinBoardProject.entity.Board
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BoardMapper {
    fun toDto(board: Board?): BoardDto
    fun toListDto(board: Board?): BoardListDto

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun update(dto: UpdateBoardDto, @MappingTarget board: Board)
}