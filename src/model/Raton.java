package model;

import java.awt.Color;

public class Raton extends Personaje {
	
	int xAnterior=0, yAnterior=0;

	public Raton(int x, int y, String rutaImagen) {
		super(x, y, rutaImagen);
	}

	public String toString () {		
		return "Raton: "+super.toString();
	}

	public void dibujar () {
		
		laberinto.cp.g.drawImage(this.img, (x * laberinto.cp.anchoCelda+5), 
	  			(y * laberinto.cp.anchoCelda+5), 
	  			laberinto.cp.anchoCelda-5, laberinto.cp.anchoCelda-5, this.laberinto.cp.v.lienzo);
	}
	
	public void mover() {
		// TODO Auto-generated method stub
		this.borrar();
		this.moverConTeclado();
		this.dibujar();

	}

	private void moverConTeclado() {
		if (this.direccionPosible(laberinto.cp.haciaDonde)) {
			this.moverEnUnaDireccion(laberinto.cp.haciaDonde);
			System.out.println("RATON: "+this);
		}
	}
	
	private void buscarSalida() {
		
		this.borrar();
		laberinto.cp.g.setColor(Color.RED);
		Celda celdaDerecha = null;
		Celda celdaAbajo = null;
		Celda celdaIzquierda = null;
		Celda celdaArriba = null;
		Celda celdaActual = laberinto.maze[x][y];
		if (x>0) celdaIzquierda = laberinto.maze[x-1][y];
		if (x<laberinto.anchoLaberinto-1) celdaDerecha = laberinto.maze[x+1][y];
		if (y>0) celdaArriba = laberinto.maze[x][y-1];
		if (y<laberinto.anchoLaberinto-1) celdaAbajo = laberinto.maze[x][y+1];
		
		if (celdaDerecha != null && !celdaDerecha.isPared() && !celdaDerecha.haSidoExplorada()) {
			xAnterior=x;
			yAnterior=y;
			x=x+1;
			System.out.println("Ratón a la derecha");
		} else if (celdaAbajo != null && !celdaAbajo.isPared() && !celdaAbajo.haSidoExplorada()) {
			xAnterior=x;
			yAnterior=y;
			y=y+1;
			System.out.println("Ratón abajo");
		} else if (celdaIzquierda != null && !celdaIzquierda.isPared() && !celdaIzquierda.haSidoExplorada()) {
			xAnterior=x;
			yAnterior=y;
			x=x-1;
			System.out.println("Ratón a la izquierda");
		} else if (celdaArriba != null && !celdaArriba.isPared() && !celdaArriba.haSidoExplorada()) {
			xAnterior=x;
			yAnterior=y;
			y=y-1;
			System.out.println("Ratón arriba");
		} else {
			x=xAnterior;
			y=yAnterior;
			System.err.println("Retrocedemos");			
		}
		celdaActual.visitada();
	}
}
