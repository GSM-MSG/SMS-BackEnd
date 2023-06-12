package team.msg.sms.domain.auth.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.service.TechStackService
import team.msg.sms.domain.user.service.UserService

@UseCase
class WithdrawalUseCase(
    private val userService: UserService,
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val regionService: RegionService,
    private val languageCertificateService: LanguageCertificateService,
    private val certificateService: CertificateService
) {
    fun execute() {
        val user = userService.getCurrentUser()
        if (user.roles[0].name == "ROLE_STUDENT") {
            val student = studentService.getStudentByUser(user)

            listOf(
                techStackService::deleteAllByStudent,
                regionService::deleteAllByStudent,
                languageCertificateService::deleteAllByStudent,
                certificateService::deleteAllByStudent
            ).forEach { service ->
                service(student, user)
            }

            studentService.deleteByUuid(studentId = student.id)
        }
        userService.deleteByUuid(userId = user.id)
    }
}