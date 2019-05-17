package control;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.VentanaLaberinto;

public class ControladorPersonajes implements KeyListener, MouseListener {

	public static final int Quieto = -1;
	public static final int Derecha = 0;
	public static final int Abajo = 1;
	public static final int Izquierda = 2;
	public static final int Arriba = 3;
	
	public int haciaDonde = Quieto;
	
	public VentanaLaberinto v;
	public Graphics g;
	public int anchoCelda;
	
	public ControladorPersonajes(VentanaLaberinto v, int anchoCelda) {
		
		this.v = v;
		this.g = v.lienzo.getGraphics();
		this.anchoCelda = anchoCelda;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_LEFT) haciaDonde = Izquierda;
		if (e.getKeyCode()==KeyEvent.VK_UP) haciaDonde = Arriba;
		if (e.getKeyCode()==KeyEvent.VK_DOWN) haciaDonde = Abajo;
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) haciaDonde = Derecha;	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		System.out.println("Entro");
		this.v.requestFocusInWindow();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
