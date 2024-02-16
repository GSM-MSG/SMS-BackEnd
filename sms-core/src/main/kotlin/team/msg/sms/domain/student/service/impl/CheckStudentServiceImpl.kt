package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.exception.StudentAlreadyException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.CheckStudentService
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.user.model.User

@Service
class CheckStudentServiceImpl (
    private val studentPort: StudentPort
) : CheckStudentService{
    override fun checkStudentExistsByUser(user: User) {
        if(studentPort.existsStudentByUser(user))
            throw StudentAlreadyException
    }
    override fun studentExistsByUser(user: User): Boolean =
        studentPort.existsStudentByUser(user)

    override fun checkNewStudent(user: User): Boolean =
        studentPort.existsStudentByUser(user)

    override fun checkStudentDataMismatch(student: Student, modifyStudentData: Student): Boolean {
        return if(student.contactEmail != modifyStudentData.contactEmail) true
        else if(student.major != modifyStudentData.major) true
        else if(student.introduce != modifyStudentData.introduce) true
        else if(student.department.name != modifyStudentData.department.name) true
        else if(student.formOfEmployment.name != modifyStudentData.formOfEmployment.name) true
        else if(student.gsmAuthenticationScore != modifyStudentData.gsmAuthenticationScore) true
        else if(student.militaryService.name != modifyStudentData.militaryService.name) true
        else if(student.portfolioUrl != modifyStudentData.portfolioUrl) true
        else if(student.salary != modifyStudentData.salary) true
        else student.profileImgUrl != modifyStudentData.profileImgUrl
    }
}