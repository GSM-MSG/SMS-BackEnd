package team.msg.sms.domain.authentication.model

enum class ActivityStatus(description: String) {
    APPROVED("승인됨"),
    REJECTED("반려됨"),
    REQUESTED("요청됨"),
    PENDING("대기중")
}