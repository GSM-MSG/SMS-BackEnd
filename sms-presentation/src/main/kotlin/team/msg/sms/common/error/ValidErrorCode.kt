package team.msg.sms.common.error


enum class ValidErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {
    BAD_REQUEST(400, "Bad Request");

    override fun status(): Int = status
    override fun message(): String = message
}