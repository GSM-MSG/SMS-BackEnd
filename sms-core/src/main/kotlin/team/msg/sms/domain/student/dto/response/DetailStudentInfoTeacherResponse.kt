package team.msg.sms.domain.student.dto.response

import team.msg.sms.domain.student.model.Department

class DetailStudentInfoTeacherResponse(
    val name: String,
    val introduce: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: Department,
    val major: String,
    val profileImg: String,
    val techStack: List<String>
) {
}