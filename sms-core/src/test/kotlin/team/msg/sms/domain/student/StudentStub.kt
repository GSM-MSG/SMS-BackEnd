package team.msg.sms.domain.student

import team.msg.sms.domain.student.model.Department
import team.msg.sms.domain.student.model.FormOfEmployment
import team.msg.sms.domain.student.model.MilitaryService
import team.msg.sms.domain.student.model.Student
import java.util.*

class StudentStub {

    fun generatedStudent(
        id: UUID = UUID.randomUUID(),
        major: String = "Android",
        introduce: String = "안녕하세요 안드로이드 왕이 될 남자입니다.",
        department: Department = Department.SW_DEVELOPMENT,
        contactEmail: String = "s21042@gsm.hs.kr",
        formOfEmployment: FormOfEmployment = FormOfEmployment.FULL_TIME,
        gsmAuthenticationScore: Int = 990,
        militaryService: MilitaryService = MilitaryService.HOPE,
        portfolioUrl: String? = "https://notion.pig/",
        dreamBookFileUrl: String? = "https://s3.download.pig.jpg",
        salary: Int = 3500,
        profileImgUrl: String = "https://github.com/kjhpig",
        userId: UUID = UUID.randomUUID()
    ): Student = Student(
        id = id,
        major = major,
        introduce = introduce,
        department = department,
        contactEmail = contactEmail,
        formOfEmployment = formOfEmployment,
        gsmAuthenticationScore = gsmAuthenticationScore,
        militaryService = militaryService,
        portfolioUrl = portfolioUrl,
        dreamBookFileUrl = dreamBookFileUrl,
        salary = salary,
        profileImgUrl = profileImgUrl,
        userId = userId
    )
    fun generatedStudentWithUserInfo(
        id: UUID = UUID.randomUUID(),
        major: String = "Android",
        stuNum: String = "3205",
        introduce: String = "안녕하세요 안드로이드 왕이 될 남자입니다.",
        department: Department = Department.SW_DEVELOPMENT,
        contactEmail: String = "s21042@gsm.hs.kr",
        formOfEmployment: FormOfEmployment = FormOfEmployment.FULL_TIME,
        gsmAuthenticationScore: Int = 990,
        militaryService: MilitaryService = MilitaryService.HOPE,
        portfolioUrl: String? = "https://notion.pig/",
        dreamBookFileUrl: String? = "https://s3.download.pig.jpg",
        salary: Int = 3500,
        name: String = "김현승",
        profileImgUrl: String = "https://github.com/kjhpig",
        techStack: List<String> = arrayListOf("Compose", "Retrofit", "Kotlin", "Coroutine")
    ): Student.StudentWithUserInfo = Student.StudentWithUserInfo(
            id, major, stuNum, introduce, department, contactEmail, formOfEmployment, gsmAuthenticationScore, militaryService, portfolioUrl, dreamBookFileUrl, salary, name, profileImgUrl, techStack
        )

    fun generatedStudentWithPageInfo(
        students: List<Student.StudentWithUserInfo> = arrayListOf(this.generatedStudentWithUserInfo()),
        page: Int = 1,
        contentSize: Int = 20,
        totalSize: Long = 1,
        last: Boolean = true
    ): Student.StudentWithPageInfo = Student.StudentWithPageInfo(
        students, page, contentSize, totalSize, last
    )
}