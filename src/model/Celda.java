package model;

public class Celda {
	
	private boolean pared = false;
	private int numeroVisitas = 0;
	
	public boolean isPared() {
		return pared;
	}
	public void setPared(boolean pared) {
		this.pared = pared;
	}
	public boolean haSidoExplorada() {
		return numeroVisitas>=3;
	}
	public void visitada() {
		this.numeroVisitas++;
	}

	public String toString() {
		
		if (isPared()) {
			return "#";
		} else {
			return " ";
		}
	}

}
