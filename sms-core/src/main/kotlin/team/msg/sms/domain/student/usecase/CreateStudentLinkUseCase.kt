package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.util.PasswordUtil.generateSecret
import team.msg.sms.domain.student.dto.req.CreateStudentLinkRequestData
import team.msg.sms.domain.student.dto.res.CreateStudentLinkResponseData
import team.msg.sms.domain.student.model.StudentLink
import team.msg.sms.domain.student.service.StudentLinkService
import team.msg.sms.domain.student.service.StudentService
import java.util.*

@UseCase
class CreateStudentLinkUseCase (
	private val studentService: StudentService,
	private val studentLinkService: StudentLinkService,
) {
	fun execute(createStudentLinkData: CreateStudentLinkRequestData): CreateStudentLinkResponseData {
		val student = studentService.getStudentById(createStudentLinkData.studentId)
		val token = generateSecret(32)

		val studentLink = StudentLink(
			token = token,
			studentId = student.id,
			timeToLive = createStudentLinkData.periodDay * 86400
		)
		studentLinkService.save(studentLink)

		return CreateStudentLinkResponseData(
			token = token
		)
	}
}
