package team.msg.sms.thirdparty.aws.stroage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import team.msg.sms.domain.file.exception.FileIOInterruptedException
import team.msg.sms.domain.file.spi.UploadFilePort
import java.io.File
import java.io.IOException
import java.util.*

@Component
class AwsS3Adapter(
    private val amazonS3: AmazonS3,
    private val awsS3Properties: AwsS3Properties
) : UploadFilePort {
    override fun upload(file: File): String {
        val fileName = "${UUID.randomUUID().toString()}.${file.extension}"

        return inputS3(file, fileName)
            .run { getResourceUrl(fileName = fileName) }
    }

    // ex. fileName = "2301_김동학_포트폴리오"
    override fun uploadWithName(file: File, fileName: String): String {
        return inputS3(file, "${fileName}.${file.extension}")
            .run { getResourceUrl(fileName = fileName) }
    }

    private fun inputS3(file: File, fileName: String) {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = file.length()
        objectMetadata.contentType = Mimetypes.getInstance().getMimetype(file)
        try {
            amazonS3.putObject(
                PutObjectRequest(awsS3Properties.bucket, fileName, file.inputStream(), objectMetadata)
                    .withCannedAcl(
                        CannedAccessControlList.PublicRead
                    )
            )
            file.delete()
        } catch (e: IOException) {
            throw FileIOInterruptedException
        }
    }

    override fun getResourceUrl(fileName: String): String =
        amazonS3.getUrl(awsS3Properties.bucket, fileName).toString()
}