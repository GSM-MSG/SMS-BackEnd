package team.msg.sms.domain.auth.model

enum class Role(description: String) {
    ROLE_STUDENT("학생"),
    ROLE_TEACHER("선생님"),
    ROLE_PRINCIPAL("교장선생님"),
    ROLE_DEPUTY_PRINCIPAL("교감선생님"),
    ROLE_DIRECTOR("부장선생님"),
    ROLE_HOMEROOM("담임선생님")
}