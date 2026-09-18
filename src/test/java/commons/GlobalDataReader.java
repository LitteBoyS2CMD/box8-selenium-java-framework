package commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class GlobalDataReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 1. HÀM ĐỌC 1 OBJECT JSON ĐƠN
    public static <T> T getJsonData(String fileName, Class<T> clazz) {
        try {
            InputStream inputStream = GlobalDataReader.class.getClassLoader()
                    .getResourceAsStream(fileName);

            if (inputStream == null) {
                throw new RuntimeException("Không tìm thấy file: " + fileName + " trong src/test/resources!");
            }

            return MAPPER.readValue(inputStream, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file dữ liệu JSON: " + fileName, e);
        }
    }

    // 2. HÀM ĐỌC MẢNG JSON ARRAY [...] (Dùng cho @DataProvider)
    public static <T> T[] getJsonDataArray(String fileName, Class<T[]> clazz) {
        try {
            InputStream inputStream = GlobalDataReader.class.getClassLoader()
                    .getResourceAsStream(fileName);

            if (inputStream == null) {
                throw new RuntimeException("Không tìm thấy file: " + fileName + " trong src/test/resources!");
            }

            return MAPPER.readValue(inputStream, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi đọc file JSON Array: " + e.getMessage());
        }
    }
}