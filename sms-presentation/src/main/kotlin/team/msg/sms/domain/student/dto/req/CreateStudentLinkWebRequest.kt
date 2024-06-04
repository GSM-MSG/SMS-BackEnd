package team.msg.sms.domain.student.dto.req

import java.util.UUID
import javax.validation.constraints.*

data class CreateStudentLinkWebRequest (
	@field:NotNull
	val studentId: UUID,

	@field:Min(1)
	@field:Max(30)
	@field:NotNull
	val periodDay: Long
) {
	fun toData(): CreateStudentLinkRequestData =
		CreateStudentLinkRequestData(
			studentId,
			periodDay
		)
}
