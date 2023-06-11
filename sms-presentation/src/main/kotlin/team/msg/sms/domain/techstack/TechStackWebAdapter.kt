package team.msg.sms.domain.techstack

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.techstack.dto.TechStacksResponse
import team.msg.sms.domain.techstack.usecase.QueryAllTechStackUseCase

@RestController
@RequestMapping("/stack")
class TechStackWebAdapter(
    private val queryAllTechStackUseCase: QueryAllTechStackUseCase
) {
    @GetMapping("/list")
    fun getAllMajor(
        @RequestParam(name = "stack", required = false) stack: String?
    ): ResponseEntity<TechStacksResponse> =
        ResponseEntity.ok(queryAllTechStackUseCase.execute(stack = stack))
}