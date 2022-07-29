package kdh.kotlinBoardProject.entity

import javax.persistence.*

@Entity
class Authority (
    @Id @Column(name = "idx") @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null,
    @Column(name = "name", length = 50)
    var authorityName: String? = null
)