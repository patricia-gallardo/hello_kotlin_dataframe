package org.example

import java.io.File

class CpvFolder {
    companion object {
        fun get(fileName: String, folder: String): File {
            val path = folder.split('/')
                .joinToString(File.separator)
            val parentFolder = File(path)
            require(parentFolder.exists()) { "Folder for file $fileName not found: $folder" }
            return parentFolder
        }
    }
}
