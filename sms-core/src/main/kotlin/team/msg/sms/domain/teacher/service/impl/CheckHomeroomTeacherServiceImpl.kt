package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.teacher.exception.HomeroomTeacherAlreadyException
import team.msg.sms.domain.teacher.service.CheckHomeroomTeacherService
import team.msg.sms.domain.teacher.spi.HomeroomTeacherPort

@Service
class CheckHomeroomTeacherServiceImpl(
    private val homeroomTeacherPort: HomeroomTeacherPort
) : CheckHomeroomTeacherService {
    override fun checkHomeroomTeacherExistsByGradeAndClassNum(grade: Int, classNum: Int) {
        if (homeroomTeacherPort.existsHomeroomTeacherByGradeAndClassNum(grade, classNum))
            throw HomeroomTeacherAlreadyException
    }
}