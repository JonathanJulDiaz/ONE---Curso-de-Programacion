import javax.swing.*;

public class Convertidor {
    protected String[] opciones;

    private String[] getOpciones() {
        return opciones;
    }

    protected double seleccionar(){
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

    protected Object tipoConvertidor(String unidad, String tema){
        Object opcionesDeMoneda;
        do {
            opcionesDeMoneda = JOptionPane.showInputDialog(null, "Seleccione a que " + unidad + " convertir", tema, JOptionPane.INFORMATION_MESSAGE, null, getOpciones(), getOpciones()[0]);
        } while (salir(opcionesDeMoneda));

        return opcionesDeMoneda;
    }

    public void empezar(String unidad, String tema){}

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

    protected void inicio(){
        Object[] listaConvertidor = {"Convertidor de Monedas", "Convertidor de Temperatura"};
        Object opcionesDeConvertidor;
        do {
            opcionesDeConvertidor = JOptionPane.showInputDialog(null, "Seleccione uno de los convertidores", "Convertidores", JOptionPane.INFORMATION_MESSAGE, null, listaConvertidor, listaConvertidor[0]);

        } while (salir(opcionesDeConvertidor));

        String tipoDeConvertidor = opcionesDeConvertidor.toString();

        String[] unidad = tipoDeConvertidor.split(" ");

        if (tipoDeConvertidor == listaConvertidor[0]) {
            new ConvertidorDeMonedas(unidad[unidad.length-1].toLowerCase(), opcionesDeConvertidor.toString());
        } else if (tipoDeConvertidor == listaConvertidor[1]) {
            System.out.println("Temperatura");
        }
    }
}