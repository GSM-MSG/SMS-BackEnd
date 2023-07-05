package team.msg.sms.domain.student.usecase

import org.springframework.cache.annotation.Cacheable
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.service.SecurityService
import team.msg.sms.domain.student.dto.req.FiltersRequestData
import team.msg.sms.domain.student.dto.res.MainStudentsResponseData
import team.msg.sms.domain.student.dto.res.StudentInfoListResponseData
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class FindAllUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val securityService: SecurityService
) {
    @Transactional
    @Cacheable(value = ["StudentInfoListResponse"], key = "#root.target.generateCacheKey(#page, #size)", cacheManager = "contentCacheManager", unless = "#root.target.isCurrentAnonymous()")
    fun execute(page: Int, size: Int, filtersData: FiltersRequestData): StudentInfoListResponseData {
        val studentsWithPageInfo = studentService.getStudentsWithPage(page, size)
        val techStacks = techStackService.getAllTechStack()
        val currentRole = securityService.getCurrentUserRole()

        val students = studentService.matchStudentWithTechStacks(studentsWithPageInfo.students, techStacks, currentRole)

        val filterStudents = studentService.filterStudents(students, filtersData, currentRole)

        val studentsResponses = filterStudents.map {
            MainStudentsResponseData(
                id = it.id,
                major = it.major,
                profileImg = it.profileImgUrl,
                name = it.name,
                techStack = it.techStack
            )
        }
        return StudentInfoListResponseData(
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

    fun isCurrentAnonymous(): Boolean =
        securityService.isAnonymous()
}