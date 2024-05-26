package team.msg.sms.domain.student.dto.req

data class CreateStudentLinkRequestData (
	val studentId: String,
	val periodDay: Long
)