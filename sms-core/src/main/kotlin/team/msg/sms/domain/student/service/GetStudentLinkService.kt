package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student.StudentWithUserInfo

interface GetStudentLinkService {
    fun getStudentUserInfoByToken(token: String) : StudentWithUserInfo
}