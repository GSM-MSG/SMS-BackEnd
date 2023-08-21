package team.msg.sms.domain.languagecertificate.service

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate

interface CheckLanguageCertificateService {
    fun checkAddedLanguageCertificate(
        languageCertificates: List<LanguageCertificate>,
        modifyLanguageCertificates: List<LanguageCertificate>
    ): List<LanguageCertificate>

    fun checkRemovedLanguageCertificate(
        languageCertificates: List<LanguageCertificate>,
        modifyLanguageCertificates: List<LanguageCertificate>
    ): List<LanguageCertificate>
}