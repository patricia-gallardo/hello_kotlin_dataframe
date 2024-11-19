package org.example

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.io.BufferedWriter
import java.io.InputStream


data class ReferenceRecord(
    val id: String? = null,
    val list1: List<String>? = null,
    val list2: List<String>? = null,
    val list3: List<String>? = null
) {
    companion object {

        fun readCSV(csvMapper: CsvMapper, schema: CsvSchema, inputStream: InputStream): List<ReferenceRecord> {
            val readList = csvMapper.readerFor(ReferenceRecord::class.java)
                .with(schema.withSkipFirstDataRow(true))
                .readValues<ReferenceRecord>(inputStream)
                .readAll()
            return readList
        }

        fun writeCSV(list: List<ReferenceRecord>, writer: BufferedWriter, csvMapper: CsvMapper, schema: CsvSchema) {
            csvMapper.writer()
                .with(schema.withHeader())
                .writeValues(writer)
                .writeAll(list)
        }

        fun mapper(): CsvMapper {
            val csvMapper = CsvMapper()
                .apply {
                    enable(CsvParser.Feature.TRIM_SPACES)
                    enable(CsvParser.Feature.SKIP_EMPTY_LINES)
                }
            return csvMapper
        }

        fun schema(): CsvSchema = CsvSchema.builder()
            .addColumn("id")
            .addColumn("list1")
            .addColumn("list2")
            .addColumn("list3")
            .setNullValue("")
            .build()
    }
}
