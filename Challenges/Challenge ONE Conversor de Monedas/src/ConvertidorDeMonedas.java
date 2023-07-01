import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

public class ConvertidorDeMonedas extends Convertidor{

    public ConvertidorDeMonedas(String unidad, String tema) {
        this.opciones = new String[]{"COP a Dólares", "COP a Euros", "COP a Libras Esterlinas", "COP a Yen Japonés", "COP a Won Sur-coreano", "Dólares a COP", "Euros a COP", "Libras Esterlinas a COP", "Yen Japonés a COP", "Won Sur-coreano a COP"};
        empezar(unidad, tema);
    }

    @Override
    public void repetir(String unidad, String tema){
        super.repetir(unidad, tema);
    }

    @Override
    public void empezar(String unidad, String tema){
        double valorAConvertir = seleccionar();

        // Buscaar en la pagina la frase de una divisa a otra

        Object opcionesDeMoneda = tipoConvertidor(unidad, tema);

        convertir(opcionesDeMoneda.toString(), valorAConvertir);

        repetir(unidad, tema);
    }

    private void convertir(String opcion, double valor) {
        if (opcion.equals(this.opciones[0])) {
            JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-USD", valor) + " dolares");
        } else if (opcion.equals(this.opciones[1])) {
            JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-EUR", valor) + " euros");
        } else if (opcion.equals(this.opciones[2])) {
            JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-GBP", valor) + " libras esterlinas");
        } else if (opcion.equals(this.opciones[3])) {
            JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-JPY", valor) + " yenes japoneses");
        } else if (opcion.equals(this.opciones[4])) {
            JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-KRW", valor) + " wones sur-coreano");
        } else if (opcion.equals(this.opciones[5])) {
            JOptionPane.showMessageDialog(null, valor + " doláres es igual a " + extraerInformacion("USD-COP", valor) + " pesos colombianos");
        } else if (opcion.equals(this.opciones[6])) {
            JOptionPane.showMessageDialog(null, valor + " euros es igual a " + extraerInformacion("EUR-COP", valor) + " pesos colombianos");
        } else if (opcion.equals(this.opciones[7])) {
            JOptionPane.showMessageDialog(null, valor + " libras esterlinas es igual a " + extraerInformacion("GBP-COP", valor) + " pesos colombianos");
        } else if (opcion.equals(this.opciones[8])) {
            JOptionPane.showMessageDialog(null, valor + " yenes japoneses es igual a " + extraerInformacion("JPY-COP", valor) + " pesos colombianos");
        } else if (opcion.equals(this.opciones[9])) {
            JOptionPane.showMessageDialog(null, valor + " wones sur-coreano es igual a " + extraerInformacion("KRW-COP", valor) + " pesos colombianos");
        }
    }

    private static double extraerInformacion(String aConvertir, double valor) {
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
}