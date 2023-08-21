package team.msg.sms.domain.certificate.service

import team.msg.sms.common.annotation.Service

@Service
class CertificateService(
    commandCertificateService: CommandCertificateService,
    getCertificateService: GetCertificateService,
    checkCertificateService: CheckCertificateService
) : CommandCertificateService by commandCertificateService,
    GetCertificateService by getCertificateService,
    CheckCertificateService by checkCertificateService