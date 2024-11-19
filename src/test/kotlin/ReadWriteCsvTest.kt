import org.example.ReferenceRecordJacksonIO
import org.example.ReferenceRecordDataframeIO
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.Test

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ReadWriteCsvTest {

    private class Config {
        companion object {
            const val TARGET_FOLDER = "src/test/resources/"
            const val DATA_FRAME_FILE_NAME = "dataframe.csv"
            const val JACKSON_FILE_NAME = "jackson.csv"
        }
    }

    @Test
    @Order(1)
    fun readJacksonWriteDataFrame() {
        val references = ReferenceRecordJacksonIO.read(Config.JACKSON_FILE_NAME, Config.TARGET_FOLDER)
        ReferenceRecordDataframeIO.write(references, Config.DATA_FRAME_FILE_NAME, Config.TARGET_FOLDER)
    }

    @Test
    @Order(2)
    fun readWriteJackson() {
        val references = ReferenceRecordJacksonIO.read(Config.JACKSON_FILE_NAME, Config.TARGET_FOLDER)
        ReferenceRecordJacksonIO.write(references, Config.JACKSON_FILE_NAME, Config.TARGET_FOLDER)
    }

    @Test
    @Order(3)
    fun readWriteDataFrame() {
        val references = ReferenceRecordDataframeIO.read(Config.DATA_FRAME_FILE_NAME, Config.TARGET_FOLDER)
        ReferenceRecordDataframeIO.write(references, Config.DATA_FRAME_FILE_NAME, Config.TARGET_FOLDER)
    }
}
