package team.msg.sms.domain.certificate.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.certificate.service.CheckCertificateService

@Service
class CheckCertificateServiceImpl(

) : CheckCertificateService {
    override fun checkAddedCertificate(certificates: List<Certificate>, modifyCertificates: List<String>) =
        modifyCertificates
            .filterNot { certificates.map { it -> it.certificateName }.contains(it) }
    override fun checkRemovedCertificate(certificates: List<Certificate>, modifyCertificates: List<String>): List<Certificate> =
        certificates.filterNot { modifyCertificates.contains(it.certificateName) }
}