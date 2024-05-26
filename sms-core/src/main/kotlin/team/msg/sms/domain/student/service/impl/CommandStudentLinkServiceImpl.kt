package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.StudentLink
import team.msg.sms.domain.student.service.CommandStudentLinkService
import team.msg.sms.domain.student.spi.StudentLinkPort

@Service
class CommandStudentLinkServiceImpl(
	private val studentLinkPort: StudentLinkPort
): CommandStudentLinkService {
	override fun save(studentLink: StudentLink): StudentLink {
		return studentLinkPort.save(studentLink)
	}
}