package team.msg.sms.persistence.student.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.util.UUID

@RedisHash(value = "student_link")
class StudentLinkEntity (
	@Id
	@Indexed
	val token: String,

	@Indexed
	val studentId: UUID,

	@TimeToLive
	val timeToLive: Long
)
