package team.msg.sms.global.logger

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import team.msg.sms.thirdparty.aws.stroage.AwsS3Properties
import java.io.File

@Component
class LoggingScheduler(
    private val amazonS3: AmazonS3,
    private val awsS3Properties: AwsS3Properties
) {
    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    @Scheduled(cron = "59 59 23 * * * ?", zone =  "Asia/Seoul")
    fun sendLog() {
        log.info("--------------------로그 스케줄링이 시작됩니다.-----------------------")
        log.error("--------------------로그 스케줄링이 시작됩니다.-----------------------")
        val logDir = "./logs/"
        val logDirectory = File(logDir)
        logDirectory.listFiles()
            .forEach { file ->
                val fileName = file.name
                val objectMetadata = ObjectMetadata()
                objectMetadata.contentLength = file.length()
                objectMetadata.contentType = "text/plain"
                amazonS3.putObject(
                    PutObjectRequest(awsS3Properties.bucketLog, fileName, file.inputStream(), objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
                )
                file.delete()
            }
    }
}