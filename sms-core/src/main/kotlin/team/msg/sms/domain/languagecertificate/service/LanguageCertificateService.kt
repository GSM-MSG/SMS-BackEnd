package team.msg.sms.domain.languagecertificate.service

import team.msg.sms.common.annotation.Service

@Service
class LanguageCertificateService(
    commandLanguageCertificateService: CommandLanguageCertificateService,
    getLanguageCertificateService: GetLanguageCertificateService
) : CommandLanguageCertificateService by commandLanguageCertificateService,
    GetLanguageCertificateService by getLanguageCertificateService