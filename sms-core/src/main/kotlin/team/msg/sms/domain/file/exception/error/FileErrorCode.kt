package team.msg.sms.domain.file.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class FileErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {
    IMAGE_INVALID_EXTENSION(ErrorStatus.BAD_REQUEST, "Allowed Extension : jpg(JPG), jpeg(JPEG), png(PNG), heic(HEIC)"),
    FILE_INVALID_EXTENSION(ErrorStatus.BAD_REQUEST, "Allowed Extension : hwp(HWP)"),
    IO_INTERRUPTED(ErrorStatus.INTERNAL_SERVER_ERROR, "Interrupted File IO");

    override fun status(): Int = status
    override fun message(): String = message

}