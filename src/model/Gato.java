package model;

import control.ControladorPersonajes;

public class Gato extends Personaje {

	public Gato(int x, int y, String rutaImagen) {
		super(x, y, rutaImagen);
	}
	
	public String toString () {
		
		return "Gato: "+super.toString();
	}

	public void dibujar() {

		laberinto.cp.g.drawImage(this.img, (x * laberinto.cp.anchoCelda)+5, (y * laberinto.cp.anchoCelda)+5, 
	  			laberinto.cp.anchoCelda-5, laberinto.cp.anchoCelda-5, this.laberinto.cp.v.lienzo);
	}
	
	public void mover() {

		this.borrar();
		this.moverPersiguiendo();
		this.dibujar();
	}

	public void moverAleatorio() {

		int movimientoAleatorio = (int) (Math.random() * 10 % 4);
		while (!this.direccionPosible(movimientoAleatorio)) {
			movimientoAleatorio = (movimientoAleatorio + 1) % 4;
		} 
		this.moverEnUnaDireccion(movimientoAleatorio);
	}

	public void moverPersiguiendo() {

		double distancia = laberinto.anchoLaberinto*2;
		int haciaDonde = ControladorPersonajes.Quieto;
		
		// Comprueba en todas las direcciones si el movimiento es posible. 
		// Si lo es, calculo la distancia al ratón del posible movimiento.
		// La distancia la calculo restando las X's y las Y's
		
		if (this.direccionPosible(ControladorPersonajes.Derecha)){
			double nuevaDistancia = Math.sqrt(Math.pow(this.laberinto.raton.x-(x+1),2)+Math.pow(this.laberinto.raton.y-y,2));
			haciaDonde = ControladorPersonajes.Derecha;
			distancia = nuevaDistancia;
			System.out.print("Posible Derecha ("+nuevaDistancia+"), ");
		}
		if (this.direccionPosible(ControladorPersonajes.Arriba)){
			double nuevaDistancia = Math.sqrt(Math.pow(this.laberinto.raton.x-(x),2)+Math.pow(this.laberinto.raton.y-(y-1),2));
			if (nuevaDistancia < distancia) {
				haciaDonde = ControladorPersonajes.Arriba;
				distancia = nuevaDistancia;
			}
			System.out.print("Posible Arriba ("+nuevaDistancia+"), ");
		}
		if (this.direccionPosible(ControladorPersonajes.Izquierda)){
			double nuevaDistancia = Math.sqrt(Math.pow(this.laberinto.raton.x-(x-1),2)+Math.pow(this.laberinto.raton.y-y,2));
			if (nuevaDistancia < distancia) {
				haciaDonde = ControladorPersonajes.Izquierda;
				distancia = nuevaDistancia;
			}
			System.out.print("Posible Izquierda ("+nuevaDistancia+"), ");
		}
		if (this.direccionPosible(ControladorPersonajes.Abajo)){
			double nuevaDistancia = Math.sqrt(Math.pow(this.laberinto.raton.x-(x),2)+Math.pow(this.laberinto.raton.y-(y+1),2));
			if (nuevaDistancia < distancia) {
				haciaDonde = ControladorPersonajes.Abajo;
				distancia = nuevaDistancia;
			}
			System.out.print("Posible Abajo ("+nuevaDistancia+"), ");
		}
		
		System.out.print("\nMoviendo Gato: ");
		this.moverEnUnaDireccion(haciaDonde);
	}

}
