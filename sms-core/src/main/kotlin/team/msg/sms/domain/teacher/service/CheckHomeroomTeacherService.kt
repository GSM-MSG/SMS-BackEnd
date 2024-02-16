package team.msg.sms.domain.teacher.service

interface CheckHomeroomTeacherService {
    fun checkHomeroomTeacherExistsByTeacher(grade: Int, classNum: Int)
}