package org.example

import java.io.File

class Log {
    companion object {
        fun write(parentFolder: File, fileName: String) {
            val platformPath = "${parentFolder.path}${File.separator}${fileName}"
            val normalizedPath = platformPath.split(File.separator).joinToString("/")
            println("Wrote CSV to $normalizedPath")
        }

        fun read(parentFolder: File, fileName: String) {
            val platformPath = "${parentFolder.path}${File.separator}${fileName}"
            val normalizedPath = platformPath.split(File.separator).joinToString("/")
            println("Reading CSV from $normalizedPath")
        }
    }
}
