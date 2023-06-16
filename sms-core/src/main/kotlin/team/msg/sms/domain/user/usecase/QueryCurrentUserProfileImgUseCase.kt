package team.msg.sms.domain.user.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.service.SecurityService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.dto.res.UserProfileResponseData

@UseCase
class QueryCurrentUserProfileImgUseCase(
    private val studentService: StudentService,
    private val securityService: SecurityService
) {
    fun execute(): UserProfileResponseData {
        val role = securityService.getCurrentUserRole()
        return UserProfileResponseData(
            profileImgUrl = when (role) {
                "ROLE_STUDENT" -> studentService.currentStudent().profileImgUrl
                else -> ""
            }
        )
    }
}