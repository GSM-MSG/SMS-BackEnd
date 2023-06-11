package team.msg.sms.domain.student.dto.request

data class FiltersData(
    val majors: List<String>?,
    val techStacks: List<String>?,
    val grade: Int?,
    val classNum: Int?,
    val department: List<String>?,
    val formOfEmployment: List<String>?,
    val minGsmAuthenticationScore: Int?,
    val maxGsmAuthenticationScore: Int?,
    val minSalary: Int?,
    val maxSalary: Int?,
    val stuNumSort: String?,
    val gsmAuthenticationScoreSort: String?,
    val salarySort: String?
) {
}