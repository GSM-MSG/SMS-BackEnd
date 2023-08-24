package team.msg.sms.domain.certificate.service

import team.msg.sms.domain.certificate.model.Certificate

interface CheckCertificateService {
    fun checkAddedCertificate(certificates: List<Certificate>, modifyCertificates: List<String>): List<String>

    fun checkRemovedCertificate(certificates: List<Certificate>, modifyCertificates: List<String>): List<Certificate>
}