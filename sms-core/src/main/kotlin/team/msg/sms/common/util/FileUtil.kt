package team.msg.sms.common.util

object FileUtil {
    fun isImageCorrectExtension(extension: String) =
        when (extension.lowercase()) {
            "jpg", "jpeg", "png", "heic" -> true
            else -> false
        }

    fun isHWPCorrectExtension(extension: String) =
        when (extension.lowercase()) {
            "hwp" -> true
            else -> false
        }
}