package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.FieldType
import java.util.*

data class SubmitUserFormRequestData(
    val sectionId: UUID,
    val objects: List<SubmitValueRequestData>
) {
    data class SubmitValueRequestData(
        val groupId: UUID,
        val fields: List<SubmitFieldValueRequestData>
    )

    data class SubmitFieldValueRequestData(
        val fieldId: UUID,
        val selectId: UUID?,
        val fieldType: FieldType,
        val value: String?
    )
}