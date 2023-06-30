import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        String pagina = "https://www.google.com/finance/quote/COP-USD";
        //Connecting to the web page
        Connection conexion = Jsoup.connect(pagina);
        //executing the get request
        try {
            Document documento = conexion.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Retrieving the contents (body) of the web page
        Element resultado = documento.body();

        Elements valor = resultado.getElementsByClass("YMlKec fxKbKc");

        System.out.println("USD a COP: " + valor.html());
    }
}