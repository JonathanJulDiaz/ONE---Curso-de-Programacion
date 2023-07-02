import javax.swing.*;
public class ConvertidorDeTemperatura extends Convertidor implements Formula{
    public ConvertidorDeTemperatura(String unidad, String tema) {
        this.opciones = permutacion(new String[]{"Celcius", "Fahrenheit", "Kelvin"}, " a ");

        empezar(unidad, tema);
    }

    @Override
    public void repetir() {
        super.repetir();
    }

    @Override
    public void empezar(String unidad, String tema) {
        super.empezar(unidad, tema);
    }

    @Override
    protected String[] permutacion(String[] divisas, String separador) {
        return super.permutacion(divisas, separador);
    }

    @Override
    protected void mostrar(String opcion, double valor) {
        for (String paso : this.opciones) {
            if (opcion.equals(paso)) {
                char primero = opcion.substring(0, opcion.indexOf(" a ")).charAt(0);
                char segundo = opcion.substring(opcion.indexOf(" a ") + 3).charAt(0);

                JOptionPane.showMessageDialog(null, super.doubleConComa(valor) + " " + primero + " son iguales a " + super.doubleConComa(formula(primero + "-" + segundo, valor)) + " " + segundo, opcion, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    @Override
    public double formula(String tipoFormula, double valor) {
        return 0;
    }

    @Override
    public double formula(String tipoFormula, double valor1, double valor2) {
        return 0;
    }
}