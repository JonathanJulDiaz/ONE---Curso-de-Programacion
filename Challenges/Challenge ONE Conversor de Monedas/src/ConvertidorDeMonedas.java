import javax.swing.*;

public class ConvertidorDeMonedas extends Convertidor {

    public ConvertidorDeMonedas(String unidad, String tema) {
        this.opciones = permutacion(new String[]{"Peso colombiano", "Dólares", "Euros", "Libras esterlinas", "Yen japonés", "Won sur-coreano", "Colón costarricense", "Peso mexicano", "Peso dominicano", "Sol peruano", "Peso argentino", "Peso chileno", "Quetzal guatemalteco", "Colón salvadorenyo", "Lempira hondurenyo", "Boliviano", "Bolívar venezolano", "Balbo panamenyo", "Guaraní paraguayo", "Gurde haitiano"}, " a ");

        this.tipoPagina = new String[]{"https://www.google.com/finance/quote/", "YMlKec fxKbKc"};

        empezar(unidad, tema);
    }

    @Override
    protected void repetir(String unidad, String tema) {
        super.repetir(unidad, tema);
    }

    @Override
    protected void empezar(String unidad, String tema) {
        super.empezar(unidad, tema);
    }

    @Override
    protected void convertir(String opcion, double valor) {
        String[] simbols = {"COP", "USD", "EUR", "GBP", "JPY", "KRW", "CRC", "MXN", "DOP", "PEN", "ARS", "CLP", "GTQ", "SVC", "HNL", "BOB", "VES", "PAB", "PYG", "HTG"};

        String[] dosUnidades = permutacion(simbols, "-");

        for (int i = 0; i < this.opciones.length; i++) {
            if (opcion.equals(this.opciones[i])) {
                String primero = opcion.substring(0, opcion.indexOf(" a "));
                String segundo = opcion.substring(opcion.indexOf(" a ") + 3);

                JOptionPane.showMessageDialog(null, super.doubleConComa(valor) + " unidades monetarias en " + primero + " son iguales \na " + super.doubleConComa(extraerInformacion(dosUnidades[i], valor)) + " unidades monetarias en " + segundo, opcion, JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    @Override
    protected double extraerInformacion(String aConvertir, double valor) {
        return super.extraerInformacion(aConvertir, valor);
    }

    @Override
    protected String[] permutacion(String[] divisas, String separador) {
        return super.permutacion(divisas, separador);
    }
}