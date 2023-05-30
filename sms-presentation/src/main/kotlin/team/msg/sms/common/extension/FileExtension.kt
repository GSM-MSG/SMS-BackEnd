package team.msg.sms.common.extension

import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

fun MultipartFile.toFile() =
    File("${UUID.randomUUID()}||$originalFilename")
        .let {
            FileOutputStream(it)
                .run {
                    this.write(bytes)
                    this.close()
                }
            it
        }