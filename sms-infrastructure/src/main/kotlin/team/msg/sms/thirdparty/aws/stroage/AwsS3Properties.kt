package team.msg.sms.thirdparty.aws.stroage

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("cloud.aws.s3")
@ConstructorBinding
class AwsS3Properties(
    val bucket: String,
    val bucketLog: String
)