import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {
    public static void main(String[] args) {
        String pagina = "https://www.google.com/search?q=cop+a+dolar";
        //Connecting to the web page
        Connection conexion = Jsoup.connect(pagina);
        //executing the get request
        Document documento = null;
        try {
            documento = conexion.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Retrieving the contents (body) of the web page
        String resultado = documento.body().text();

        String[] informacion = resultado.split(" ", -1);

        int contador = 0;

        String[] filtrado = new String[100];

        for(int i = 0; i < filtrado.length; i++) {
            filtrado[i] = informacion[i];
        }

        for(String palabra : filtrado){
            System.out.println(contador++);
            System.out.println(palabra);
        }
    }
}