package team.msg.sms.domain.student

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import team.msg.sms.domain.student.dto.response.StudentInfoListResponse

@Controller
@RequestMapping("/student")
class StudentGraphqlWebAdapter(

) {
    @SchemaMapping(typeName = "Query", value = "findAll")
    fun findAll(): ResponseEntity<List<StudentInfoListResponse>> = Any
}