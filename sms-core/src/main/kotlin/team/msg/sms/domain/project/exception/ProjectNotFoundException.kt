package team.msg.sms.domain.project.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.project.exception.error.ProjectErrorCode

object ProjectNotFoundException : SmsException(
    ProjectErrorCode.PROJECT_NOT_FOUND
)