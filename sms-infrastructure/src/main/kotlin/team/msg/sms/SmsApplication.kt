package team.msg.sms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SmsApplication

fun main(args: Array<String>) {
    runApplication<SmsApplication>(*args)
}