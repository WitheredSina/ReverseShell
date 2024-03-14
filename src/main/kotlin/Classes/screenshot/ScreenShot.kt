package Classes.screenshot

import Classes.dropbox.DropboxUploader
import java.awt.Rectangle
import java.awt.Robot
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import javax.imageio.ImageIO

class ScreenShot(private val printStream: PrintStream) {
    fun captureScreen(): BufferedImage {
        val screenRect = Rectangle(Toolkit.getDefaultToolkit().screenSize)
        return Robot().createScreenCapture(screenRect)
    }

    fun sendScreenshot(screenshot: BufferedImage,  dropboxPath: String) {
        val uploader = DropboxUploader("sl.BwyyRwU-LZVyjNyMmyLRFVnBY7E_WBu7YQvF2MCMsbmmVdHj5iwFhFrxkdekcskkEgmP3ZaDMHJ9S3LmGBXSjGH-t1MwmVHmiH2I524MA24fP7JC-PvIS4rrImiIGY0UWlIY_1Ix1yKB")

        val baos = ByteArrayOutputStream()
        ImageIO.write(screenshot, "jpg", baos)
        val fileData = baos.toByteArray()

        try {
            val meta = uploader.uploadFile(dropboxPath, fileData)
            printStream.println("Upload succeeded: ${meta.pathDisplay}")
        } catch (e: Exception){
            printStream.println("Upload failed: " + e.message)
        }
    }
    fun getFile(image: BufferedImage, fileName: String): File? {
        val name = fileName
        val file = File(fileName)
        file.takeIf { file.exists() }?.run {
            printStream.print("File already exists. Please change the name: ")
            return null
        }


        TODO()
    }
}
