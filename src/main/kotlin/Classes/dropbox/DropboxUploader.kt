package Classes.dropbox

import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.FileMetadata
import java.io.ByteArrayInputStream

class DropboxUploader(private val accessToken: String) {
    fun uploadFile(dropboxPath: String, fileData: ByteArray): FileMetadata {
        val config = DbxRequestConfig.newBuilder("WitheredSina's App").build()
        val client = DbxClientV2(config, accessToken)

        val inputStream = ByteArrayInputStream(fileData)
        return client.files().uploadBuilder(dropboxPath)
            .uploadAndFinish(inputStream)
    }
}
