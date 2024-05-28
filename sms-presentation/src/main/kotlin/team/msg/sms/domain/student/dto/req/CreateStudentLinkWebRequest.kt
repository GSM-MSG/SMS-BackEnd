package team.msg.sms.domain.student.dto.req

import java.util.UUID
import javax.validation.constraints.*

data class CreateStudentLinkWebRequest (
	@field:NotBlank
	val studentId: UUID,

	@field:Min(1)
	@field:Max(30)
	@field:NotBlank
	val periodDay: Long
) {
	fun toData(): CreateStudentLinkRequestData =
		CreateStudentLinkRequestData(
			studentId,
			periodDay
		)
}
