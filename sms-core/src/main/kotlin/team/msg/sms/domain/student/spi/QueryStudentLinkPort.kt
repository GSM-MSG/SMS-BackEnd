package team.msg.sms.domain.student.spi

interface QueryStudentLinkPort {
	fun existsByToken(token: String): Boolean
}
