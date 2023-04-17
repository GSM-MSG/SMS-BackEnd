package team.msg.sms.common.error

interface ErrorProperty {

    fun status(): Int

    fun message(): String
}
