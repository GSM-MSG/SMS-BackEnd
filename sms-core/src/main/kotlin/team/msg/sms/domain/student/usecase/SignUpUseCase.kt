package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.languagecertificate.dto.request.LanguageCertificateRequest
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.LanguageCertificateService
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.service.RegionService
import team.msg.sms.domain.student.dto.request.SignUpData
import team.msg.sms.domain.student.exception.StuNumNotRightException
import team.msg.sms.domain.student.model.Department
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.TechStackService
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.UserService
import java.util.UUID

@UseCase
class SignUpUseCase(
    private val studentService: StudentService,
    private val userService: UserService,
    private val techStackService: TechStackService,
    private val regionService: RegionService,
    private val languageCertificateService: LanguageCertificateService,
    private val certificateService: CertificateService
) {
    fun execute(signUpData: SignUpData) {
        val user = userService.getCurrentUser()

        studentService.checkStudentExistsByUser(user)

        val signUpStudent = toStudentModel(signUpData, user)

        val student = studentService.saveStudent(signUpStudent, user)

        techStackService.saveAll(
            signUpData.techStack.map { toTechStackModel(techStack = it, studentId = student.id) },
            student,
            user
        )

        regionService.saveAll(signUpData.region.map { toRegionModel(it, studentId = student.id) }, student, user)

        languageCertificateService.saveAll(signUpData.languageCertificate.map {
            toLanguageCertificate(
                languageCertificate = it,
                studentId = student.id
            )
        }, student, user)

        certificateService.saveAll(
            signUpData.certificate.map { toCertificate(certificate = it, studentId = student.id) },
            student,
            user
        )
    }

    private fun toRegionModel(region: String, studentId: UUID): Region =
        Region(
            id = 0,
            region = region,
            studentId = studentId
        )

    private fun toTechStackModel(techStack: String, studentId: UUID): TechStack =
        TechStack(
            id = 0,
            stack = techStack,
            studentId = studentId,
        )


    private fun toCertificate(certificate: String, studentId: UUID): Certificate =
        Certificate(
            id = 0,
            certificateName = certificate,
            studentId = studentId
        )

    private fun toLanguageCertificate(
        languageCertificate: LanguageCertificateRequest,
        studentId: UUID
    ): LanguageCertificate =
        LanguageCertificate(
            id = 0,
            languageCertificateName = languageCertificate.languageCertificateName,
            score = languageCertificate.score,
            studentId = studentId
        )

    private fun toStudentModel(signUpData: SignUpData, user: User): Student =
        Student(
            id = UUID.randomUUID(),
            department = findDepartment(user.stuNum),
            contactEmail = signUpData.contactEmail,
            major = signUpData.major,
            portfolioUrl = signUpData.portfolioUrl,
            gsmAuthenticationScore = signUpData.gsmAuthenticationScore,
            salary = signUpData.salary,
            formOfEmployment = signUpData.formOfEmployment,
            introduce = signUpData.introduce,
            militaryService = signUpData.militaryService,
            profileImgUrl = signUpData.profileImgUrl,
            userId = user.id
        )

    private fun findDepartment(stuNum: String): Department {
        val departmentCode = stuNum.slice(IntRange(1, 1))
        return when {
            stuNum.startsWith("1") -> when (departmentCode) {
                "1", "2" -> Department.SW_DEVELOPMENT
                "3" -> Department.SMART_IOT_DEVELOPMENT
                "4" -> Department.AI_DEVELOPMENT
                else -> throw StuNumNotRightException
            }

            else -> when (departmentCode) {
                "1", "2" -> Department.SW_DEVELOPMENT
                "3", "4" -> Department.SMART_IOT_DEVELOPMENT
                else -> throw StuNumNotRightException
            }
        }
    }
}