package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.SectionType
import java.util.*

data class SubmitUserFormRequestData(
    val sectionId: UUID,
    val objects: List<SubmitValueRequestData>
) {
    data class SubmitValueRequestData(
        val fieldId: UUID,
        val selectId: UUID?,
        val sectionType: SectionType,
        val value: String?
        )
}