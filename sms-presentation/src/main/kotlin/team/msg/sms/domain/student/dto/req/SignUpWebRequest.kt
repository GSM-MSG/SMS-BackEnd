package team.msg.sms.domain.student.dto.req

import javax.validation.constraints.*

data class SignUpWebRequest(
    val major: String,

    val techStacks: List<String>,

    @field:NotBlank
    @field:Pattern(regexp = "^https://.*")
    val profileImgUrl: String,

    @field:Size(max = 50)
    val introduce: String,

    @field:NotBlank
    @field:Email
    val contactEmail: String,

    ) {
    fun toData(): SignUpRequestData =
        SignUpRequestData(
            major = major,
            techStacks = techStacks,
            profileImgUrl = profileImgUrl,
            introduce = introduce,
            contactEmail = contactEmail,
        )
}
