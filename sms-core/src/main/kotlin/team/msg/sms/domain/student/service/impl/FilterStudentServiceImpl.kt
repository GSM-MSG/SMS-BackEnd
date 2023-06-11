package team.msg.sms.domain.student.service.impl

import org.springframework.stereotype.Service
import team.msg.sms.domain.student.dto.request.FiltersData
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.FilterStudentService

@Service
class FilterStudentServiceImpl(

) : FilterStudentService {
    override fun filterStudents(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersData,
        role: String
    ): List<Student.StudentWithUserInfo> {
        if (role == "ROLE_TEACHER") {
            return this.filterStudentsForTeacher(students, filters)
        } else if (role == "ROLE_STUDENT") {
            return this.filterStudentsForStudent(students, filters)
        } else {
            return this.filterStudentsForAnonymous(students, filters)
        }
    }

    private fun filterStudentsForTeacher(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersData
    ): List<Student.StudentWithUserInfo> {
        var filteredStudents = students

        filters.majors?.let { majors -> // 전공
            filteredStudents = filteredStudents.filter { student ->
                student.major in majors
            }
        }

        filters.techStacks?.let { techStacks -> // techStack
            filteredStudents = filteredStudents.filter { student ->
                student.techStack.intersect(techStacks).isNotEmpty()
            }
        }

        filters.grade?.let { grade ->
            filteredStudents = filteredStudents.filter { student ->
                student.stuNum.substring(0, 1).toInt() == grade
            }
        }

        filters.classNum?.let { classNum ->
            filteredStudents = filteredStudents.filter { student ->
                student.stuNum.substring(1, 2).toInt() == classNum
            }
        }

        filters.department?.let { departments ->
            filteredStudents = filteredStudents.filter { student ->
                student.department.name in departments
            }
        }

        filters.formOfEmployment?.let { formOfEmployments ->
            filteredStudents = filteredStudents.filter { student ->
                student.formOfEmployment.name in formOfEmployments
            }
        }

//        filters.minGsmAuthenticationScore?.let { minScore ->
//            filteredStudents = filteredStudents.filter { student ->
//                student.gsmAuthenticationScore >= minScore
//            }
//        }
//
//        filters.minSalary?.let { minSalary ->
//            filteredStudents = filteredStudents.filter { student ->
//                student.salary >= minSalary
//            }
//        }
//
//        filters.stuNumSort?.let { stuNumSort ->
//            filteredStudents = filteredStudents.sortedBy { it.grade }
//        }

        return filteredStudents
    }

    private fun filterStudentsForStudent(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersData
    ): List<Student.StudentWithUserInfo> {
        var filteredStudents = students

        filters.majors?.let { majors -> // 전공
            filteredStudents = filteredStudents.filter { student ->
                student.major in majors
            }
        }

        filters.techStacks?.let { techStacks -> // techStack
            filteredStudents = filteredStudents.filter { student ->
                student.techStack.intersect(techStacks).isNotEmpty()
            }
        }

        filters.grade?.let { grade ->
            filteredStudents = filteredStudents.filter { student ->
                student.stuNum.substring(0, 1).toInt() == grade
            }
        }

        filters.classNum?.let { classNum ->
            filteredStudents = filteredStudents.filter { student ->
                student.stuNum.substring(1, 2).toInt() == classNum
            }
        }

        filters.department?.let { departments ->
            filteredStudents = filteredStudents.filter { student ->
                student.department.name in departments
            }
        }

        //        filters.stuNumSort?.let { stuNumSort ->
//            filteredStudents = filteredStudents.sortedBy { it.grade }
//        }

        return filteredStudents
    }

    private fun filterStudentsForAnonymous(
        students: List<Student.StudentWithUserInfo>,
        filters: FiltersData
    ): List<Student.StudentWithUserInfo> {
        var filteredStudents = students

        filters.majors?.let { majors -> // 전공
            filteredStudents = filteredStudents.filter { student ->
                student.major in majors
            }
        }

        filters.techStacks?.let { techStacks -> // techStack
            filteredStudents = filteredStudents.filter { student ->
                student.techStack.intersect(techStacks).isNotEmpty()
            }
        }

        return filteredStudents
    }
}

