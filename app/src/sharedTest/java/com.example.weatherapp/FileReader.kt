package com.example.weatherapp

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object FileReader {
    /*   fun readStringFromFile(fileName: String): String {
           try {
               val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                   .applicationContext as WeatherTestApp).assets.open(fileName)
               val builder = StringBuilder()
               val reader = InputStreamReader(inputStream, "UTF-8")
               reader.readLines().forEach {
                   builder.append(it)
               }
               return builder.toString()
           } catch (e: IOException) {
               throw e
           }
       }

   */


    @Throws(IOException::class)
    fun readStringFromFile(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream =
                javaClass.classLoader?.getResourceAsStream(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }

    fun kotlinReadFileWithNewLineFromResources(fileName: String): String {
        return getInputStreamFromResource(fileName)?.bufferedReader()
            .use { bufferReader -> bufferReader?.readText() } ?: ""
    }

    private fun getInputStreamFromResource(fileName: String) =
        javaClass.classLoader?.getResourceAsStream(fileName)
}