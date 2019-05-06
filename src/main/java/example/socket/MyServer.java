package example.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
    private static ArrayList<Socket> listConnectedSocket = new ArrayList<>();

    private static void sendMessageToClients(String message) {
//        System.out.println("Bắn dữ liệu về cho tất cả client với nội dung: " + message);
        if (listConnectedSocket == null || listConnectedSocket.size() == 0) {
            return;
        }
        try {
            for (Socket socket :
                    listConnectedSocket) {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write(message);
                bw.newLine();
                bw.flush();
            }
        } catch (Exception ex) {
            System.err.println("Có lỗi xảy ra, vui lòng thử lại sau!");
        }
    }

    public static void main(String[] args) {
        try {
            int port = 8888;
            // Mở cổng server, nhận kết nối từ client.
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Mở server với cổng: " + port);
            System.out.println("Chờ client kết nối...");
            // Client kết nối đến server.
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client với IP: " + socket.getInetAddress() + " kết nối thành công.");
                listConnectedSocket.add(socket);
                new Thread(() -> {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        while (true) {
                            String line = br.readLine();
                            if (line != null) {
                                String message = socket.getInetAddress() + " say: " + line;
                                System.out.println(message);
                                sendMessageToClients(message);
                                if (line.equals("bye")) {
                                    break;
                                }
                            }
                        }
                    } catch (Exception ex) {
                        System.err.println("Có lỗi xảy ra, vui lòng thử lại.");
                    }
                }).start();
            }
        } catch (Exception ex) {
            System.err.println("Có lỗi xảy ra, vui lòng thử lại.");
        }

    }
}
