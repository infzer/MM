public class Tablero {
    private final static int MAX_JUGADAS = 10;

    private Jugada[] jugadas;
    private Pistas[] pistas;
    private int numJugadas;

    public Tablero() {
       // TODO
        this.jugadas = new Jugada[MAX_JUGADAS];
        this.pistas = new Pistas[MAX_JUGADAS];
        this.numJugadas = 0;
    }

    public int getNumJugadas() {
        return numJugadas;
    }

    public Jugada[] getJugadas() {

        Jugada[] jugadasRealizadas = new Jugada[numJugadas];
        for (int i = 0; i < numJugadas; i++) {
            jugadasRealizadas[i] = jugadas[i];
        }
        return jugadasRealizadas;

    }

    public Pistas[] getResultados() {

        Pistas[] resultadosRealizados = new Pistas[numJugadas];

        for (int i = 0; i < numJugadas; i++) {
            resultadosRealizados[i] = pistas[i];
        }

        return resultadosRealizados;

    }

    public void insertar(Jugada jugada, Pistas pistas) {
        if(numJugadas < MAX_JUGADAS){
            this.jugadas[numJugadas] = jugada;
            this.pistas[numJugadas] = pistas;
            numJugadas++;
        }
    }

    public boolean completo() {
        return numJugadas >= MAX_JUGADAS;
    }

    public void visualizar() {

        for (int i = 0; i < numJugadas; i++) {

            System.out.printf("Jugada %2d: ", i + 1);
            jugadas[i].visualizar();
            System.out.print(" ");
            pistas[i].visualizar();
            System.out.println();

        }

    }


}
