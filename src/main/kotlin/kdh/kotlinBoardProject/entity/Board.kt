package kdh.kotlinBoardProject.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    var idx: Long? = null,

    @Column(name = "title")
    var title: String? = null,

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
    @JoinColumn(name = "category_idx")
    var category: Category? = null
){
    init {
        createdAt = LocalDateTime.now()
    }

    fun updatedAt() {
        updatedAt = LocalDateTime.now()
    }
}