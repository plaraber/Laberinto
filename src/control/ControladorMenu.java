package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VentanaLaberinto;

public class ControladorMenu implements ActionListener {

	public VentanaLaberinto ventana;

	public ControladorMenu (VentanaLaberinto v) {
		this.ventana = v;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(ventana.menuItemUnGato)) {
			System.out.println("Partida con 1 gato");
			Sistema.numeroGatos=1;
		} else {
			System.out.println("Partida con 2 gatos");			
			Sistema.numeroGatos=2;
		}
	}
}
