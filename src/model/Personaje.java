package model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.ControladorPersonajes;

public abstract class Personaje {

	public int x, y;
	public BufferedImage img;
	
	public Laberinto laberinto;
	public int ultimoMovimiento = ControladorPersonajes.Quieto;


	// Constructor
	public Personaje(int x, int y, String rutaImagen) {
		super();
		this.x = x;
		this.y = y;
		
		try {
			this.img = ImageIO.read(new File(rutaImagen));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Métodos abstractos
	public abstract void dibujar ();
	public abstract void mover ();
	
	// Métodos implementados 
	
	public String toString () {
		
		return "("+x+","+y+")";
	}
	
	public void borrar () {		
		laberinto.cp.g.clearRect(x*laberinto.cp.anchoCelda, y*laberinto.cp.anchoCelda, 
				laberinto.cp.anchoCelda, laberinto.cp.anchoCelda);
	}
	public boolean direccionPosible (int direccion) {
		
		if (direccion == ControladorPersonajes.Derecha){
			return (x < laberinto.anchoLaberinto - 1 && !laberinto.maze[x + 1][y].isPared());
		} else if (direccion == ControladorPersonajes.Abajo) {
			return (y < laberinto.anchoLaberinto - 1 && !laberinto.maze[x][y + 1].isPared());
		} else if (direccion == ControladorPersonajes.Izquierda) {
			return (x > 0 && !laberinto.maze[x - 1][y].isPared());
		} else if (direccion == ControladorPersonajes.Arriba) {
			return (y > 0 && !laberinto.maze[x][y - 1].isPared());
		} else {
			return false;
		}
	}

	public void moverEnUnaDireccion (int direccion) {
		
		if (direccion == ControladorPersonajes.Derecha){
				x = x + 1;
				System.out.println(this+" - Movido derecha");
		} else if (direccion == ControladorPersonajes.Abajo) {
				y = y + 1;
				System.out.println(this+" - Movido abajo");
		} else if (direccion == ControladorPersonajes.Izquierda) {
				x = x - 1;
				System.out.println(this+" - Movido izquierda");
		} else if (direccion == ControladorPersonajes.Arriba) {
				y = y - 1;
				System.out.println(this+" - Movido arriba");
		} 
		ultimoMovimiento = direccion;
	}
}
