package kdh.kotlinBoardProject.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class BoardComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "idx")
    var idx: Long? = null,

    @Column(name = "content")
    var content: String? = null,

    @Column(name = "createdAt")
    var createdAt: LocalDateTime? = null,

    @Column(name = "updatedAt")
    var updatedAt: LocalDateTime? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user")
    var user: User? = null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_idx")
    var board: Board? = null
) {
    init {
        createdAt = LocalDateTime.now()
    }

    fun updatedAt() {
        updatedAt = LocalDateTime.now()
    }
}