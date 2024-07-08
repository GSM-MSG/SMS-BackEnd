package team.msg.sms.domain.authentication.service

import java.util.UUID

interface GetAuthenticationFormService {
    fun getActiveAuthenticationFormId(): UUID
}