package team.msg.sms.domain.auth.usecase

import gauth.GAuthUserInfo
import gauth.exception.GAuthException
import team.msg.sms.domain.auth.dto.req.SignInRequestData
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.dto.res.SignInResponseData
import team.msg.sms.common.spi.GAuthPort
import team.msg.sms.domain.auth.exception.ExpiredCodeException
import team.msg.sms.domain.auth.exception.SecretMismatchException
import team.msg.sms.domain.auth.exception.ServiceNotFoundException
import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.auth.spi.JwtPort
import team.msg.sms.domain.auth.spi.RefreshTokenPort
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.exception.InternalServerErrorException
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
    fun execute(request: SignInRequestData): SignInResponseData {
        try {
            val gAuthToken = gAuthPort.receiveGAuthToken(request.code)
            val gAuthUserInfo = gAuthPort.receiveUserInfo(gAuthToken.accessToken)

            val role = userService.getRoleByGAuthInfo(gAuthUserInfo.email, gAuthUserInfo.role)

            val isExistsUser = userService.checkUserExistByEmail(gAuthUserInfo.email)
            val user = userService.createUserWhenNotExistUser(
                isExistsUser,
                User(
                    name = gAuthUserInfo.name,
                    email = gAuthUserInfo.email,
                    stuNum = stuNumValid(role, gAuthUserInfo),
                    roles = mutableListOf(role)
                )
            )

            val (accessToken, accessTokenExp, refreshToken, refreshTokenExp) = jwtPort.receiveToken(user.id, role)

            refreshTokenPort.saveRefreshToken(RefreshToken(refreshToken, user.id))

            val isStudent = studentService.checkNewStudent(user, role.name)

            return SignInResponseData(
                accessToken = accessToken,
                accessTokenExp = accessTokenExp,
                refreshToken = refreshToken,
                refreshTokenExp = refreshTokenExp,
                role = role,
                isExist = isStudent
            )
        } catch (error: GAuthException) {
            when (error.code) {
                400 -> throw SecretMismatchException
                401 -> throw ExpiredCodeException
                404 -> throw ServiceNotFoundException
                else -> throw InternalServerErrorException
            }
        }
    }
}

private fun stuNumValid(role: Role, gAuthUserInfo: GAuthUserInfo) =
    if (role.name != "ROLE_TEACHER") "${gAuthUserInfo.grade}${gAuthUserInfo.classNum}" + if (gAuthUserInfo.num < 10) {
        "0${gAuthUserInfo.num}"
    } else gAuthUserInfo.num else ""