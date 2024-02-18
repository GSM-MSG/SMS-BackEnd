package team.msg.sms.domain.auth.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.dto.res.VerifyAccessResponseData
import team.msg.sms.domain.user.service.UserService

@UseCase
class VerifyAccessUseCase(
    private val userService: UserService
) {
    /**
     * Role에 대한 반환 타입이 list가 아닌 단일 string이라 슬래시로 구분해두었습니다.
     * 추후 response를 변경하게 되면 바꿔도 괜찮을 거 같아요!
     */
    fun execute(): VerifyAccessResponseData =
        userService.getCurrentUser()
            .let {
                VerifyAccessResponseData(
                    userService.checkNewUser(it),
                    it.roles.joinToString("/")
                )
            }
}