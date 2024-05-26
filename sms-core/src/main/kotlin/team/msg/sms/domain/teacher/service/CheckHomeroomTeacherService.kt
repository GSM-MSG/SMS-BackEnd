package team.msg.sms.domain.teacher.service

interface CheckHomeroomTeacherService {
    fun checkHomeroomTeacherExistsByGradeAndClassNum(grade: Int, classNum: Int)
}