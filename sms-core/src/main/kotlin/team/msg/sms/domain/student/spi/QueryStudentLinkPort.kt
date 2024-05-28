package team.msg.sms.domain.student.spi

import java.util.UUID

interface QueryStudentLinkPort {
	fun existsByToken(token: String): Boolean
	fun findStudentIdByToken(token: String): UUID?
}
