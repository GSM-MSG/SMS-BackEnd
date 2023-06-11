package team.msg.sms.domain.student.dto

import org.springframework.web.bind.annotation.RequestParam
import team.msg.sms.domain.student.dto.request.FiltersData

class FindAllFiltersWebRequest(
    @RequestParam("majors", required = false, defaultValue = "") val majors: List<String>?,
    @RequestParam("techStacks", required = false, defaultValue = "") val techStacks: List<String>?,
    @RequestParam("grade", required = false, defaultValue = "null") val grade: Int?,
    @RequestParam("classNum", required = false, defaultValue = "null") val classNum: Int?,
    @RequestParam("department", required = false, defaultValue = "") val department: List<String>?,
    @RequestParam("stuNumSort", required = false, defaultValue = "") val stuNumSort: String?,
    @RequestParam("formOfEmployment", required = false, defaultValue = "") val formOfEmployment: List<String>?,
    @RequestParam("minGsmAuthenticationScore", required = false, defaultValue = "null") val minGsmAuthenticationScore: Int?,
    @RequestParam("maxGsmAuthenticationScore", required = false, defaultValue = "null") val maxGsmAuthenticationScore: Int?,
    @RequestParam("minSalary", required = false, defaultValue = "null") val minSalary: Int?,
    @RequestParam("maxSalary", required = false, defaultValue = "null") val maxSalary: Int?,
    @RequestParam(
        "gsmAuthenticationScoreSort", required = false, defaultValue = ""
    ) val gsmAuthenticationScoreSort: String?,
    @RequestParam("salarySort", required = false, defaultValue = "") val salarySort: String?
) {
    fun toData(): FiltersData =
        FiltersData(
            majors = majors,
            techStacks = techStacks,
            grade = grade,
            classNum = classNum ,
            department = department,
            stuNumSort = stuNumSort,
            formOfEmployment = formOfEmployment,
            minGsmAuthenticationScore = minGsmAuthenticationScore,
            maxGsmAuthenticationScore = maxGsmAuthenticationScore,
            minSalary = minSalary,
            maxSalary = maxSalary,
            gsmAuthenticationScoreSort = gsmAuthenticationScoreSort,
            salarySort = salarySort
    )
}