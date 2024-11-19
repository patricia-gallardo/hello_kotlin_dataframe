package org.example

import org.jetbrains.kotlinx.dataframe.codeGen.generateCode

private class Config {
    companion object {
        const val TARGET_FOLDER = "src/test/resources/"
        const val DATA_FRAME_FILE_NAME = "dataframe.csv"
    }
}

// based on https://github.com/Kotlin/dataframe/discussions/717
fun main() {
    val df = ReferenceRecordDataframeIO.readDataFrame(Config.DATA_FRAME_FILE_NAME, Config.TARGET_FOLDER)
    println(df.generateCode(markerName = "ReferenceRecord"))
}
