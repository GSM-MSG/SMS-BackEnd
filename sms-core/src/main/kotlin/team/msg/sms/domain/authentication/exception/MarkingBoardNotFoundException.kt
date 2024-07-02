package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object MarkingBoardNotFoundException : SmsException(
    AuthenticationErrorCode.MARKING_BOARD_NOT_FOUND
)
