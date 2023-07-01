import javax.swing.*;
public class ConvertidorDeTemperatura extends Convertidor{
    public ConvertidorDeTemperatura(String unidad, String tema) {
        this.opciones = permutar(new String[]{"Celcius", "Fahrenheit", "Kelvin"}, " a ");

        this.tipoPagina = new String[]{"https://www.google.com/finance/quote/", "YMlKec fxKbKc"};
        // ARREGLAR ESTO, SE SUPONE QUE USA FORMULAS, NO PAGINAS ARREGLAR
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

    private String[] permutar(String[] divisas, String separador) {
        return permutacion(divisas, separador);
    }

    @Override
    protected void convertir(String opcion, double valor) {

    }
}