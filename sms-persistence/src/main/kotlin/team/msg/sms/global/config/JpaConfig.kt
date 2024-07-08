package team.msg.sms.global.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EnableJpaRepositories(
    basePackages = [
        "team.msg.sms.persistence.authentication.repository",
        "team.msg.sms.persistence.certificate.repository",
        "team.msg.sms.persistence.file.repository",
        "team.msg.sms.persistence.image.repository",
        "team.msg.sms.persistence.languagecertificate.repository",
        "team.msg.sms.persistence.major.repository",
        "team.msg.sms.persistence.prize.repository",
        "team.msg.sms.persistence.project.repository",
        "team.msg.sms.persistence.region.repository",
        "team.msg.sms.persistence.student.repository",
        "team.msg.sms.persistence.teacher.repository",
        "team.msg.sms.persistence.techstack.repository",
        "team.msg.sms.persistence.user.repository",
    ],
    excludeFilters = [ComponentScan.Filter(
        type = FilterType.ASPECTJ,
        pattern = ["team.msg.sms.persistence.auth.repository.RefreshTokenRepository", "team.msg.sms.persistence.student.redisRepository.*"]
    )]
)
class JpaConfig