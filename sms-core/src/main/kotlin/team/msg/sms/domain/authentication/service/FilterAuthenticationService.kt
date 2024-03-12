package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.dto.req.FiltersRequestData
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.teacher.model.HomeroomTeacher

interface FilterAuthenticationService {
    fun filterAuthenticationsForTeacher(
        authentications: List<Authentication.AuthenticationWithStudentInfoAndRequestedTime>,
        filters: FiltersRequestData
    ): List<Authentication.AuthenticationWithStudentInfoAndRequestedTime>

    fun filterAuthenticationsForHomeroomTeacher(
        authentications: List<Authentication.AuthenticationWithStudentInfoAndRequestedTime>,
        filters: FiltersRequestData,
        homeroomTeacher: HomeroomTeacher
    ): List<Authentication.AuthenticationWithStudentInfoAndRequestedTime>
}