package team.msg.sms.domain.student.dto.req

import java.util.UUID

data class CreateStudentLinkWebRequest (
	val studentId: UUID,
	val periodDay: Long
) {
	fun toData(): CreateStudentLinkRequestData =
		CreateStudentLinkRequestData(
			studentId,
			periodDay
		)
}
