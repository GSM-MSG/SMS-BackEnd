package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.SectionType
import java.util.*

data class SubmitUserFormRequestData(
    val sectionId: UUID,
    val sectionType: SectionType,
    val objects: List<SubmitValueRequestData>
) {
    data class SubmitValueRequestData(
        val selectId: UUID?,
        val value: String?
    )
}