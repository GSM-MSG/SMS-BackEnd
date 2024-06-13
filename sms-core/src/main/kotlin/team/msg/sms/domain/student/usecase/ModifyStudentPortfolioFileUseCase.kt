package team.msg.sms.domain.student.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.util.FileUtil
import team.msg.sms.common.util.FileUtil.isPDFCorrectExtension
import team.msg.sms.domain.file.spi.UploadFilePort
import team.msg.sms.domain.student.exception.PortfolioInvalidExtensionException
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.user.service.UserService
import java.io.File

@UseCase
class ModifyStudentPortfolioFileUseCase(
    private val userService: UserService,
    private val studentService: StudentService,
    private val uploadFilePort: UploadFilePort
) {
    fun execute(portfolioFile: File?) {
        val user = userService.getCurrentUser()
        val student = studentService.getStudentByUser(user)

        if(portfolioFile == null){
            studentService.saveStudent(student.copy(portfolioFileUrl = null), user)
        } else {
            if(!portfolioFile.extension.isPDFCorrectExtension()){
                throw PortfolioInvalidExtensionException
            }

            val portfolioFileUrl = uploadFilePort.uploadWithName(
                portfolioFile,
                "${user.stuNum}_${user.name}_포트폴리오"
            )

            studentService.saveStudent(
                student.copy(portfolioFileUrl = portfolioFileUrl, portfolioUrl = null),
                user
            )
        }
    }
}