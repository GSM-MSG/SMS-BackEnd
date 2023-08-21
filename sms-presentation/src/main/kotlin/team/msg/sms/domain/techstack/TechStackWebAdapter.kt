package team.msg.sms.domain.techstack

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.techstack.dto.res.TechStacksResponseData
import team.msg.sms.domain.techstack.dto.res.TechStacksWebResponse
import team.msg.sms.domain.techstack.usecase.QueryAllTechStackUseCase

@RestController
@RequestMapping("/stack")
class TechStackWebAdapter(
    private val queryAllTechStackUseCase: QueryAllTechStackUseCase
) {
    @GetMapping("/list")
    fun getAllMajor(
        @RequestParam(name = "stack", required = false) stack: String?
    ): ResponseEntity<TechStacksWebResponse> =
        queryAllTechStackUseCase.execute(stack)
            .let { ResponseEntity.ok(it.toResponse()) }

    private fun TechStacksResponseData.toResponse(): TechStacksWebResponse =
        TechStacksWebResponse(
            techStacks= this.techStacks
        )
}