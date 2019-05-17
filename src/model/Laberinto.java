package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import control.ControladorPersonajes;
import control.Sistema;

public class Laberinto {
	
	public static PrintWriter stdOut = new PrintWriter(System.out, true);
	public static PrintWriter stdErr = new PrintWriter(System.err, true);

	public int anchoLaberinto;
	public Celda maze[][];
	
	public Vector<Personaje> listaGatos = new Vector <Personaje> ();
	public Personaje raton;
	public ControladorPersonajes cp;
	
	public void addGato (Personaje p) {
		
		listaGatos.add(p);
		p.laberinto=this;
	}
	public void addRaton(Raton r) {

		this.raton=r;
		r.laberinto=this;
	}	
	public void leerLaberinto(int anchoLaberinto){
		
		this.anchoLaberinto = anchoLaberinto;
		maze = new Celda [anchoLaberinto][anchoLaberinto];
		File fichero = new File("Laberinto.txt");
		Scanner sc;
		
		try {
			sc = new Scanner (new FileReader(fichero));
			for (int f=0; f<anchoLaberinto; f++) {
				String linea = sc.nextLine();
				for (int c=0; c<anchoLaberinto; c++) {
					Celda nuevaCelda = new Celda();
					if (linea.charAt(c)=='#') {
						nuevaCelda.setPared(true);
					} else {
						nuevaCelda.setPared(false);					
					}
					this.maze[f][c]=nuevaCelda;
				}
			} 
		} catch (FileNotFoundException e1) {
			stdErr.println("El archivo no se encuentra en "+System.getProperty("user.dir"));
		}
	}

	public void mostrarLaberinto(){
		
		for (int f=0; f<anchoLaberinto; f++) {
			for (int c=0; c<anchoLaberinto; c++) {
				System.out.print(maze [f][c]);
			}
			System.out.println();
		}
	}
	
	public void moverPersonajes () {
		
		raton.mover();
		for (Personaje p: this.listaGatos) {	
			p.mover();
		}
	}
	
	public void dibujarPersonajes () {
		
		raton.dibujar();
		for (Personaje p: this.listaGatos) {	
			p.dibujar();
		}
	}
	
	public void inicializarLaberinto () {
		
		raton.borrar();
		for (Personaje p: this.listaGatos) {	
			p.borrar();
		}
		listaGatos = new Vector <Personaje>();
	}

	public boolean encontradaSalida () {
		
		return (raton.x==(Sistema.ANCHO_LABERINTO-1) && raton.y==(Sistema.ANCHO_LABERINTO-1));
		
	}
	
	public boolean ratonComido () {
		
		boolean comido=false;
		for (Personaje g: this.listaGatos) {	
			comido = (comido || (raton.x==g.x && raton.y==g.y));
		}
		return comido; 
	}


}
