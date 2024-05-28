package team.msg.sms.domain.authentication.dto.res

import org.assertj.core.internal.Integers
import team.msg.sms.domain.authentication.model.ActivityStatus
import java.time.LocalDate
import java.util.UUID

data class RequestStudentAuthenticationResponseData(
    val id: UUID,
	val title: String,
	val lastModifiedDate: LocalDate,
	val score: Int,
	val activityStatus: ActivityStatus
)
