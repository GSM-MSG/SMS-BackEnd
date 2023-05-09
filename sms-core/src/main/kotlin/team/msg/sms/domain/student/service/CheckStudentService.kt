package team.msg.sms.domain.student.service

import java.util.UUID

interface CheckStudentService {
    fun checkStudentExistsByUserId(userId: UUID)
}