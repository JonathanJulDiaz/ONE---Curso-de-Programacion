import javax.swing.*;
public class ConvertidorDeTemperatura extends Convertidor implements Formula{
    public ConvertidorDeTemperatura(String unidad, String tema) {
        this.opciones = permutacion(new String[]{"Celcius", "Fahrenheit", "Kelvin"}, " a ");

        empezar(unidad, tema);
    }

    @Override
    public void repetir(String unidad, String tema) {
        super.repetir(unidad, tema);
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
    protected void convertir(String opcion, double valor) {
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
        double convertido;
        char primero = tipoFormula.charAt(0);
        char segundo = tipoFormula.charAt(tipoFormula.length()-1);

        if(primero == 'C' && segundo == 'F')
            convertido = (valor * ((double) 9 /5)) + 32;
        else if(primero == 'F' && segundo == 'C')
            convertido = (double) (5/9) * (valor - 32);
        else if(primero == 'C' && segundo == 'K')
            convertido = valor + 273.15;
        else if(primero == 'K' && segundo == 'C')
            convertido = valor - 273.15;
        else if(primero == 'F' && segundo == 'K')
            convertido = formula("F-C", valor) + 273.15;
        else
            convertido = formula("C-F", formula("K-C",valor));

        return convertido;
    }
}