package team.msg.sms.domain.teacher.spi

interface QueryHomeroomTeacherPort {
    fun existsHomeroomTeacherByGradeAndClassNum(grade: Int, classNum: Int): Boolean
}