package org.example

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toDataFrame
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets

class ReferenceRecordJacksonIO {
    companion object {
        fun readDataFrame(fileName: String, targetFolder: String): DataFrame<ReferenceRecord> {
            val list = read(fileName, targetFolder)
            return list.toDataFrame()
        }

        fun read(fileName: String, folder: String): List<ReferenceRecord> {
            val parentFolder = CpvFolder.get(fileName, folder)
            FileInputStream(File(parentFolder, fileName))
                .use { stream ->
                    val csvMapper = mapper()
                    val schema = schema()
                    Log.read(parentFolder, fileName)
                    return readCSV(csvMapper, schema, stream)
                }
        }

        fun write(records: List<ReferenceRecord>, fileName: String, folder: String) {
            val parentFolder = CpvFolder.get(fileName, folder)
            File(parentFolder, fileName).bufferedWriter(StandardCharsets.UTF_8)
                .use { writer ->
                    val csvMapper = mapper()
                    val schema = schema()
                    writeCSV(records, writer, csvMapper, schema)
                    Log.write(parentFolder, fileName)
                }
        }

        private fun readCSV(csvMapper: CsvMapper, schema: CsvSchema, inputStream: InputStream): List<ReferenceRecord> {
            val readList = csvMapper.readerFor(ReferenceRecord::class.java)
                .with(schema.withSkipFirstDataRow(true))
                .readValues<ReferenceRecord>(inputStream)
                .readAll()
            return readList
        }

        private fun writeCSV(list: List<ReferenceRecord>, writer: BufferedWriter, csvMapper: CsvMapper, schema: CsvSchema) {
            csvMapper.writer()
                .with(schema.withHeader())
                .writeValues(writer)
                .writeAll(list)
        }

        private fun mapper(): CsvMapper {
            val csvMapper = CsvMapper()
                .apply {
                    enable(CsvParser.Feature.TRIM_SPACES)
                    enable(CsvParser.Feature.SKIP_EMPTY_LINES)
                }
            return csvMapper
        }

        private fun schema(): CsvSchema = CsvSchema.builder()
            .addColumn("id")
            .addColumn("list1")
            .addColumn("list2")
            .addColumn("list3")
            .setNullValue("")
            .build()
    }
}
