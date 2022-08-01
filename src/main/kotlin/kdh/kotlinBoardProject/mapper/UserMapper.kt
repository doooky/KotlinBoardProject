package kdh.kotlinBoardProject.mapper

import kdh.kotlinBoardProject.dto.user.SignUpDto
import kdh.kotlinBoardProject.dto.user.UserRequestDto
import kdh.kotlinBoardProject.entity.User
import org.mapstruct.*

/*
* unmappedTargetPolicy = ReportingPolicy.IGNORE :
* 매핑되지 않은 속성을 무시하고 출력 경고를 받지 않으려면 IGNORE 값을 unmappedTargetPolicy에 할당해야 합니다.
* */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {
    fun requestToSignUpDto(userRequestDto: UserRequestDto): SignUpDto
    fun toDto(user: User): SignUpDto
    fun toDomain(signUpDto: SignUpDto): User

    // null value 는 dto -> entity 업데이트시에 적용하지 않는다.
    // @MappingTarget 으로 데이터 매핑 방향 설정
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun update(signUpDto: SignUpDto, @MappingTarget user: User)
}
