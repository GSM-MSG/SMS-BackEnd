package team.msg.sms.domain.student.dto.response

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.student.model.Department
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService

class DetailStudentInfoTeacherResponse(
    val name: String,
    val introduce: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: Department,
    val major: String,
    val profileImg: String,
    val contactEmail: String,
    val gsmAuthenticationScore: Int,
    val formOfEmployment: FormOfEmployment,
    val regions: List<String>, // 근무지역
    val militaryService: MilitaryService,
    val salary: Int,
    val languageCertificates: List<LanguageCertificate>,
    val certificates: List<String>,
    val techStack: List<String>
) {
}