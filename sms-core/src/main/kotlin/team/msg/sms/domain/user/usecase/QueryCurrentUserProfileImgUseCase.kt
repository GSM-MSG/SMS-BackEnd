package team.msg.sms.domain.user.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.service.SecurityService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.dto.UserProfileResponse

@UseCase
class QueryCurrentUserProfileImgUseCase(
    private val studentService: StudentService,
    private val securityService: SecurityService
) {
    fun execute(): UserProfileResponse {
        val role = securityService.getCurrentUserRole()
        return UserProfileResponse(
            profileImgUrl = when (role) {
                "STUDENT" -> studentService.currentStudent().profileImgUrl
                else -> ""
            }
        )
    }
}