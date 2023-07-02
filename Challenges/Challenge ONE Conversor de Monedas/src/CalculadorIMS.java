import javax.swing.*;

public class CalculadorIMS extends Convertidor{

    public CalculadorIMS(){
        this.opciones = new String[] {"Bajo peso", "Normal", "Sobrepeso", "Obesidad"};
        this.empezar();
    }

    private void empezar() {
        double ims = this.ingresarValor();
        String opcion = clasificar(ims);
        mostrar(opcion, ims);
        repetir();
    }

    @Override
    protected void repetir() {
        super.repetir();
    }

    @Override
    protected double ingresarValor() {
        JTextField peso = new JTextField();
        JTextField altura = new JTextField();

        Object[] contenido = {"Peso (kg):", peso, "Altura (m):", altura};

        boolean noBlanco;
        double ims = 0;
        do {
            noBlanco = false;
            int opcion = JOptionPane.showConfirmDialog(null, contenido, "Calculadora de IMS", JOptionPane.OK_CANCEL_OPTION);

            if (opcion == JOptionPane.OK_OPTION) {
                String p = peso.getText();
                String al = altura.getText();
                if((p.isBlank() || al.isBlank()) || (verificarNum(p) || verificarNum(al)))
                    noBlanco = true;
                else {
                    double kg = Double.parseDouble(peso.getText());
                    double m = Double.parseDouble(altura.getText());
                    ims = kg / (m * m);
                }
            } else
                System.exit(0);
        } while (noBlanco);

        return ims;
    }

    @Override
    protected void mostrar(String opcion, double valor) {
        for (String paso : this.opciones) {
            if (opcion.equals(paso)) {
                JOptionPane.showMessageDialog(null, "Su IMS es de " + super.doubleConComa(valor) + " y su peso esta en la categoría de " + opcion, "Calculador de Indice de Masa Corporal", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    private String clasificar(double ims){
        String nivel;
        if(ims < 18.5)
            nivel = "Bajo peso";
        else if(ims >= 18.5 && ims <= 24.9)
            nivel = "Normal";
        else if(ims > 24.9 && ims <= 29.9)
            nivel = "Sobrepeso";
        else
            nivel = "Obesidad";
        return nivel;
    }
}