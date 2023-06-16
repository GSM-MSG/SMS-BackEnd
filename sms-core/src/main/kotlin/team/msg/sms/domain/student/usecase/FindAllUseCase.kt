package team.msg.sms.domain.student.usecase

import org.springframework.cache.annotation.Cacheable
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.service.SecurityService
import team.msg.sms.domain.student.dto.request.FiltersData
import team.msg.sms.domain.student.dto.response.MainStudentsResponse
import team.msg.sms.domain.student.dto.response.StudentInfoListResponse
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.service.TechStackService
import javax.servlet.http.HttpServletRequest

@UseCase
class FindAllUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val securityService: SecurityService
) {
    @Transactional
    @Cacheable(value = ["StudentInfoListResponse"], key = "#root.target.generateCacheKey(#page, #size)", cacheManager = "contentCacheManager", unless = "#root.target.hasNonPageAndSizeParameters(#request)")
    fun execute(page: Int, size: Int, filtersData: FiltersData): StudentInfoListResponse {

        val studentsWithPageInfo = studentService.getStudentsWithPage(page, size) // 20
        val techStacks = techStackService.getAllTechStack() // 20
        val currentRole = securityService.getCurrentUserRole()

        val students = studentService.matchStudentWithTechStacks(studentsWithPageInfo.students, techStacks, currentRole)

        val filterStudents = studentService.filterStudents(students, filtersData, currentRole) // 160

        val studentsResponses = filterStudents.map {
            MainStudentsResponse(
                id = it.id,
                major = it.major,
                profileImg = it.profileImgUrl,
                name = it.name,
                techStack = it.techStack
            )
        }
        return StudentInfoListResponse(
            content = studentsResponses,
            page = studentsWithPageInfo.page,
            contentSize = filterStudents.size,
            totalSize = studentsWithPageInfo.totalSize,
            last = studentsWithPageInfo.last
        )
    }

    fun generateCacheKey(page: Int, size: Int): String {
        return "$page-$size"
    }

    fun hasNonPageAndSizeParameters(request: HttpServletRequest): Boolean {
        val queryString = request.queryString ?: return false
        val params = queryString.split("&").map { it.split("=")[0] }
        val nonPageAndSizeParams = params.filter { it != "page" && it != "size" }
        return nonPageAndSizeParams.isNotEmpty()
    }
}