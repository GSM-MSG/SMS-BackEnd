package team.msg.sms.domain.techstack

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.user.dto.req.UserProfileWebResponse
import team.msg.sms.domain.user.dto.res.UserProfileResponseData
import team.msg.sms.domain.user.usecase.QueryCurrentUserProfileImgUseCase

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val queryCurrentUserProfileImgUseCase: QueryCurrentUserProfileImgUseCase
) {
    @GetMapping("/profile")
    fun getUserProfile(
    ): ResponseEntity<UserProfileWebResponse> =
        queryCurrentUserProfileImgUseCase.execute()
            .let { ResponseEntity.ok(it.toResponse()) }

    private fun UserProfileResponseData.toResponse(): UserProfileWebResponse =
        UserProfileWebResponse(
            profileImgUrl = this.profileImgUrl
        )
}