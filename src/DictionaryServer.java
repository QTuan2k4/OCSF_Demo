import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DictionaryServer extends AbstractServer {

    private Map<String, String> dictionary;

    public DictionaryServer(int port) {
        super(port);
        // Khởi tạo một từ điển đơn giản
        dictionary = new HashMap<>();
        dictionary.put("apple", "Táo");
        dictionary.put("banana", "Chuối");
        dictionary.put("computer", "Máy tính");

    }

    @Override
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        String word = (String) msg; 
        String definition = dictionary.getOrDefault(word.toLowerCase(), "Word not found in dictionary.");
        
        try {
            client.sendToClient("Definition of '" + word + "': " + definition);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 12345; 
        DictionaryServer server = new DictionaryServer(port);
        
        try {
            server.listen(); 
            System.out.println("Dictionary Server started on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
