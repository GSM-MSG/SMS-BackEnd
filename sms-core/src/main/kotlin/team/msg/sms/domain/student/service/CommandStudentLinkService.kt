package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.StudentLink

interface CommandStudentLinkService {
	fun save(
		studentLink: StudentLink
	): StudentLink
}