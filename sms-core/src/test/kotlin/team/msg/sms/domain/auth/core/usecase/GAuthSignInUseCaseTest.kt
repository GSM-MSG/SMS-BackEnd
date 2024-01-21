package team.msg.sms.domain.auth.core.usecase

import gauth.GAuthToken
import gauth.GAuthUserInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import team.msg.sms.common.spi.GAuthPort
import team.msg.sms.domain.auth.dto.req.SignInRequestData
import team.msg.sms.domain.auth.dto.res.SignInResponseData
import team.msg.sms.domain.auth.dto.res.TokenResponseData
import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.auth.spi.JwtPort
import team.msg.sms.domain.auth.spi.RefreshTokenPort
import team.msg.sms.domain.auth.usecase.SignInUseCase
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.UserService
import team.msg.sms.global.annotation.SmsTest
import java.time.LocalDateTime
import java.util.UUID

@SmsTest
class GAuthSignInUseCaseTest {
    @Mock
    private lateinit var gAuthPort: GAuthPort

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var jwtPort: JwtPort

    @Mock
    private lateinit var refreshTokenPort: RefreshTokenPort

    @Mock
    private lateinit var studentService: StudentService

    @Mock
    private lateinit var teacherService: TeacherService

    private lateinit var signInUseCase: SignInUseCase

    private val email = "test@gsm.hs.kr"
    private val name = "test"
    private val grade = 2
    private val classNum = 4
    private val number = 6
    private val role = Role.ROLE_STUDENT

    private val code = "test_gauth_code"

    private val accessTokenExp = LocalDateTime.now()
    private val refreshTokenExp = LocalDateTime.now()

    private val saveUserStub: User by lazy {
        User(
            id = UUID.randomUUID(),
            email = email,
            name = name,
            stuNum = "$grade$classNum+0+$number",
            roles = mutableListOf(role)
        )
    }

    private val requestStub: SignInRequestData by lazy {
        SignInRequestData(code)
    }

    private val tokenResponseStub: TokenResponseData by lazy {
        TokenResponseData(
            accessToken = "access_token",
            refreshToken = "refreshToken",
            accessTokenExp = accessTokenExp,
            refreshTokenExp = refreshTokenExp,
        )
    }

    private val responseStub: SignInResponseData by lazy {
        SignInResponseData(
            accessToken = "access_token",
            refreshToken = "refreshToken",
            accessTokenExp =accessTokenExp,
            refreshTokenExp = refreshTokenExp,
            isExist = false,
            role = role
        )
    }

    private val refreshTokenStub: RefreshToken by lazy {
        RefreshToken(
            userId = saveUserStub.id,
            token = "refreshToken"
        )
    }

    private val gAuthTokenStub: GAuthToken by lazy {
        GAuthToken(
            mapOf(
                "accessToken" to "gauth_access_token",
                "refreshToken" to "gauth_refresh_token"
            )
        )
    }
    private val gAuthUserInfoStub: GAuthUserInfo by lazy {
        GAuthUserInfo(
            mapOf(
                "email" to email,
                "name" to name,
                "grade" to grade,
                "classNum" to classNum,
                "num" to number,
                "gender" to "MALE",
                "profileUrl" to "profileImageUrl",
                "role" to "ROLE_STUDENT"
            )
        )
    }

    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)

        signInUseCase = SignInUseCase(
            gAuthPort = gAuthPort,
            jwtPort = jwtPort,
            refreshTokenPort = refreshTokenPort,
            userService = userService,
            studentService =  studentService,
            teacherService = teacherService
        )
    }

    @Test
    fun `회원가입 성공`() {

        // given
        given(gAuthPort.receiveGAuthToken(requestStub.code))
            .willReturn(gAuthTokenStub)

        given(gAuthPort.receiveUserInfo(gAuthTokenStub.accessToken))
            .willReturn(gAuthUserInfoStub)

        given(userService.getRoleByGAuthInfo(gAuthUserInfoStub.email, gAuthUserInfoStub.role))
            .willReturn(role)

        given(userService.checkUserExistByEmail(gAuthUserInfoStub.email))
            .willReturn(false)

        given(userService.createUserWhenNotExistUser(any(), any()))
            .willReturn(saveUserStub)

        given(jwtPort.receiveToken(saveUserStub.id, saveUserStub.roles.first()))
            .willReturn(tokenResponseStub)

        given(refreshTokenPort.saveRefreshToken(any()))
            .willReturn(refreshTokenStub)

        given(studentService.checkNewStudent(saveUserStub))
            .willReturn(false)

        // when
        val result = signInUseCase.execute(requestStub)

        // then
        assertThat(result).isEqualTo(responseStub)
    }
}