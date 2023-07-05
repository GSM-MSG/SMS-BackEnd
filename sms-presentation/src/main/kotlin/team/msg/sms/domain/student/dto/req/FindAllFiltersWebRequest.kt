package team.msg.sms.domain.student.dto.req

import org.springframework.web.bind.annotation.RequestParam

class FindAllFiltersWebRequest(
    @RequestParam("majors", required = false) val majors: List<String>?,
    @RequestParam("techStacks", required = false) val techStacks: List<String>?,
    @RequestParam("grade", required = false) val grade: List<Int>?,
    @RequestParam("classNum", required = false) val classNum: List<Int>?,
    @RequestParam("department", required = false) val department: List<String>?,
    @RequestParam("stuNumSort", required = false) val stuNumSort: String?,
    @RequestParam("formOfEmployment", required = false) val formOfEmployment: List<String>?,
    @RequestParam("minGsmAuthenticationScore", required = false) val minGsmAuthenticationScore: Int?,
    @RequestParam("maxGsmAuthenticationScore", required = false) val maxGsmAuthenticationScore: Int?,
    @RequestParam("minSalary", required = false) val minSalary: Int?,
    @RequestParam("maxSalary", required = false) val maxSalary: Int?,
    @RequestParam(
        "gsmAuthenticationScoreSort", required = false
    ) val gsmAuthenticationScoreSort: String?,
    @RequestParam("salarySort", required = false) val salarySort: String?
) {
    fun toData(): FiltersRequestData =
        FiltersRequestData(
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