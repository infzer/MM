enum Color {
    ROJO, VERDE, AMARILLO, PURPURA
}

public class Jugada {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final char CUADRADO = '\u25A0';

    private Color[] fichas;

    public Jugada(String cadena) {

        this.fichas = new Color[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            switch (cadena.charAt(i)) {
                case 'R':
                    fichas[i] = Color.ROJO;
                    break;
                case 'V':
                    fichas[i] = Color.VERDE;
                    break;
                case 'A':
                    fichas[i] = Color.AMARILLO;
                    break;
                case 'P':
                    fichas[i] = Color.PURPURA;
                    break;
                default:
                    throw new IllegalArgumentException("Carácter no válido: " + cadena.charAt(i));
            }
        }

    }

    public Jugada(int numFichas) { // jugada oculta
        // TODO
        fichas = new Color[numFichas];

        for (int i = 0; i < numFichas; i++) {
            int randomColor = (int) (Math.random() * (3 + 1)); // sumas uno mas del rango que quieres
            switch (randomColor) {
                case 0:
                    fichas[i] = Color.ROJO;
                    break;
                case 1:
                    fichas[i] = Color.VERDE;
                    break;
                case 2:
                    fichas[i] = Color.AMARILLO;
                    break;
                case 3:
                    fichas[i] = Color.PURPURA;
                    break;
            }
        }

    }

    public Pistas comprobar(Jugada oculta) {
        // TODO

        boolean[] marcadasActual = new boolean[this.fichas.length];
        boolean[] marcadasOculta = new boolean[this.fichas.length];
        int aciertos = 0;
        int descolocadas = 0;

        // Contar aciertos
        for (int i = 0; i < this.fichas.length; i++) {
            if (this.fichas[i] == oculta.fichas[i]) {
                marcadasActual[i] = true;
                marcadasOculta[i] = true;
                aciertos++;
            }
        }

        for (int i = 0; i < this.fichas.length; i++) {
            if (!marcadasActual[i]) {
                for (int j = 0; j < oculta.fichas.length; j++) {
                    if (!marcadasOculta[j] && this.fichas[i] == oculta.fichas[j]) {
                        descolocadas++;
                        marcadasOculta[j] = true;
                        break;
                    }
                }
            }
        }

        return new Pistas(aciertos, descolocadas);
    }


    public void visualizar() {
        for (int i = 0; i < fichas.length; i++) {
            switch (fichas[i]) {
                case ROJO:
                    System.out.print(ANSI_RED + CUADRADO + " ");
                    break;
                case VERDE:
                    System.out.print(ANSI_GREEN + CUADRADO + " ");
                    break;
                case AMARILLO:
                    System.out.print(ANSI_YELLOW + CUADRADO + " ");
                    break;
                case PURPURA:
                    System.out.print(ANSI_PURPLE + CUADRADO + " ");
                    break;
            }
        }
        System.out.print(ANSI_BLACK);
    }

    public String toString() {
        String resultado = "";
        for (int i = 0; i < fichas.length; i++) {
            switch (fichas[i]) {
                case ROJO:
                    resultado += "R";
                    break;
                case VERDE:
                    resultado += "V";
                    break;
                case AMARILLO:
                    resultado += "A";
                    break;
                case PURPURA:
                    resultado += "P";
                    break;
            }
        }
        return resultado;
    }

}
