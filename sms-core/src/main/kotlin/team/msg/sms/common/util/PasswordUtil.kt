package team.msg.sms.common.util

import java.security.SecureRandom
import java.util.stream.Collectors
import java.util.stream.IntStream

object PasswordUtil {
	// 소문자와 숫자로 이루어진 length 만큼의 길이를 가진 문자열을 생성하는 함수
	fun generateSecret(length: Int): String {
		val secureRandom = SecureRandom()

		val password = IntStream.concat(
			IntStream.rangeClosed(48, 57),
			IntStream.rangeClosed(97, 122)
		).mapToObj { i -> i.toChar().toString() }.collect(Collectors.joining())

		val builder = StringBuilder()

		for (i in 0 until length) {
			builder.append(password[secureRandom.nextInt(password.length)])
		}

		return builder.toString()
	}
}
