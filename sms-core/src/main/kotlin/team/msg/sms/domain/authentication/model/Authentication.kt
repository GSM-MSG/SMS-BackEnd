package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import team.msg.sms.domain.student.model.Department
import java.time.LocalDateTime
import java.util.*

@Aggregate
class Authentication (
    val id: UUID,
    val title: String,
    val content: String,
    val activityImages: List<String> = mutableListOf(),
    val score: Int = 0,
    val activityStatus: ActivityStatus = ActivityStatus.PENDING,
    val studentId: UUID
) {
    class AuthenticationWithStudentInfoAndRequestedTime (
        val id: UUID,
        val title: String,
        val requestedTime: LocalDateTime,
        val stuNum: String,
        val name: String,
        val department: Department,
    )

    class AuthenticationWithPageInfo(
        val authentications: List<AuthenticationWithStudentInfoAndRequestedTime>,
        val page: Int,
        val contentSize: Int,
        val totalSize: Long,
        val last: Boolean
    )
}