package Classes.terminal

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream

class ExecTerminal(command: String, output: PrintStream) {
    init{
        val proc = Runtime.getRuntime().exec(command)
        println("Command executed")

        val reader = BufferedReader(InputStreamReader(proc.inputStream))
        println("Outputting results...")

        var line: String?
        while (reader.readLine().also { line = it } != null){
            output.println(line)
        }
        output.close()
        reader.close()
    }
}
