package team.msg.sms.domain.student.service.impl

import org.springframework.stereotype.Service
import team.msg.sms.domain.student.dto.req.FiltersRequestData
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.FilterStudentService

@Service
class FilterStudentServiceImpl(
) : FilterStudentService {
    override fun filterStudents(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersRequestData,
        role: String
    ): List<Student.StudentWithUserInfo> =
        when (role) {
            "ROLE_TEACHER" -> this.filterStudentsForTeacher(students, filters)
            "ROLE_STUDENT" -> this.filterStudentsForStudent(students, filters)
            else -> this.filterStudentsForAnonymous(students, filters)
        }

    private fun filterStudentsForTeacher(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersRequestData
    ): List<Student.StudentWithUserInfo> {
        var filteredStudents = students

        filters.majors?.let { majors -> // 전공
            if (filters.majors.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.major in majors
                }
        }

        filters.techStacks?.let { techStacks -> // techStack
            if (filters.techStacks.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.techStack.intersect(techStacks.toSet()).isNotEmpty()
                }
        }

        filters.grade?.let { grade ->
            if (filters.grade.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.stuNum.substring(0, 1).toInt() in grade
                }
        }


        filters.classNum?.let { classNum ->
            if (filters.classNum.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.stuNum.substring(1, 2).toInt() in classNum
                }
        }

        filters.department?.let { departments ->
            if (filters.department.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.department.name in departments
                }
        }
        filters.formOfEmployment?.let { formOfEmployments ->
            if (filters.formOfEmployment.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.formOfEmployment.name in formOfEmployments
                }
        }

        if (filters.minGsmAuthenticationScore != null && filters.maxGsmAuthenticationScore != null)
            filteredStudents = filteredStudents.filter { student ->
                student.gsmAuthenticationScore >= filters.minGsmAuthenticationScore && student.gsmAuthenticationScore <= filters.maxGsmAuthenticationScore
            }

        if (filters.minSalary != null && filters.maxSalary != null) {
            filteredStudents = filteredStudents.filter { student ->
                student.salary >= filters.minSalary && student.salary <= filters.maxSalary
            }
        }

        filters.gsmAuthenticationScoreSort?.let { gsmScoreSort ->
            filteredStudents =
                if (gsmScoreSort == "ASCENDING") filteredStudents.sortedBy { it.gsmAuthenticationScore }
                else filteredStudents.sortedBy { it.gsmAuthenticationScore }.reversed()
        }

        filters.salarySort?.let { salarySort ->
            filteredStudents =
                if (salarySort == "ASCENDING") filteredStudents.sortedBy { it.salary }
                else filteredStudents.sortedBy { it.salary }.reversed()
        }

        filters.stuNumSort?.let { stuNumSort ->
            filteredStudents =
                if (stuNumSort == "ASCENDING") filteredStudents.sortedBy { it.stuNum }
                else filteredStudents.sortedBy { it.stuNum }.reversed()
        }

        return filteredStudents
    }


    private fun filterStudentsForStudent(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersRequestData
    ): List<Student.StudentWithUserInfo> {
        var filteredStudents = students

        filters.majors?.let { majors -> // 전공
            if (filters.majors.isNotEmpty())

                filteredStudents = filteredStudents.filter { student ->
                    student.major in majors
                }
        }

        filters.techStacks?.let { techStacks -> // techStack
            if (filters.techStacks.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.techStack.intersect(techStacks.toSet()).isNotEmpty()
                }
        }


        filters.grade?.let { grade ->
            if (filters.grade.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.stuNum.substring(0, 1).toInt() in grade
                }
        }

        filters.classNum?.let { classNum ->
            if (filters.classNum.isNotEmpty())

                filteredStudents = filteredStudents.filter { student ->
                    student.stuNum.substring(1, 2).toInt() in classNum
                }
        }

        filters.department?.let { departments ->
            if (filters.department.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.department.name in departments
                }
        }


        filters.stuNumSort?.let { stuNumSort ->
            filteredStudents =
                if (stuNumSort == "ASCENDING") filteredStudents.sortedBy { it.stuNum }
                else filteredStudents.sortedBy { it.stuNum }.reversed()
        }

        return filteredStudents
    }

    private fun filterStudentsForAnonymous(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersRequestData
    ): List<Student.StudentWithUserInfo> {
        var filteredStudents = students

        filters.majors?.let { majors -> // 전공
            if (filters.majors.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.major in majors
                }
        }

        filters.techStacks?.let { techStacks -> // techStack
            if (filters.techStacks.isNotEmpty())
                filteredStudents = filteredStudents.filter { student ->
                    student.techStack.intersect(techStacks.toSet()).isNotEmpty()
                }
        }

        return filteredStudents
    }
}

