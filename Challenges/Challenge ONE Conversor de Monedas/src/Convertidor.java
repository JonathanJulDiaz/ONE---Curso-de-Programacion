import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Convertidor {
    protected String[] opciones;
    protected String[] tipoPagina;

    private String[] getOpciones() {
        return opciones;
    }

    private String[] getTipoPagina() {
        return tipoPagina;
    }

    protected double seleccionar() {
        boolean otraVez;
        String valor;
        do {

            valor = JOptionPane.showInputDialog(null, "Ingrese el valor a convertir", "Convertidor de unidades", JOptionPane.INFORMATION_MESSAGE);

            if(valor.isBlank())
                otraVez = true;
            else
                otraVez = verificarNum(valor);

        } while (otraVez);

        return Double.parseDouble(valor);
    }

    private boolean verificarNum(String numero) {
        boolean valido = false;
        String caracterValido = "0123456789.";
        ArrayList<Character> nums = new ArrayList<>();

        for (int i = 0; i < caracterValido.length(); i++) {
            nums.add(caracterValido.charAt(i));
        }

        for (int i = 0; i < numero.length(); i++) {
            if (!nums.contains(numero.charAt(i))) {
                valido = true;
                JOptionPane.showMessageDialog(null, "Ingrese un valor valido", "Error", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }

        return valido;
    }

    protected Object tipoConvertidor(String unidad, String tema) {
        Object opcionesDeUnidad;
        do {
            opcionesDeUnidad = JOptionPane.showInputDialog(null, "Seleccione a que " + unidad + " convertir", tema, JOptionPane.INFORMATION_MESSAGE, null, getOpciones(), getOpciones()[0]);
        } while (salir(opcionesDeUnidad));

        return opcionesDeUnidad;
    }

    protected double extraerInformacion(String aConvertir, double valor) {
        String pagina = getTipoPagina()[0] + aConvertir;

        Connection conexion = Jsoup.connect(pagina);

        Document documento = null;
        try {
            documento = conexion.get();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudó establecer conexión a internet", "Error al intentar conectar", JOptionPane.ERROR_MESSAGE);
        }

        Element resultado = documento.body();

        Elements elemento = resultado.getElementsByClass(getTipoPagina()[1]);

        String valorString = elemento.html();

        if (valorString.indexOf(',') != -1) {
            int temp = valorString.indexOf(',');
            valorString = valorString.substring(0, temp) + valorString.substring(temp + 1);
        }

        valor = Double.parseDouble(valorString) * valor;

        String dosDecimal = String.valueOf(valor);

        if (dosDecimal.substring(dosDecimal.indexOf('.')).length() > 2
                && dosDecimal.indexOf('E') < 0)
            dosDecimal = dosDecimal.substring(0, dosDecimal.indexOf('.') + 3);

        valor = Double.parseDouble(dosDecimal);

        return valor;
    }

    protected String[] permutacion(String[] aPermutar, String separador) {
        String[] permutado = new String[(aPermutar.length * aPermutar.length) - aPermutar.length];

        int pos = 0;
        for (int i = 0; i < aPermutar.length; i++) {
            for (int j = 0; j < aPermutar.length; j++) {
                if (i != j) {
                    permutado[pos] = aPermutar[i] + separador + aPermutar[j];
                    pos++;
                }
            }
        }
        return permutado;
    }

    protected String doubleConComa(double numero) {
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        return formatea.format(numero);
    }

    protected void empezar(String unidad, String tema) {
        double valorAConvertir = seleccionar();

        Object opciones = tipoConvertidor(unidad, tema);

        convertir(opciones.toString(), valorAConvertir);

        repetir(unidad, tema);
    }

    protected void convertir(String opcion, double valor) {
    }

    protected void repetir(String unidad, String tema) {
        Object[] opciones = {"Si", "No"};

        int seguir = JOptionPane.showOptionDialog(null, "¿Desea continuar?", "¿Continuar?", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        if (seguir == 0)
            inicio();
        else
            System.exit(0);
    }

    private boolean salir(Object ventana) {
        return ventana == null || ventana == JOptionPane.UNINITIALIZED_VALUE;
    }

    protected void inicio() {
        Object[] listaConvertidor = {"Convertidor de Monedas", "Convertidor de Temperatura"};
        Object opcionesDeConvertidor;
        do {
            opcionesDeConvertidor = JOptionPane.showInputDialog(null, "Seleccione uno de los convertidores", "Convertidores", JOptionPane.INFORMATION_MESSAGE, null, listaConvertidor, listaConvertidor[0]);

        } while (salir(opcionesDeConvertidor));

        String tipoDeConvertidor = opcionesDeConvertidor.toString();

        String[] unidad = tipoDeConvertidor.split(" ");

        if (tipoDeConvertidor == listaConvertidor[0]) {
            new ConvertidorDeMonedas(unidad[unidad.length - 1].toLowerCase(), tipoDeConvertidor);
        } else if (tipoDeConvertidor == listaConvertidor[1]) {
            new ConvertidorDeTemperatura(unidad[unidad.length - 1].toLowerCase(), tipoDeConvertidor);
        }
    }
}