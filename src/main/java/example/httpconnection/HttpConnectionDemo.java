package example.httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnectionDemo {
    public static void main(String[] args) {
        String websiteUrl = "https://www.w3schools.com/";
        try {
            URL urlObject = new URL(websiteUrl);
            URLConnection urlConnection = urlObject.openConnection();
            InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
            // chuyển sang BufferedReader để đọc theo từng dòng.
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            // đến khi dữ liệu trả về null thì là đọc hết.
            while (line != null) {
                System.out.println(line);
                // đọc dòng tiếp theo hoặc sẽ rơi vào end game. Tạch!
                line = br.readLine();
            }
            br.close();
            isr.close();
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra, vui lòng thử lại.");
            e.printStackTrace();
        }
    }
}
