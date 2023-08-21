package team.msg.sms.domain.student

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.common.exception.InvalidUuidException
import team.msg.sms.domain.student.dto.req.FindAllFiltersWebRequest
import team.msg.sms.domain.student.dto.req.SignUpWebRequest
import team.msg.sms.domain.student.dto.res.*
import team.msg.sms.domain.student.usecase.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/student")
class StudentWebAdapter(
    private val signUpUseCase: SignUpUseCase,
    private val findAllUseCase: FindAllUseCase,
    private val studentInfoAnonymousUseCase: StudentInfoAnonymousUseCase,
    private val studentInfoDetailUseCase: StudentInfoDetailUseCase,
    private val studentInfoTeacherUseCase: StudentInfoTeacherUseCase
) {
    @GetMapping
    fun findAll(
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int,
        filterRequest: FindAllFiltersWebRequest
    ): ResponseEntity<StudentInfoListWebResponse> =
        findAllUseCase.execute(page, size, filterRequest.toData())
            .let { ResponseEntity.ok(it.toResponse()) }

    @PostMapping
    fun signUpStudent(@RequestBody @Valid signUpWebRequest: SignUpWebRequest): ResponseEntity<Void> =
        signUpUseCase.execute(signUpWebRequest.toData())
            .run { ResponseEntity.ok().build() }

    @GetMapping("/anonymous/{uuid}")
    fun findForAnonymousRole(@PathVariable uuid: String): ResponseEntity<DetailStudentInfoAnonymousWebResponse> {
        if (!isValidUUID(uuid)) throw InvalidUuidException
        return studentInfoAnonymousUseCase.execute(uuid)
            .let { ResponseEntity.ok(it.toResponse()) }
    }

    @GetMapping("/{uuid}")
    fun findForStudentRole(@PathVariable uuid: String): ResponseEntity<DetailStudentInfoWebResponse> {
        if (!isValidUUID(uuid)) throw InvalidUuidException
        return studentInfoDetailUseCase.execute(uuid)
            .let { ResponseEntity.ok(it.toResponse()) }
    }

    @GetMapping("/teacher/{uuid}")
    fun findForTeacherRole(@PathVariable uuid: String): ResponseEntity<DetailStudentInfoTeacherWebResponse> {
        if (!isValidUUID(uuid)) throw InvalidUuidException
        return studentInfoTeacherUseCase.execute(uuid)
            .let { ResponseEntity.ok(it.toResponse()) }
    }

    private fun StudentInfoListResponseData.toResponse(): StudentInfoListWebResponse =
        StudentInfoListWebResponse(
            content = this.content,
            page = this.page,
            totalSize = this.totalSize,
            contentSize = this.contentSize,
            last = this.last
        )

    fun DetailStudentInfoAnonymousResponseData.toResponse(): DetailStudentInfoAnonymousWebResponse =
        DetailStudentInfoAnonymousWebResponse(
            name = this.name,
            introduce = this.introduce,
            major = this.major,
            profileImg = this.profileImg,
            techStacks = this.techStacks,
            projects = this.projects,
            prizes = this.prizes
        )

    fun DetailStudentInfoResponseData.toResponse(): DetailStudentInfoWebResponse =
        DetailStudentInfoWebResponse(
            name = this.name,
            introduce = this.introduce,
            grade = this.grade,
            classNum = this.classNum,
            number = this.number,
            department = this.department,
            major = this.major,
            profileImg = this.profileImg,
            techStacks = this.techStacks,
            projects = this.projects,
            prizes = this.prizes
        )

    fun DetailStudentInfoTeacherResponseData.toResponse(): DetailStudentInfoTeacherWebResponse =
        DetailStudentInfoTeacherWebResponse(
            name = this.name,
            introduce = this.introduce,
            grade = this.grade,
            classNum = this.classNum,
            number = this.number,
            department = this.department,
            major = this.major,
            profileImg = this.profileImg,
            contactEmail = this.contactEmail,
            techStacks = this.techStacks,
            formOfEmployment = this.formOfEmployment,
            portfolioUrl = this.portfolioUrl,
            certificates = this.certificates,
            militaryService = this.militaryService,
            gsmAuthenticationScore = this.gsmAuthenticationScore,
            salary = this.salary,
            languageCertificates = this.languageCertificates,
            regions = this.regions,
            projects = this.projects,
            prizes = this.prizes
        )

    private fun isValidUUID(uuid: String): Boolean {
        return try {
            UUID.fromString(uuid)
            true
        } catch (ex: IllegalArgumentException) {
            false
        }
    }
}