package team.msg.sms.domain.student.service

interface CheckStudentTechStackService {
    fun checkAddedTechStacks(techStacks: List<String>, modifyTechStack: List<String>): List<String>

    fun checkRemovedTechStacks(techStack: List<String>, modifyTechStack: List<String>): List<String>
}