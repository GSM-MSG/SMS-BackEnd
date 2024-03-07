package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.dto.req.FiltersRequestData
import team.msg.sms.domain.authentication.model.Authentication.AuthenticationWithStudentInfoAndRequestedTime
import team.msg.sms.domain.authentication.service.FilterAuthenticationService
import team.msg.sms.domain.teacher.model.HomeroomTeacher

@Service
class FilterAuthenticationServiceImpl : FilterAuthenticationService {
    override fun filterAuthenticationsForTeacher(
        authentications: List<AuthenticationWithStudentInfoAndRequestedTime>,
        filters: FiltersRequestData
    ) : List<AuthenticationWithStudentInfoAndRequestedTime> {
        var filteredAuthentication = authentications

        filters.grade?.let { grade ->
            if (filters.grade.isNotEmpty())
                filteredAuthentication = filteredAuthentication.filter { authentication ->
                    authentication.stuNum.substring(0, 1).toInt() in grade
                }
        }

        filters.classNum?.let { classNum ->
            if (filters.classNum.isNotEmpty())
                filteredAuthentication = filteredAuthentication.filter { authentication ->
                    authentication.stuNum.substring(1, 2).toInt() in classNum
                }
        }

        filters.department?.let { departments ->
            if (filters.department.isNotEmpty())
                filteredAuthentication = filteredAuthentication.filter { authentication ->
                    authentication.department in departments
                }
        }

        return filteredAuthentication
    }

    override fun filterAuthenticationsForHomeroomTeacher(
        authentications: List<AuthenticationWithStudentInfoAndRequestedTime>,
        filters: FiltersRequestData,
        homeroomTeacher: HomeroomTeacher
    ) : List<AuthenticationWithStudentInfoAndRequestedTime> {
        var filteredAuthentication = authentications

        filteredAuthentication = filteredAuthentication.filter { authentication ->
            authentication.stuNum.substring(0, 1).toInt() == homeroomTeacher.grade
        }

        filteredAuthentication = filteredAuthentication.filter { authentication ->
            authentication.stuNum.substring(1, 2).toInt() == homeroomTeacher.classNum
        }

        return filteredAuthentication
    }
}