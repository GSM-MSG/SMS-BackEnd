package team.msg.sms.domain.authentication.service

class AuthenticationService(
    commandAuthenticationService: CommandAuthenticationService
) : CommandAuthenticationService by commandAuthenticationService