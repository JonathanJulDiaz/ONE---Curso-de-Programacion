import javax.swing.*;
import java.lang.reflect.Method;

public class Convertidor {
    protected String[] opciones;

    public String[] getOpciones() {
        return opciones;
    }

    public double seleccionar(){
        boolean otraVez;
        String valor;
        do {
            otraVez = false;

            valor = JOptionPane.showInputDialog("Ingrese el valor a convertir", 0.0);

            if (valor == null || valor.isBlank()) {
                otraVez = true;
                JOptionPane.showMessageDialog(null, "Ingrese un valor valido", "Error", JOptionPane.WARNING_MESSAGE);
            }

        } while (otraVez);

        return Double.parseDouble(valor);
    }

    public void empezar(){}

    public void repetir() {
        Object[] opciones = {"Si", "No"};

        int seguir = JOptionPane.showOptionDialog(null, "¿Desea continuar?", "¿Continuar?", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        if (seguir == 0)
            empezar();
        else
            System.exit(0);
    }

    protected boolean salir(Object ventana) {
        return ventana == null || ventana == JOptionPane.UNINITIALIZED_VALUE;
    }
}