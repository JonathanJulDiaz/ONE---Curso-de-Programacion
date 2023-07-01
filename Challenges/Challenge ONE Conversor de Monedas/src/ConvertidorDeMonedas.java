import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

public class ConvertidorDeMonedas extends Convertidor{

    public ConvertidorDeMonedas(String unidad, String tema) {
        this.opciones = conseguirMonedas(new String[] {"Peso colombiano", "Dólares", "Euros", "Libras esterlinas", "Yen japonés", "Won sur-coreano", "Colón costarricense", "Peso mexicano", "Peso dominicano", "Sol peruano", "Peso argentino", "Peso chileno", "Quetzal guatemalteco", "Colón salvadorenyo", "Lempira hondurenyo", "Boliviano", "Bolívar venezolano", "Balbo panamenyo", "Guaraní paraguayo", "Gurde haitiano"}, " a ");

        empezar(unidad, tema);
    }

    @Override
    public void repetir(String unidad, String tema){
        super.repetir(unidad, tema);
    }

    @Override
    public void empezar(String unidad, String tema){
        double valorAConvertir = seleccionar();

        Object opcionesDeMoneda = tipoConvertidor(unidad, tema);

        convertir(opcionesDeMoneda.toString(), valorAConvertir);

        repetir(unidad, tema);
    }

    private void convertir(String opcion, double valor) {
        String[] simbols = {"COP", "USD", "EUR", "GBP", "JPY", "KRW", "CRC", "MXN", "DOP", "PEN", "ARS", "CLP", "GTQ", "SVC", "HNL", "BOB", "VES", "PAB", "PYG", "HTG"};

        String[] dosUnidades = permutacion(simbols, "-");

        for(int i = 0; i < this.opciones.length; i++){
            if(opcion.equals(this.opciones[i])){
                String primero = opcion.substring(0, opcion.indexOf(" a "));
                String segundo = opcion.substring(opcion.indexOf(" a ")+3);

                JOptionPane.showMessageDialog(null, valor + " unidades monetarias en " + primero + " son iguales \na " + extraerInformacion(dosUnidades[i], valor) + " unidades monetarias en " + segundo, opcion, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    private double extraerInformacion(String aConvertir, double valor) {
        String pagina = "https://www.google.com/finance/quote/" + aConvertir;

        Connection conexion = Jsoup.connect(pagina);

        Document documento = null;
        try {
            documento = conexion.get();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudó establecer conexión a internet", "Error al intentar conectar", JOptionPane.ERROR_MESSAGE);
        }
        //Retrieving the contents (body) of the web page
        Element resultado = documento.body();

        Elements elemento = resultado.getElementsByClass("YMlKec fxKbKc");

        String valorString = elemento.html();

        if(valorString.indexOf(',') != -1){
            int temp = valorString.indexOf(',');
            valorString = valorString.substring(0, temp) + valorString.substring(temp+1);
        }

        valor = Double.parseDouble(valorString) * valor;

        String dosDecimal = String.valueOf(valor);

        if(dosDecimal.substring(dosDecimal.indexOf('.')).length() > 2)
            dosDecimal = dosDecimal.substring(0, dosDecimal.indexOf('.') + 3);

        valor = Double.parseDouble(dosDecimal);

        return valor;
    }

    private String[] conseguirMonedas(String[] divisas, String separador){
        return permutacion(divisas, separador);
    }
}