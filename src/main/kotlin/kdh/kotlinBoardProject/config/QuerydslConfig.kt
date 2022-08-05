package kdh.kotlinBoardProject.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class QuerydslConfig(
    // 영속성 컨텍스트를 관리하는 entitymanager를 의존성 주입받아 JPAQueryFactory의 인자로 넘김.
    // @PersistenceContext
    // 컨테이너 관리 EntityManager 및 관련 지속성 컨텍스트에 대한 종속성을 표현합니다
    @PersistenceContext
    private val entityManager: EntityManager
) {
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory{
        return JPAQueryFactory(this.entityManager)
    }
}