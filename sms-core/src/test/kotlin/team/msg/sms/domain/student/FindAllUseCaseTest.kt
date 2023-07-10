package team.msg.sms.domain.student

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import team.msg.sms.common.service.SecurityService
import team.msg.sms.common.spi.SecurityPort
import team.msg.sms.domain.student.dto.req.FiltersRequestData
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.student.spi.QueryStudentPort
import team.msg.sms.domain.student.usecase.FindAllUseCase
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.techstack.service.TechStackService
import team.msg.sms.domain.techstack.spi.QueryTechStackPort

class FindAllUseCaseTest : BehaviorSpec({
    val queryStudentPort = mockk<QueryStudentPort>()
    val queryTechStackPort = mockk<QueryTechStackPort>()
    val securityPort = mockk<SecurityPort>()

    val studentService = mockk<StudentService>()
    val techStackService = mockk<TechStackService>()
    val securityService = mockk<SecurityService>()

    val findAllUseCase = FindAllUseCase(studentService, techStackService, securityService)

    val studentStub = StudentStub()

    val student = studentStub.generatedStudent()

    val techStack = arrayListOf<TechStack>()
    techStack.add(TechStack(1, "Compose", student.id))
    techStack.add(TechStack(2, "Retrofit", student.id))
    techStack.add(TechStack(3, "Kotlin", student.id))
    techStack.add(TechStack(4, "Coroutine", student.id))

    val page = 1
    val size = 20

    val filterData = FiltersRequestData(
        majors = arrayListOf("Frontend"),
        techStacks = null,
        grade = null,
        classNum = null,
        department = null,
        formOfEmployment = null,
        minGsmAuthenticationScore = null,
        maxGsmAuthenticationScore = null,
        minSalary = null,
        maxSalary = null,
        stuNumSort = null,
        gsmAuthenticationScoreSort = null,
        salarySort = null
    )
    given("전공 분야인 Frontend만 필터링 하는 조건이 주어 졌을 때") {
        val studentsWithPageInfo = studentStub.generatedStudentWithPageInfo()

        every { studentService.getStudentsWithPage(page, size) } returns studentsWithPageInfo
        every { techStackService.getAllTechStack() } returns techStack
        every { securityService.getCurrentUserRole() } returns "ROLE_STUDENT"

        `when`("FindAllUseCase의 execute 메서드를 호출하면") {
            val responseData = findAllUseCase.execute(page, size, filterData)

            then("context 객체를 반환해야한다") {
                responseData.content shouldNotBe null
            }
        }
    }
})