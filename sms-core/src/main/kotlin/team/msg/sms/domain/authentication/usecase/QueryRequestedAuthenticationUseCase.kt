package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.authentication.dto.req.FiltersRequestData
import team.msg.sms.domain.authentication.dto.res.QueryRequestedAuthenticationListResponseData
import team.msg.sms.domain.authentication.dto.res.RequestedAuthenticationResponseData
import team.msg.sms.domain.authentication.exception.PermissionRoleDeniedException
import team.msg.sms.domain.authentication.service.AuthenticationService
import team.msg.sms.domain.teacher.service.HomeroomTeacherService
import team.msg.sms.domain.user.service.UserService
import team.msg.sms.domain.authentication.model.Authentication.AuthenticationWithPageInfo as AuthenticationWithPageInfo
import team.msg.sms.domain.authentication.model.Authentication.AuthenticationWithStudentInfoAndRequestedTime as AuthenticationWithStudentInfoAndRequestedTime

@UseCase
class QueryRequestedAuthenticationUseCase(
    private val authenticationService: AuthenticationService,
    private val homeroomTeacherService: HomeroomTeacherService,
    private val userService: UserService
) {
    @Transactional(readOnly = true)
    fun execute(
        page: Int, size: Int,
        filterRequestData: FiltersRequestData
    ): QueryRequestedAuthenticationListResponseData {
        val user = userService.getCurrentUser()
        val authentications = authenticationService.getRequestedAuthentications()

        val filteredAuthentications = when {
            Role.ROLE_HOMEROOM in user.roles -> homeroomTeacherService.getHomeroomTeacherByUserId(user.id)
                .let { authenticationService.filterAuthenticationsForHomeroomTeacher(authentications, filterRequestData, it) }
            Role.ROLE_DIRECTOR in user.roles ||
            Role.ROLE_PRINCIPAL in user.roles ||
            Role.ROLE_DEPUTY_PRINCIPAL in user.roles -> {
                authenticationService.filterAuthenticationsForTeacher(authentications, filterRequestData)
            }
            else -> throw PermissionRoleDeniedException
        }

        val authenticationPage = filteredAuthentications.toDomainPageWithUserInfo(page, size)

        return QueryRequestedAuthenticationListResponseData(
            content = authenticationPage.authentications.toRequestedAuthenticationResponseData(),
            page = authenticationPage.page,
            contentSize = authenticationPage.contentSize,
            totalSize = authenticationPage.totalSize,
            last = authenticationPage.last
        )
    }

    private fun List<AuthenticationWithStudentInfoAndRequestedTime>.toDomainPageWithUserInfo(page: Int, size: Int): AuthenticationWithPageInfo {
        val startIndex = (page - 1) * size
        val endIndex = (startIndex + size).coerceAtMost(this.size)
        val content = if (startIndex <= endIndex) this.subList(startIndex, endIndex) else emptyList()

        val totalPages = (this.size + size - 1) / size
        val isLast = page >= totalPages

        return AuthenticationWithPageInfo(
            authentications = content,
            page = page,
            contentSize = content.size,
            totalSize = this.size.toLong(),
            last = isLast
        )
    }

    fun List<AuthenticationWithStudentInfoAndRequestedTime>.toRequestedAuthenticationResponseData(): List<RequestedAuthenticationResponseData> =
        map {
            RequestedAuthenticationResponseData(
                id = it.id,
                requestedTime = it.requestedTime.toLocalDate(),
                title = it.title,
                stuNum = it.stuNum,
                name = it.name,
                department = it.department
            )
        }


}