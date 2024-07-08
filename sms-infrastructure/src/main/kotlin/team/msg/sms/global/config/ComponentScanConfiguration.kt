package team.msg.sms.global.config

import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import team.msg.sms.common.annotation.ReadOnlyUseCase
import team.msg.sms.common.annotation.Service
import team.msg.sms.common.annotation.UseCase

@Configuration
@ComponentScan(
    basePackages = ["team.msg.sms"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                UseCase::class,
                Service::class,
                ReadOnlyUseCase::class
            ]
        )
    ]
)
class ComponentScanConfiguration