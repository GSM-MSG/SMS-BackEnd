package team.msg.sms.persistence.student

import com.querydsl.jpa.impl.JPAQueryFactory
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.transaction.annotation.Transactional

@Transactional
class StudentPersistenceAdapterTest(
    @Autowired
    var entityManager: TestEntityManager
) : FunSpec() {
    init {
        test("QueryDSL Test") {
            val queryFactory = JPAQueryFactory(entityManager.entityManager)
        }
    }
}