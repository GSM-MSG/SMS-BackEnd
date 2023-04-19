package team.msg.sms.global.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "security")
class SecurityProperties(
    val secretKey: String,
    val accessExp: Int,
    val refreshExp: Int
)