public class MasterMind {
    private Tablero tablero;
    private Jugada jugadaOculta;
    private int numFichas;

    public MasterMind(int numFichas) {
        this.tablero = new Tablero();
        this.jugadaOculta = new Jugada(numFichas);
        this.numFichas = numFichas;

    }

    public Tablero getTablero() {
        return tablero;
    }

    public MasterMind(String nombreArchivo) {
        // TODO
    }

    private void guardarPartida(String nombreArchivo) {
        // TODO
    }

    public void jugar() {
        // TODO
        while (tablero.getNumJugadas() < 10 ) {

            String mensaje = "Introduce jugada o G (guardar la partida).\n" +
                    "R (Rojo), V(Verde), A (Amarillo), P (púrpura): ";
            String turno = Teclado.leerJugadaGuardar(numFichas, mensaje);

            if (turno == "G") { // para guardar lo que pasa

            }

            else {
                Jugada jugadaActual = new Jugada(turno);
                Pistas pistas = jugadaActual.comprobar(jugadaOculta);

                tablero.insertar(jugadaActual, pistas);
                tablero.visualizar();

                if (pistas.getAciertos() == numFichas) {
                    System.out.println("¡ACERTASTE LA JUGADA!");
                    break;
                }

                if (tablero.completo()){
                    System.out.println("FIN DE LOS INTENTOS, NO CONSEGUISTE ACERTAR");
                    System.out.print("La jugada oculta era: ");
                    jugadaOculta.visualizar();
                    break;
                }
            }

        }


    }

    public static void main(String[] args) {
        MasterMind masterMind;
        if (Teclado.leerSiNo("¿Quieres recuperar una partida? (S/N): ") == 'S') {
            String nombreArchivo = Teclado.leerString("Nombre del archivo: ");
            masterMind = new MasterMind(nombreArchivo);
            masterMind.getTablero().visualizar();
        } else {
            int fichas = Teclado.leerEntero(4, 6, "Número de fichas de las jugadas (4 - 6): ");
            masterMind = new MasterMind(fichas);
        }
        masterMind.jugar();
    }
}
