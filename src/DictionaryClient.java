import ocsf.client.AbstractClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DictionaryClient extends AbstractClient {

    public DictionaryClient(String host, int port) {
        super(host, port);
    }

    @Override
    public void handleMessageFromServer(Object msg) {
        System.out.println(msg);  
    }

    public static void main(String[] args) {
        String host = "localhost"; 
        int port = 12345; 
        
        DictionaryClient client = new DictionaryClient(host, port);
        
        try {
            client.openConnection(); 
            System.out.println("Connected to server at " + host + ":" + port);
            
           
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String word;
            
            while (true) {
                System.out.print("Enter word to look up (or type 'exit' to quit): ");
                word = reader.readLine();
                if (word.equalsIgnoreCase("exit")) {
                    break;
                }
                client.sendToServer(word); 
            }
            
            client.closeConnection(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
