package ftp

import org.apache.commons.net.ftp.FTPClient
import java.nio.file.Paths

object FtpUtil {
    private val ftpClient = FTPClient()

    fun download(fileName: String, filePath: String) {
        ftpClient.connect("127.0.0.1", 14021)
        ftpClient.login("test", "test")
        val file = Paths.get(filePath, fileName).toFile()
        if (!file.exists()) {
            file.createNewFile()
        }
        file.outputStream().use {
            ftpClient.retrieveFile("/topsec/pcap/20231121/test.txt", it)
        }
        ftpClient.logout()
    }
}

fun main() {
    FtpUtil.download("1.txt", "E:\\Project\\java-kotlin-knowledge\\target")
}