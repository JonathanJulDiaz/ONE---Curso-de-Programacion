import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

public class ConvertidorDeMonedas extends Convertidor{

    public ConvertidorDeMonedas() {
        this.opciones = new String[]{"COP a Dólares", "COP a Euros", "COP a Libras Esterlinas", "COP a Yen Japonés", "COP a Won Sur-coreano", "Dólares a COP", "Euros a COP", "Libras Esterlinas a COP", "Yen Japonés a COP", "Won Sur-coreano a COP"};
        empezar();
    }

    @Override
    public void repetir(){
        super.repetir();
    }

    @Override
    public void empezar(){
        double valorAConvertir = seleccionar();

        Object opcionesDeMoneda;
        do {
            opcionesDeMoneda = JOptionPane.showInputDialog(null, "Seleccione a que moneda convertir", "Conversion de Monedas", JOptionPane.INFORMATION_MESSAGE, null, this.getOpciones(), this.getOpciones()[0]);
        } while (salir(opcionesDeMoneda));

        convertir(opcionesDeMoneda.toString(), valorAConvertir);

        repetir();
    }

    private static void convertir(String opcion, double valor) {
        switch (opcion) {
            case "COP a Dólares":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-USD", valor) + " dolares");
                break;
            case "COP a Euros":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-EUR", valor) + " euros");
                break;
            case "COP a Libras Esterlinas":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-GBP", valor) + " libras esterlinas");
                break;
            case "COP a Yen japonés":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-JPY", valor) + " yenes japoneses");
                break;
            case "COP a Won Sur-coreano":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-KRW", valor) + " wones sur-coreano");
                break;
            case "Doláres a COP":
                JOptionPane.showMessageDialog(null, valor + " doláres es igual a " + extraerInformacion("USD-COP", valor) + " pesos colombianos");
                break;
            case "Euros a COP":
                JOptionPane.showMessageDialog(null, valor + " euros es igual a " + extraerInformacion("EUR-COP", valor) + " pesos colombianos");
                break;
            case "Libras Esterlinas a COP":
                JOptionPane.showMessageDialog(null, valor + " libras esterlinas es igual a " + extraerInformacion("GBP-COP", valor) + " pesos colombianos");
                break;
            case "Yen japónes a COP":
                JOptionPane.showMessageDialog(null, valor + " yenes japoneses es igual a " + extraerInformacion("JPY-COP", valor) + " pesos colombianos");
                break;
            case "Won Sur-coreano a COP":
                JOptionPane.showMessageDialog(null, valor + " wones sur-coreano es igual a " + extraerInformacion("KRW-COP", valor) + " pesos colombianos");
                break;
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

        valor = Double.parseDouble(elemento.html()) * valor;

        String dosDecimal = "" + valor;

        if(dosDecimal.length() > 4)
            dosDecimal = dosDecimal.substring(0, dosDecimal.indexOf('.') + 3);

        valor = Double.parseDouble(dosDecimal);

        return valor;
    }

    @Override
    public  boolean salir(Object ventana) {
        return super.salir(ventana);
    }
}