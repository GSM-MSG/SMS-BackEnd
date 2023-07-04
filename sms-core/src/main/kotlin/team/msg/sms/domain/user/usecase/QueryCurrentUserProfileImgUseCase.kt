package team.msg.sms.domain.user.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.service.SecurityService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.dto.res.UserProfileImgResponseData

@UseCase
class QueryCurrentUserProfileImgUseCase(
    private val studentService: StudentService,
    private val securityService: SecurityService
) {
    fun execute(): UserProfileImgResponseData {
        val role = securityService.getCurrentUserRole()
        return UserProfileImgResponseData(
            profileImgUrl = when (role) {
                "ROLE_STUDENT" -> studentService.currentStudent().profileImgUrl
                else -> ""
            }
        )
    }
}