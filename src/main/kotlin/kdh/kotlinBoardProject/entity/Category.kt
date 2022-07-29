package kdh.kotlinBoardProject.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import kdh.kotlinBoardProject.entity.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    var idx: Long? = null,

    @Column(name = "name")
    var categoryName: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user")
    var user: User,
) {
    init {
        createdAt = LocalDateTime.now()
        this.user = user
    }

    fun updatedAt() {
        updatedAt = LocalDateTime.now()
    }
}