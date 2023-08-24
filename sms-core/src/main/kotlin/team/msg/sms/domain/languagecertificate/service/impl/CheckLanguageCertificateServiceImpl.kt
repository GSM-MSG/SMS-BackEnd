package team.msg.sms.domain.languagecertificate.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.CheckLanguageCertificateService

@Service
class CheckLanguageCertificateServiceImpl(

) : CheckLanguageCertificateService {
    override fun checkAddedLanguageCertificate(
        languageCertificates: List<LanguageCertificate>,
        modifyLanguageCertificates: List<LanguageCertificate>
    ): List<LanguageCertificate> {
        return modifyLanguageCertificates.filterNot { modifiedScore ->
            languageCertificates.any { existingCert ->
                existingCert.languageCertificateName == modifiedScore.languageCertificateName &&
                        existingCert.score == modifiedScore.score
            }
        }
    }

    override fun checkRemovedLanguageCertificate(
        languageCertificates: List<LanguageCertificate>,
        modifyLanguageCertificates: List<LanguageCertificate>
    ): List<LanguageCertificate> {
        return languageCertificates.filterNot { existingCert ->
            modifyLanguageCertificates.any { modifiedScore ->
                modifiedScore.languageCertificateName == existingCert.languageCertificateName &&
                        modifiedScore.score == existingCert.score
            }
        }
    }
}