package team.msg.sms.domain.project.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.project.exception.error.ProjectErrorCode

object ProjectLimitExceededException : SmsException(
    ProjectErrorCode.PROJECT_LIMIT_EXCEEDED_EXCEPTION
)