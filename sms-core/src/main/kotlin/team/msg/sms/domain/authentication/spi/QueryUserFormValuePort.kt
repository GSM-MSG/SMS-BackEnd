package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.UserFormValue
import java.util.UUID

interface QueryUserFormValuePort {
    fun queryUserFormValueListByFieldIdAndStudentId(fieldId: UUID, studentId: UUID): List<UserFormValue>
    fun existsUserFormValueBySetIds(setIds: List<UUID>): Boolean
}