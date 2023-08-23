package team.msg.sms.domain.project.dto.res

data class ProjectResponseData(
    val name: String,
    val icon: String,
    val previewImages: List<String>,
    val description: String,
    val links: List<ProjectLinkResponseData>,
    val techStacks: List<String>,
    val myActivity: String?,
    val inProgress: ProjectInProgressResponseData
)
