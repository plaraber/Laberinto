package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.ControladorMenu;
import model.Celda;

public class VentanaLaberinto extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public int anchoCelda;
	public JLabel mazeV[][];
	public Canvas lienzo;
	public BufferedImage imgWall;
	
	public JMenuItem menuItemUnGato;
	public JMenuItem menuItemDosGatos;
	
	public ControladorMenu cm;
	
	public VentanaLaberinto (int anchoCelda) {
		
		this.anchoCelda = anchoCelda;
		lienzo = new Canvas();
		this.getContentPane().add(lienzo);
		this.setSize(anchoCelda*10+20,anchoCelda*10+70);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		crearMenu();
		this.setFocusable(true);
		this.setVisible(true);
		try {
			this.imgWall = ImageIO.read(new File(".\\imagenes\\wall.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void crearMenu () {
		
		JMenuBar menuBar;
		JMenu menu;

		// Crea la barra de menú
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Primer menú
		menu = new JMenu("Nueva Partida");
		menu.setMnemonic(KeyEvent.VK_N);

		// Submenú 1
		menuItemUnGato = new JMenuItem("Partida con un Gato");
		menuItemUnGato.getAccessibleContext().setAccessibleDescription("Leer lista de Personas");
		// Añadimos controlador al item de menú
		menu.add(menuItemUnGato);

		// Submenú 2
		menuItemDosGatos = new JMenuItem("Partida con dos Gato");
		menuItemDosGatos.getAccessibleContext().setAccessibleDescription("Leer lista de Personas");
		// Añadimos controlador al item de menú
		menu.add(menuItemDosGatos);
		
		menuBar.add(menu);
		menuBar.setVisible(true);
	}
	
	public void addControladorMenu (ControladorMenu cm) {
		
		this.cm = cm;
		menuItemUnGato.addActionListener(this.cm);
		menuItemDosGatos.addActionListener(this.cm);
	}

	public void dibujarLaberinto(Celda [][]mazeC) {
		
		int altoYancho = mazeC.length;
		for (int x = 0; x < altoYancho; x++) {
			for (int y = 0; y < altoYancho; y++) {

				if (mazeC[x][y].isPared()) {
					lienzo.getGraphics().setColor(Color.BLACK);
					lienzo.getGraphics().drawImage(this.imgWall, anchoCelda * x, anchoCelda * y, 
												   anchoCelda, anchoCelda, this.lienzo);
				}
				System.out.print("("+x+", "+y+")");
			}
			System.out.println();
		}
	}
}
