package team.msg.sms.domain.authentication.dto.res

import java.util.UUID

data class StudentAuthenticationFormResponseData(
    val markingBoardId: UUID,
    val title: String,
    val content: List<Area>
) {
    data class Area(
        val areaId: UUID,
        val areaTitle: String,
        val sections: List<Section>
    )
    data class Section(
        val sectionId: UUID,
        val sectionName: String,
        val maxCount: Int,
        val groups: List<Group>
    )
    data class Group(
        val groupId: UUID,
        val maxScore: Double,
        val fields: List<FieldSet>
    )

    data class FieldSet(
        val setId: UUID,
        val values: List<Field>
    )
    data class Field(
        val fieldId: UUID,
        val value: String?
    )
}
