package team.msg.sms.domain.file.spi

import java.io.File
interface UploadFilePort {
    fun upload(file: File): String

    fun uploadWithName(file: File, filename: String): String

    fun getResourceUrl(fileName: String): String
}