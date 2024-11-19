package org.example

import org.apache.commons.csv.CSVFormat
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.cast
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import org.jetbrains.kotlinx.dataframe.api.toList
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.toCsv
import java.io.File
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
            File(parentFolder, fileName).let { file ->
                Log.read(parentFolder, fileName)
                val readDataFrame = DataFrame.readCSV(file)
                return readDataFrame.cast()
            }
        }

        fun write(records: List<ReferenceRecord>, fileName: String, folder: String) {
            val parentFolder = CpvFolder.get(fileName, folder)
            File(parentFolder, fileName).bufferedWriter(StandardCharsets.UTF_8)
                .use { writer ->
                    val frames = records.toDataFrame()
                    val format = CSVFormat.DEFAULT
                        .builder()
                        .setDelimiter(",")
                        .setRecordSeparator('\n')
                        .setQuote(null)
                        .setNullString("")
                        .setIgnoreEmptyLines(true) // SKIP_EMPTY_LINES
                        .setIgnoreSurroundingSpaces(true) // TRIM_SPACES
                        .setAllowMissingColumnNames(true)
                        .build();
                    val csvContents = frames.toCsv(format)
                    writer.write(csvContents)
                    Log.write(parentFolder, fileName)
                }
        }
    }
}
