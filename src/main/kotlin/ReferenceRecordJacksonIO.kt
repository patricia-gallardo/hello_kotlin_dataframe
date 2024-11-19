package org.example

import java.io.File
import java.io.FileInputStream
import java.nio.charset.StandardCharsets

class ReferenceRecordJacksonIO {
    companion object {
        fun read(fileName: String, folder: String): List<ReferenceRecord> {
            val parentFolder = CpvFolder.get(fileName, folder)
            FileInputStream(File(parentFolder, fileName))
                .use { stream ->
                    val csvMapper = ReferenceRecord.mapper()
                    val schema = ReferenceRecord.schema()
                    Log.read(parentFolder, fileName)
                    return ReferenceRecord.readCSV(csvMapper, schema, stream)
                }
        }

        fun write(records: List<ReferenceRecord>, fileName: String, folder: String) {
            val parentFolder = CpvFolder.get(fileName, folder)
            File(parentFolder, fileName).bufferedWriter(StandardCharsets.UTF_8)
                .use { writer ->
                    val csvMapper = ReferenceRecord.mapper()
                    val schema = ReferenceRecord.schema()
                    ReferenceRecord.writeCSV(records, writer, csvMapper, schema)
                    Log.write(parentFolder, fileName)
                }
        }
    }
}
