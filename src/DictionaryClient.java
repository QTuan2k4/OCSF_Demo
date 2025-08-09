import ocsf.client.AbstractClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DictionaryClient extends AbstractClient {

    public DictionaryClient(String host, int port) {
        super(host, port);
    }

    @Override
    public void handleMessageFromServer(Object msg) {
        System.out.println(msg);  // Hiển thị định nghĩa từ nhận được từ server
    }

    public static void main(String[] args) {
        String host = "localhost"; // Server host
        int port = 12345; // Server port
        
        DictionaryClient client = new DictionaryClient(host, port);
        
        try {
            client.openConnection(); // Mở kết nối đến server
            System.out.println("Connected to server at " + host + ":" + port);
            
            // Đọc từ cần tra cứu từ bàn phím và gửi tới server
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String word;
            
            while (true) {
                System.out.print("Enter word to look up (or type 'exit' to quit): ");
                word = reader.readLine();
                if (word.equalsIgnoreCase("exit")) {
                    break;
                }
                client.sendToServer(word); // Gửi từ cần tra cứu tới server
            }
            
            client.closeConnection(); // Đóng kết nối khi kết thúc
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
