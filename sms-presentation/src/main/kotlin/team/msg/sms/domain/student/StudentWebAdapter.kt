package team.msg.sms.domain.student

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import team.msg.sms.common.exception.InvalidUuidException
import team.msg.sms.common.extension.toFile
import team.msg.sms.domain.student.dto.req.CreateStudentLinkWebRequest
import team.msg.sms.domain.student.dto.req.FindAllFiltersWebRequest
import team.msg.sms.domain.student.dto.req.ModifyStudentInfoWebRequest
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
    private val studentInfoTeacherUseCase: StudentInfoTeacherUseCase,
    private val studentInfoTokenUseCase: StudentInfoTokenUseCase,
    private val modifyStudentInfoUseCase: ModifyStudentInfoUseCase,
    private val createStudentLinkUseCase: CreateStudentLinkUseCase,
    private val modifyStudentPortfolioFileUseCase: ModifyStudentPortfolioFileUseCase
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

    @PostMapping("/link")
    fun createStudentLink(@RequestBody @Valid createStudentLinkWebRequest: CreateStudentLinkWebRequest): ResponseEntity<CreateStudentLinkWebResponse> {
        return createStudentLinkUseCase.execute(createStudentLinkWebRequest.toData())
            .let { ResponseEntity.ok(it.toResponse()) }
    }

    @GetMapping("/link")
    fun findByToken(@RequestParam token: String): ResponseEntity<DetailStudentInfoTokenWebResponse> =
        studentInfoTokenUseCase.execute(token)
            .let { ResponseEntity.ok(it.toResponse()) }

    @PutMapping
    fun modifyStudentInfo(@RequestBody modifyStudentInfoWebRequest: ModifyStudentInfoWebRequest) {
        modifyStudentInfoUseCase.execute(modifyStudentInfoWebRequest.toData())
    }

    @PutMapping("/pdf")
    fun modifyStudentPortfolioFile(@RequestPart(name = "file", required = false) portfolioFile: MultipartFile?) {
        modifyStudentPortfolioFileUseCase.execute(portfolioFile?.toFile())
    }

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
            contactEmail = this.contactEmail,
            profileImgUrl = this.profileImg,
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
            contactEmail = this.contactEmail,
            department = this.department,
            major = this.major,
            profileImgUrl = this.profileImg,
            profileImg = this.profileImg,
            techStacks = this.techStacks,
            projects = this.projects,
            prizes = this.prizes
        )

    fun DetailStudentInfoTeacherResponseData.toResponse(): DetailStudentInfoTeacherWebResponse =
        DetailStudentInfoTeacherWebResponse(
            name = this.name,
            introduce = this.introduce,
            portfolioUrl = this.portfolioUrl,
            grade = this.grade,
            classNum = this.classNum,
            number = this.number,
            department = this.department,
            major = this.major,
            profileImgUrl = this.profileImg,
            profileImg = this.profileImg,
            contactEmail = this.contactEmail,
            gsmAuthenticationScore = this.gsmAuthenticationScore,
            formOfEmployment = this.formOfEmployment,
            regions = this.regions,
            militaryService = this.militaryService,
            salary = this.salary,
            languageCertificates = this.languageCertificates,
            certificates = this.certificates,
            techStacks = this.techStacks,
            projects = this.projects,
            prizes = this.prizes
        )

    fun CreateStudentLinkResponseData.toResponse(): CreateStudentLinkWebResponse =
        CreateStudentLinkWebResponse(
            token = this.token
        )

    fun DetailStudentInfoTokenResponseData.toResponse(): DetailStudentInfoTokenWebResponse =
        DetailStudentInfoTokenWebResponse(
            name = this.name,
            introduce = this.introduce,
            portfolioUrl = this.portfolioUrl,
            grade = this.grade,
            classNum = this.classNum,
            number = this.number,
            department = this.department,
            major = this.major,
            profileImgUrl = this.profileImg,
            profileImg = this.profileImg,
            contactEmail = this.contactEmail,
            gsmAuthenticationScore = this.gsmAuthenticationScore,
            formOfEmployment = this.formOfEmployment,
            regions = this.regions,
            militaryService = this.militaryService,
            salary = this.salary,
            languageCertificates = this.languageCertificates,
            certificates = this.certificates,
            techStacks = this.techStacks,
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
