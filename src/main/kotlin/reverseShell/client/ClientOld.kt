package reverseShell.client

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class ClientOld {
    fun start(address: String, port: Int, command: String, ) {
        val client = Socket(address, port)
        println("Connected to server")
        val writer = OutputStreamWriter(client.getOutputStream())
        println("Sending command: $command")
        writer.write(command)
        println("Sent command: $command")
        writer.flush()
        println("Waiting for response...")

        val reader = BufferedReader(InputStreamReader(client.getInputStream()))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }

        client.close()
    }
}
