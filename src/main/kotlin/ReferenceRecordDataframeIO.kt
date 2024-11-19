package org.example

import org.apache.commons.csv.CSVFormat
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readDelim
import org.jetbrains.kotlinx.dataframe.io.toCsv
import java.io.File
import java.io.FileInputStream
import java.nio.charset.StandardCharsets

class ReferenceRecordDataframeIO {
    companion object {
        fun read(fileName: String, folder: String): List<ReferenceRecord> {
            val dataFrameRecords = readDataFrame(fileName, folder)
            val records = dataFrameRecords.toList()
            return records
        }

        fun readDataFrame(fileName: String, folder: String): DataFrame<ReferenceRecord> {
            val parentFolder = CpvFolder.get(fileName, folder)
            FileInputStream(File(parentFolder, fileName)).bufferedReader()
                .use { reader ->
                    Log.read(parentFolder, fileName)
                    val format = CSVFormat.DEFAULT
                        .builder().setHeader()
                        .setDelimiter(";")
                        .setRecordSeparator('\n')
                        .build()
                    val readDataFrame = DataFrame.readDelim(reader, format)
                    return readDataFrame.convertTo<ReferenceRecord> {
                        convert<String?>().with { it }
                        convert<String?>().with { fromString(it) }
                        convert<String?>().with { fromString(it) }
                        convert<String?>().with { fromString(it) }
                    }
                }
        }

        private fun fromString(listAsString: String?): List<String>? {
            return listAsString
                ?.removePrefix("[")
                ?.removeSuffix("]")
                ?.split(",")
                ?.map { it.trim() }
        }

        fun write(records: List<ReferenceRecord>, fileName: String, folder: String) {
            val parentFolder = CpvFolder.get(fileName, folder)
            File(parentFolder, fileName).bufferedWriter(StandardCharsets.UTF_8)
                .use { writer ->
                    val frames = records.toDataFrame()
                    val format = CSVFormat.DEFAULT
                        .builder()
                        .setDelimiter(";")
                        .setRecordSeparator('\n')
                        .setQuote(null)
                        .setNullString("")
                        .setIgnoreEmptyLines(true) // SKIP_EMPTY_LINES
                        .setIgnoreSurroundingSpaces(true) // TRIM_SPACES
                        .setAllowMissingColumnNames(true)
                        .build()
                    val csvContents = frames.toCsv(format)
                    writer.write(csvContents)
                    Log.write(parentFolder, fileName)
                }
        }
    }
}
