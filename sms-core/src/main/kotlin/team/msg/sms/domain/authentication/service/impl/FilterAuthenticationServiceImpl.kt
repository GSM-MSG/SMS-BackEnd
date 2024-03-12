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
        var filteredAuthentications = authentications

        filters.grade?.let { grade ->
            if (filters.grade.isNotEmpty())
                filteredAuthentications = filteredAuthentications.filter { authentication ->
                    authentication.stuNum.substring(0, 1).toInt() in grade
                }
        }

        filters.classNum?.let { classNum ->
            if (filters.classNum.isNotEmpty())
                filteredAuthentications = filteredAuthentications.filter { authentication ->
                    authentication.stuNum.substring(1, 2).toInt() in classNum
                }
        }

        filters.department?.let { departments ->
            if (filters.department.isNotEmpty())
                filteredAuthentications = filteredAuthentications.filter { authentication ->
                    authentication.department in departments
                }
        }

        filters.stuNumSort?.let { stuNumSort ->
            filteredAuthentications =
                if (stuNumSort == "ASCENDING") filteredAuthentications.sortedBy { it.stuNum }
                else filteredAuthentications.sortedBy { it.stuNum }.reversed()
        }

        filters.requestTimeSort?.let { requestTimeSort ->
            filteredAuthentications =
                if (requestTimeSort == "ASCENDING") filteredAuthentications.sortedBy { it.requestedTime }
                else filteredAuthentications.sortedBy { it.requestedTime }.reversed()
        }

        return filteredAuthentications
    }

    override fun filterAuthenticationsForHomeroomTeacher(
        authentications: List<AuthenticationWithStudentInfoAndRequestedTime>,
        filters: FiltersRequestData,
        homeroomTeacher: HomeroomTeacher
    ) : List<AuthenticationWithStudentInfoAndRequestedTime> {
        var filteredAuthentications = authentications

        filteredAuthentications = filteredAuthentications.filter { authentication ->
            authentication.stuNum.substring(0, 1).toInt() == homeroomTeacher.grade
        }

        filteredAuthentications = filteredAuthentications.filter { authentication ->
            authentication.stuNum.substring(1, 2).toInt() == homeroomTeacher.classNum
        }

        filters.stuNumSort?.let { stuNumSort ->
            filteredAuthentications =
                if (stuNumSort == "ASCENDING") filteredAuthentications.sortedBy { it.stuNum }
                else filteredAuthentications.sortedBy { it.stuNum }.reversed()
        }

        filters.requestTimeSort?.let { requestTimeSort ->
            filteredAuthentications =
                if (requestTimeSort == "ASCENDING") filteredAuthentications.sortedBy { it.requestedTime }
                else filteredAuthentications.sortedBy { it.requestedTime }.reversed()
        }

        return filteredAuthentications
    }
}