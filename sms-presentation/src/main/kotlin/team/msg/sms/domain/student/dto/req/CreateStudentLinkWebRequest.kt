package team.msg.sms.domain.student.dto.req

data class CreateStudentLinkWebRequest (
	val studentId: String,
	val periodDay: Long
) {
	fun toData(): CreateStudentLinkRequestData =
		CreateStudentLinkRequestData(
		studentId,
		periodDay
	)
}