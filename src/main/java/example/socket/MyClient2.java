package example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient2 {
    public static void main(String[] args) {
        try {
            String host = "localhost";
            int port = 8888;
            Socket socket = new Socket(host, port);
            System.out.println("Kết nối thành công đến server: " + host + ":" + port);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Gửi dữ liệu lên server.
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Nhập nội dung và gõ enter để gửi message.");
                        while (true) {
//                            System.out.println("Nhập message để gửi: ");
                            String text = scanner.nextLine();
                            bw.write(text);
                            bw.newLine();
                            bw.flush();
//                            System.out.println("Gửi message thành công.");
                            if (text.equals("bye")) {
                                break;
                            }
                        }
                        bw.close();
                    } catch (Exception ex) {
                        System.err.println("Có lỗi xảy ra trong quá trình gửi dữ liệu lên server.");
                    }
                }
            }).start();
            ;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // Đọc dữ liệu từ server.
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String line = br.readLine();
                        while (true) {
                            if (line != null) {
//                                System.out.println("Server response: ");
                                System.out.println(line);
                                line = br.readLine();
                            }
                        }
                    } catch (Exception ex) {
                        System.err.println("Có lỗi xảy ra trong quá trình đọc dữ liệu từ server.");
                    }
                }
            }).start();
        } catch (Exception ex) {
            System.err.println("Có lỗi xảy ra, vui lòng thử lại sau.");
            ex.printStackTrace();
        }
    }
}
