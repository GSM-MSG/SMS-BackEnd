package team.msg.sms.global.security.principle

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.student.model.Student
import java.util.UUID

class StudentDetail(
    private val studentId: UUID
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority(Role.STUDENT.name))

    override fun getPassword(): String? = null

    override fun getUsername(): String = studentId.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}