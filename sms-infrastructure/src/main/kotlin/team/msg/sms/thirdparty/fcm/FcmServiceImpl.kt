package team.msg.sms.thirdparty.fcm

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.auth.oauth2.GoogleCredentials
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.*
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import team.msg.sms.common.model.Fcm
import team.msg.sms.common.service.FcmService
import java.nio.charset.StandardCharsets

@Configuration
class FcmServiceImpl : FcmService {
    override fun sendMessageTo(fcm: Fcm): Int {
        val fcmDto = FcmDto(
            token = fcm.token,
            body = fcm.body,
            title = fcm.title
        )
        val message = makeMessage(fcmDto)

        val restTemplate = RestTemplate()

        restTemplate.messageConverters
            .add(0, StringHttpMessageConverter(StandardCharsets.UTF_8))

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("Authorization", "Bearer " + getAccessToken())

        val entity = HttpEntity(message, headers)

        val apiUrl = "https://fcm.googleapis.com/v1/projects/msg-sms-6542d/messages:send"
        val response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String::class.java)

        return if (response.statusCode == HttpStatus.OK) 1 else 0
    }

    private fun getAccessToken(): String? {
        val firebaseConfigPath = "firebase/Firebase-adminsdk-97zws.json"

        val googleCredentials = GoogleCredentials
            .fromStream(ClassPathResource(firebaseConfigPath).inputStream)
            .createScoped(listOf("https://www.googleapis.com/auth/cloud-platform"))

        googleCredentials.refreshIfExpired()
        return googleCredentials.accessToken.tokenValue
    }

    private fun makeMessage(fcmDto: FcmDto): String {
        val objectMapper = ObjectMapper()
        val fcmMessageDto = FcmMessageDto(
            message = FcmMessageDto.Message(
                token = fcmDto.token,
                notification = FcmMessageDto.Notification(
                    title = fcmDto.title,
                    body = fcmDto.body,
                    image = null
                )
            ),
            validateOnly = false
        )
        return objectMapper.writeValueAsString(fcmMessageDto)
    }
}