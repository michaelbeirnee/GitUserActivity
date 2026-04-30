import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CLI {
    
    public static void main(String[] args){

        if(args.length != 1){
            System.out.println("Usage : java CLI <username>"); 
            return;
        }

        String GITHUB_API_URL = "https://api.github.com/users/" + args[0] + "/events";  

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(GITHUB_API_URL))
                .header("Accept" , "application/vnd.github+json")
                .header("User-Agent", "GitHub-User-Activity-CLI")
                .GET()
                .build();
            HttpResponse<String> response = client.send(
                request, 
                HttpResponse.BodyHandlers.ofString()
            );

            if(response.statusCode() == 200){
                System.out.println(response.body());
            }else{
                System.out.println("error" + response.statusCode()); 
            }

        } catch (URISyntaxException uriSyntaxException){
            uriSyntaxException.printStackTrace();
            //print stack trace for debugging
        } catch (IOException ioException){
            ioException.printStackTrace();
        } catch (InterruptedException interruptedException){
            Thread.currentThread().interrupt();
            interruptedException.printStackTrace();
        }
    }

}
