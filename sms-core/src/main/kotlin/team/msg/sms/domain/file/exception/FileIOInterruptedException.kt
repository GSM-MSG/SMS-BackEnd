package team.msg.sms.domain.file.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.file.exception.error.FileErrorCode

object FileIOInterruptedException : SmsException(
    FileErrorCode.IO_INTERRUPTED
)