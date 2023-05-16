package team.msg.sms.common.util

object FileUtil {
    fun String.isImageCorrectExtension() =
        when (this.lowercase()) {
            "jpg", "jpeg", "png", "heic" -> true
            else -> false
        }

    fun String.isHWPCorrectExtension() =
        when (this.lowercase()) {
            "hwp" -> true
            else -> false
        }
}