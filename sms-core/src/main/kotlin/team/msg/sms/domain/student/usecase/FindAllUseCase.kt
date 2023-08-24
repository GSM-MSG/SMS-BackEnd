package team.msg.sms.domain.student.usecase

import org.springframework.cache.annotation.Cacheable
import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.service.SecurityService
import team.msg.sms.domain.student.dto.req.FiltersRequestData
import team.msg.sms.domain.student.dto.res.MainStudentsResponseData
import team.msg.sms.domain.student.dto.res.StudentInfoListResponseData
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.service.StudentTechStackService
import team.msg.sms.domain.techstack.service.TechStackService

@UseCase
class FindAllUseCase(
    private val studentService: StudentService,
    private val techStackService: TechStackService,
    private val studentTechStackservice: StudentTechStackService,
    private val securityService: SecurityService
) {
    @Transactional
    @Cacheable(
        value = ["StudentInfoListResponseData"],
        key = "#root.target.generateCacheKey(#page, #size)",
        cacheManager = "contentCacheManager",
    )
    fun execute(page: Int, size: Int, filtersData: FiltersRequestData): StudentInfoListResponseData {
        val students = studentService.getStudents()
        val techStacks = techStackService.getAllTechStack()
        val currentRole = securityService.getCurrentUserRole()
        val studentTechStacks = studentTechStackservice.getStudentTechStack()

        val studentsWithUserInfo =
            studentService.matchStudentWithTechStacks(students, techStacks, studentTechStacks, currentRole)

        val filterStudents = studentService.filterStudents(studentsWithUserInfo, filtersData, currentRole)

        val studentPage = filterStudents.toDomainPageWithUserInfo(page, size)

        return StudentInfoListResponseData(
            content = studentPage.students.toMainStudentsResponseData(),
            page = studentPage.page,
            contentSize = studentPage.contentSize,
            totalSize = studentPage.totalSize,
            last = studentPage.last
        )
    }
}

fun generateCacheKey(page: Int, size: Int): String {
    return "$page-$size"
}



fun List<Student.StudentWithUserInfo>.toDomainPageWithUserInfo(page: Int, size: Int): Student.StudentWithPageInfo {
    val startIndex = (page - 1) * size
    val endIndex = (startIndex + size).coerceAtMost(this.size)
    val content = if (startIndex <= endIndex) this.subList(startIndex, endIndex) else emptyList()

    val totalPages = (this.size + size - 1) / size
    val isLast = page >= totalPages

    return Student.StudentWithPageInfo(
        students = content,
        page = page,
        contentSize = content.size,
        totalSize = this.size.toLong(),
        last = isLast
    )
}

fun List<Student.StudentWithUserInfo>.toMainStudentsResponseData(): List<MainStudentsResponseData> =
    this.map {
        MainStudentsResponseData(
            id = it.id,
            major = it.major,
            profileImg = it.profileImgUrl,
            name = it.name,
            techStacks = it.techStack
        )
    }
