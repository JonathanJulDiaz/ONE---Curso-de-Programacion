import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.JOptionPane;

public class Main {

    public static void repetir(){
        while(true){
            inicio();
            if(JOptionPane.showInternalConfirmDialog(null,"¿Desea continuar?", "Seguir convirtiendo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) != 0){
                System.exit(0);
            }
        }
    }
    private static void inicio(){
        Object[] listaConvertidor = {"Convertidor de Monedas", "Convertidor de Temperatura"};

        Object opcionesDeConvertidor = JOptionPane.showInputDialog(null,"Seleccione uno de los convertidores", "Convertidores",JOptionPane.INFORMATION_MESSAGE, null, listaConvertidor, listaConvertidor[0]);

        String valor = JOptionPane.showInputDialog("Ingrese el valor a convertir");

        double valorAConvertir = 0.0;

        try {
            valorAConvertir = Double.parseDouble(valor);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        if(opcionesDeConvertidor.toString() == listaConvertidor[0]){
            monedas(valorAConvertir);
        } else {
            temperatura(valorAConvertir);
        }
    }

    private static void monedas(double valor){
        Object[] conversiones = {"COP a Dólares", "COP a Euros", "COP a Libras Esterlinas", "COP a Yen Japonés", "COP a Won Sur-coreano", "Dólares a COP", "Euros a COP", "Libras Esterlinas a COP", "Yen Japonés a COP", "Won Sur-coreano a COP"};

        Object opcionesDeConvertidor = JOptionPane.showInputDialog(null,"Seleccione a que moneda convertir", "Conversion de Monedas", JOptionPane.INFORMATION_MESSAGE, null, conversiones, conversiones[0]);

        convertir(opcionesDeConvertidor.toString(), valor);
    }

    private static void convertir(String opcion, double valor){
        switch (opcion){
            case "COP a Dólares":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-USD")*valor + " dolares");
                break;
            case "COP a Euros":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-EUR")*valor + " euros");
                break;
            case "COP a Libras Esterlinas":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-GBP")*valor + " libras esterlinas");
                break;
            case "COP a Yen japonés":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-JPY")*valor + " yenes japoneses");
                break;
            case "COP a Won Sur-coreano":
                JOptionPane.showMessageDialog(null, valor + " pesos colombianos es igual a " + extraerInformacion("COP-KRW")*valor + " wones sur-coreano");
                break;
            case "Doláres a COP":
                JOptionPane.showMessageDialog(null, valor + " doláres es igual a " + extraerInformacion("USD-COP")*valor + " pesos colombianos");
                break;
            case "Euros a COP":
                JOptionPane.showMessageDialog(null, valor + " euros es igual a " + extraerInformacion("EUR-COP")*valor + " pesos colombianos");
                break;
            case "Libras Esterlinas a COP":
                JOptionPane.showMessageDialog(null, valor + " libras esterlinas es igual a " + extraerInformacion("GBP-COP")*valor + " pesos colombianos");
                break;
            case "Yen japónes a COP":
                JOptionPane.showMessageDialog(null, valor + " yenes japoneses es igual a " + extraerInformacion("JPY-COP")*valor + " pesos colombianos");
                break;
            case "Won Sur-coreano a COP":
                JOptionPane.showMessageDialog(null, valor + " wones sur-coreano es igual a " + extraerInformacion("KRW-COP")*valor + " pesos colombianos");
                break;
        }
    }

    private static double extraerInformacion(String aConvertir){
        String pagina = "https://www.google.com/finance/quote/" + aConvertir;

        Connection conexion = Jsoup.connect(pagina);

        Document documento = null;
        try {
            documento = conexion.get();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error",JOptionPane.ERROR_MESSAGE);

            e.printStackTrace();
        }
        //Retrieving the contents (body) of the web page
        Element resultado = documento.body();

        Elements valor = resultado.getElementsByClass("YMlKec fxKbKc");

        return Double.parseDouble(valor.html());
    }

    private static void temperatura(double valor){
        Object[] conversiones = {"Grado Celcius a Fahrenheit", "Grado"};

        Object opcionesDeConvertidor = JOptionPane.showInputDialog(null,"Seleccione a que moneda convertir", "Conversion de Monedas", JOptionPane.INFORMATION_MESSAGE, null, conversiones, conversiones[0]);
    }


    public static void main(String[] args) {
        repetir();
    }
}