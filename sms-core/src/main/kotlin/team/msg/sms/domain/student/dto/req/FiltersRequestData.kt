package team.msg.sms.domain.student.dto.req

data class FiltersRequestData(
    val majors: List<String>?,
    val techStacks: List<String>?,
    val grade: List<Int>?,
    val classNum: List<Int>?,
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