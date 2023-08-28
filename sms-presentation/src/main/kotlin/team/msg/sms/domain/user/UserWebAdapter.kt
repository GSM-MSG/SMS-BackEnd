package team.msg.sms.domain.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.user.dto.res.UserProfileDetailResponseData
import team.msg.sms.domain.user.dto.res.UserProfileDetailWebResponse
import team.msg.sms.domain.user.dto.res.UserProfileImgResponseData
import team.msg.sms.domain.user.dto.res.UserProfileWebResponse
import team.msg.sms.domain.user.usecase.QueryCurrentUserProfileDetailUseCase
import team.msg.sms.domain.user.usecase.QueryCurrentUserProfileImgUseCase

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryCurrentUserProfileImgUseCase: QueryCurrentUserProfileImgUseCase,
    private val queryCurrentUserProfileDetailUseCase: QueryCurrentUserProfileDetailUseCase
) {
    @GetMapping("/profile/img")
    fun getUserProfileImg(
    ): ResponseEntity<UserProfileWebResponse> =
        queryCurrentUserProfileImgUseCase.execute()
            .let { ResponseEntity.ok(it.toResponse()) }

    @GetMapping("/profile")
    fun getUserProfileDetail(): ResponseEntity<UserProfileDetailWebResponse> =
        queryCurrentUserProfileDetailUseCase.execute()
            .let { ResponseEntity.ok(it.toResponse()) }

    private fun UserProfileDetailResponseData.toResponse(): UserProfileDetailWebResponse =
        UserProfileDetailWebResponse(
            name = this.name,
            introduce = this.introduce,
            portfolioUrl = this.portfolioUrl,
            grade = this.grade,
            classNum = this.classNum,
            number = this.number,
            department = this.department,
            major = this.major,
            profileImgUrl = this.profileImg,
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

    private fun UserProfileImgResponseData.toResponse(): UserProfileWebResponse =
        UserProfileWebResponse(
            profileImgUrl = this.profileImgUrl
        )
}