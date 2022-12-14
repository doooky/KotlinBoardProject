package kdh.kotlinBoardProject.repository

import kdh.kotlinBoardProject.entity.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User?, Long?> {
    @EntityGraph(attributePaths = ["authorities"])
    fun findOneWithAuthoritiesById(id: String): User?
    fun findOneByIdx(idx: Long): User?
}