package team.msg.sms.domain.project.dto.res

data class ProjectResponseData(
    val id: Long,
    val name: String,
    val previewImages: List<String>,
    val description: String,
    val links: List<ProjectLinkResponseData>,
    val projectTechStacks: List<String>,
    val myActivity: String?,
    val inProgress: ProjectInProgressResponseData
)
