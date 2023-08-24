package team.msg.sms.domain.file.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.file.exception.error.FileErrorCode

object ImageNotFoundException : SmsException(
    FileErrorCode.IMAGE_NOT_FOUND
)