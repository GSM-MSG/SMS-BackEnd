package team.msg.sms.domain.student.dto.res

import java.util.UUID

class MainStudentsResponseData(
    val id: UUID,
    val profileImgUrl: String,
    @Deprecated(message = "전 Beta Version 사용자들의 접근성을 개방하기 위하여 현재버전에서는 사용하지 않습니다.")
    val profileImg: String,
    val major: String,
    val name: String,
    val techStacks: List<String>
) {
    constructor(): this(UUID.randomUUID(), "", "", "", "", arrayListOf())
}
