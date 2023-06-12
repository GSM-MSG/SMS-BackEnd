package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.certificate.service.CertificateService
import team.msg.sms.domain.student.dto.response.DetailStudentInfoTeacherResponse
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class StudentInfoTeacherUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val certificateService: CertificateService
) {
    fun execute(uuid: String): DetailStudentInfoTeacherResponse {
        val student = studentService.getStudentByUuid(uuid)
        val techStacks: List<String> = techStackService.getTechStackByStudentUuid(student.id).map { it.stack }
        val certificates = certificateService.getCertificateByUuid(student.id).map { it.certificateName }


        return DetailStudentInfoTeacherResponse(
            name = student.name,
            introduce = student.introduce,
            grade = student.stuNum.substring(0, 1).toInt(),
            classNum = student.stuNum.substring(1, 2).toInt(),
            number = student.stuNum.substring(2, 4).toInt(),
            department = student.department,
            major = student.major,
            profileImg = student.profileImgUrl,
            techStack = techStacks,
            contactEmail = student.contactEmail,
            salary = student.salary,
            militaryService = student.militaryService,
            formOfEmployment = student.formOfEmployment,
            gsmAuthenticationScore = student.gsmAuthenticationScore,
            certificates = certificates,
            languageCertificates = ,
            regions =
        )
    }
}