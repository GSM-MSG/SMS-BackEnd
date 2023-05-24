package team.msg.sms.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration
import team.msg.sms.global.security.SecurityProperties
import team.msg.sms.thirdparty.aws.AwsProperties
import team.msg.sms.thirdparty.gauth.GAuthProperties

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        SecurityProperties::class,
        AwsProperties::class,
        GAuthProperties::class
    ])
class PropertiesScanConfig {
}