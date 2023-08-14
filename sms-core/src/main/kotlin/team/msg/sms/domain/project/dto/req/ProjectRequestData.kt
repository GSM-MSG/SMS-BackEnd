package team.msg.sms.domain.project.dto.req

data class ProjectRequestData(
    val name: String,
    val icon: String,
    val previewImages: List<String>,
    val description: String,
    val links: List<LinkRequestData>,
    val techStacks: List<String>,
    val myActivity : String,
    val inProgress: ProjectInProgressData
)