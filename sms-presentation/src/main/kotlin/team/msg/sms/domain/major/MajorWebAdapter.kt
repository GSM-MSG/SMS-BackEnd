package team.msg.sms.domain.major

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.major.dto.MajorsResponse
import team.msg.sms.domain.major.usecase.QueryAllMajorUseCase

@RestController
@RequestMapping("/major")
class MajorWebAdapter
    (
    private val queryAllMajorUseCase: QueryAllMajorUseCase
) {
    @GetMapping("/list")
    fun getAllMajor(): ResponseEntity<MajorsResponse> {
        val result = queryAllMajorUseCase.execute()
        return ResponseEntity.ok(
            MajorsResponse(major = result.map { it.major })
        )
    }
}