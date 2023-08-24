package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.service.CheckStudentTechStackService

@Service
class CheckStudentTechStackServiceImpl(

) : CheckStudentTechStackService {
    override fun checkAddedTechStacks(techStacks: List<String>, modifyTechStack: List<String>): List<String> =
        modifyTechStack.filterNot { techStacks.contains(it) }

    override fun checkRemovedTechStacks(techStack: List<String>, modifyTechStack: List<String>): List<String> =
        techStack.filterNot { modifyTechStack.contains(it) }
}