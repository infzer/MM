import java.io.*;
import java.util.Scanner;

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

    public MasterMind(String nombreArchivo) { // leer
        // TODO
        try {
            Scanner leer = new Scanner(new FileReader(nombreArchivo));
            String jugadaoculta = leer.nextLine(); // saco jugada oculta y num fichas
            numFichas = jugadaoculta.length();

            jugadaOculta = new Jugada(jugadaoculta);
            tablero = new Tablero();

            while (leer.hasNextLine()) {
                String cadena = leer.nextLine();
                String[] partes = cadena.split(" ");
                if (partes.length == 3) {
                    Jugada jugadaActual = new Jugada(partes[0]);
                    int aciertos = Integer.parseInt(partes[1]);
                    int descolocados = Integer.parseInt(partes[2]);
                    Pistas pista = new Pistas(aciertos, descolocados);
                    tablero.insertar(jugadaActual, pista);
                }
            }
            leer.close();

        } catch(IOException ex) {
            System.out.println("ERROR AL RECUPERAR LA PARTIDA");
        }

    }

    private void guardarPartida(String nombreArchivo) { // escribir
        // TODO
        try {
            BufferedWriter escribir = new BufferedWriter(new FileWriter(nombreArchivo));
            escribir.write(jugadaOculta.toString());
            escribir.newLine();

            Jugada[] jugadas = tablero.getJugadas();
            Pistas[] pistas = tablero.getResultados();

            for (int i = 0; i < tablero.getNumJugadas(); i++) {
                escribir.write(jugadas[i].toString() + " " + pistas[i].toString());
                escribir.newLine();
            }

            escribir.close();

        } catch(IOException ex) {
            System.out.println("ERROR AL GUARDAR LA PARTIDA");
        }

    }

    public void jugar() {
        // TODO
        while (tablero.getNumJugadas() < 10 ) {

            String mensaje = "Introduce jugada o G (guardar la partida).\n" +
                    "R (Rojo), V(Verde), A (Amarillo), P (púrpura): ";
            String turno = Teclado.leerJugadaGuardar(numFichas, mensaje);
            if (turno.equals("G")) { // para guardar lo que pasa
                String nombreArchivo = Teclado.leerString("Nombre del archivo: ");
                guardarPartida(nombreArchivo);
                break;
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
