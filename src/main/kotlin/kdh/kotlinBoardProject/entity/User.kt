package kdh.kotlinBoardProject.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity // DB의 테이블과 1대1 매핑되는 객체.
class User(
    @JsonIgnore
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null,

    @Column(name = "id", length = 50, unique = true)
    var id: String? = null,

    @JsonIgnore
    @Column(name = "pw", length = 100)
    var pw: String? = null,

    @Column(name = "name", length = 50)
    var name: String? = null,

    @JsonIgnore
    @Column(name = "activated")
    var activated: Boolean? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "user_authority", joinColumns = [JoinColumn(name = "user_idx", referencedColumnName = "idx")], inverseJoinColumns = [JoinColumn(name = "authority_idx", referencedColumnName = "idx")])
    val authorities: Set<Authority>? = null //
    // @OneToMany(mappedBy = "user")
    //    private List<Category> categoryList = new ArrayList<>();
    //    @OneToMany(mappedBy = "user")
    //    private List<Board> boardList = new ArrayList<>();
    //
    //    @OneToMany(mappedBy = "user")
    //    private List<BoardComment> boardCommentList = new ArrayList<>();
    //
    //    public void addCategory(Category category){
    //        categoryList.add(category);
    //        category.setUser(this);
    //    }
)