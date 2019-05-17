package control;

import javax.swing.JOptionPane;

import model.Laberinto;
import model.Raton;
import model.Gato;
import view.VentanaLaberinto;

public class Sistema {

	public final static int ANCHO_CELDA = 40;
	public final static int ANCHO_LABERINTO = 10;
	public static Laberinto l = new Laberinto();
	public static VentanaLaberinto v;
	public static ControladorAudio ca;
	public static ControladorMenu cMenu;
	public static ControladorPersonajes controlador;
	public static int numeroGatos=0;

	public static void jugarPartida() {

		if (Sistema.numeroGatos != 0) {
			// Leo y muestro el laberinto por consola
			Sistema.l = new Laberinto();
			Sistema.l.leerLaberinto(Sistema.ANCHO_LABERINTO);
			Sistema.l.mostrarLaberinto();

			// Creo y asocio un controlador de teclado al laberinto
			Sistema.controlador = new ControladorPersonajes(Sistema.v, Sistema.ANCHO_CELDA);
			Sistema.v.addKeyListener(Sistema.controlador);
			Sistema.v.addMouseListener(Sistema.controlador);
			Sistema.l.cp = Sistema.controlador;

			// Creo el Controlador de Audio
			Sistema.ca = new ControladorAudio();

			Sistema.v.dibujarLaberinto(l.maze);

			ca.reproducirAudio(ControladorAudio.START);
			JOptionPane.showMessageDialog(v, "Que comience la batalla");
			ca.pararAudio();

			// Creo el gato y el ratón y los añado al laberinto
			Raton r = new Raton(0, 0, ".\\imagenes\\raton.jpg");
			Gato g = new Gato(Sistema.ANCHO_LABERINTO - 1, Sistema.ANCHO_LABERINTO - 1, ".\\imagenes\\gato.jpg");
			Sistema.l.addGato(g);
			if (Sistema.numeroGatos == 2) {
				Gato g2 = new Gato(0, 7, ".\\imagenes\\gato.jpg");
				Sistema.l.addGato(g2);
			}
			Sistema.l.addRaton(r);

			// Dibujo el gato y el raton en la posición inicial
			Sistema.l.dibujarPersonajes();

			// Inicio el bucle del juego
			ca.reproducirLoopAudio(ControladorAudio.CHOMP);
			while (!Sistema.l.encontradaSalida() && !Sistema.l.ratonComido()) {
				Sistema.l.moverPersonajes();
				try {
					Thread.sleep(250);
				} catch (Exception e) {
					System.err.println("Falló el bucle del juego");
				}
			}
			ca.pararAudio();
			if (!Sistema.l.ratonComido()) {
				ca.reproducirAudio(ControladorAudio.GANO_RATON);
				JOptionPane.showMessageDialog(v, "El ratón salió");
			} else {
				g.dibujar();
				ca.reproducirAudio(ControladorAudio.COMIDO);
				JOptionPane.showMessageDialog(v, "El Gato se comio al ratón");
			}
		}
	}

	public static void main(String[] args) {

		// Creo la ventana del laberinto
		Sistema.v = new VentanaLaberinto(Sistema.ANCHO_CELDA);
		// Creo y asocio un controlador de menu a la ventana
		Sistema.cMenu = new ControladorMenu(v);
		Sistema.v.addControladorMenu(cMenu);

		while (true) {
			int otraPartida = JOptionPane.YES_OPTION;
			if (Sistema.numeroGatos != 0) {
				do {
					Sistema.jugarPartida();
					otraPartida = JOptionPane.showConfirmDialog(v,
							"¿Otra partida con " + Sistema.numeroGatos + " Gatos?", "Fin de la partida",
							JOptionPane.YES_NO_OPTION);
					Sistema.l.inicializarLaberinto();
				} while (otraPartida == JOptionPane.YES_OPTION);
				Sistema.numeroGatos = 0;
			}
			System.out.print("."); // Necesario para que funcione
		}
	}

}
