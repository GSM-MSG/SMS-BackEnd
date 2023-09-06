package team.msg.sms.persistence.project.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import team.msg.sms.persistence.BaseIdEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "project")
class ProjectJpaEntity(
    override val id: Long,

    @Column(length = 30)
    val title: String,

    @Column
    val projectIconUrl: String,

    @Column(length = 1000)
    val description: String,

    @Column(nullable = true, length = 1000)
    val myActivity: String?,

    @Column
    val startDate: String,

    @Column(nullable = true)
    val endDate: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: StudentJpaEntity
) : BaseIdEntity()