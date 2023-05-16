package team.msg.sms.domain.auth.usecase

import team.msg.sms.domain.auth.dto.request.SignInData
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.dto.response.SignInResponse
import team.msg.sms.common.spi.GAuthPort
import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.domain.auth.spi.JwtPort
import team.msg.sms.domain.auth.spi.RefreshTokenPort
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.UserService

@UseCase
class SignInUseCase(
    private val gAuthPort: GAuthPort,
    private val jwtPort: JwtPort,
    private val refreshTokenPort: RefreshTokenPort,
    private val userService: UserService,
    private val studentService: StudentService
) {
    fun execute(request: SignInData): SignInResponse {
        val gAuthToken = gAuthPort.receiveGAuthToken(request.code)
        val gAuthUserInfo = gAuthPort.receiveUserInfo(gAuthToken.accessToken)

        val role = userService.getRoleByGAuthInfo(gAuthUserInfo.email, gAuthUserInfo.role)

        val isExistsUser = userService.checkUserExistByEmail(gAuthUserInfo.email)
        val user = userService.createUserWhenNotExistUser(
            isExistsUser,
            User(
                name = gAuthUserInfo.name,
                email = gAuthUserInfo.email,
                roles = mutableListOf(role)
            )
        )
        val (accessToken, accessTokenExp, refreshToken, refreshTokenExp) = jwtPort.receiveToken(user.id, role)

        refreshTokenPort.saveRefreshToken(RefreshToken(refreshToken, user.id))

        val isStudent = studentService.checkNewStudent(user, role.name)

        return SignInResponse(
            accessToken = accessToken,
            accessTokenExp = accessTokenExp,
            refreshToken = refreshToken,
            refreshTokenExp = refreshTokenExp,
            isExist = isStudent
        )
    }
}