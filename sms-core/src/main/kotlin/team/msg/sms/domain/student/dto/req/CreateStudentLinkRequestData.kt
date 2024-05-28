package team.msg.sms.domain.student.dto.req

import java.util.UUID

data class CreateStudentLinkRequestData (
	val studentId: UUID,
	val periodDay: Long
)
