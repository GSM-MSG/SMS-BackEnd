package team.msg.sms.domain.student.service

interface CheckStudentLinkService {
    fun checkExistsByToken(token: String): Boolean
}