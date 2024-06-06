import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EndpointTester {
    public static void main(String[] args) {
        // URL mẫu để kiểm tra endpoint
        String url = "http://localhost:8080/ISMS/downloadFile?reportFile=TEST.xlsx";

        try {
            // Gửi yêu cầu GET
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            // Kiểm tra mã phản hồi
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Request was successful.");
                try (InputStream inputStream = connection.getInputStream()) {
                    // Đọc nội dung phản hồi nếu cần thiết
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        System.out.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                System.out.println("Request failed. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
