package team.msg.sms.domain.languagecertificate.service

import team.msg.sms.common.annotation.Service

@Service
class LanguageCertificateService(
    commandLanguageCertificateService: CommandLanguageCertificateService,
    getLanguageCertificateService: GetLanguageCertificateService,
    checkLanguageCertificateService: CheckLanguageCertificateService
) : CommandLanguageCertificateService by commandLanguageCertificateService,
    GetLanguageCertificateService by getLanguageCertificateService,
    CheckLanguageCertificateService by checkLanguageCertificateService