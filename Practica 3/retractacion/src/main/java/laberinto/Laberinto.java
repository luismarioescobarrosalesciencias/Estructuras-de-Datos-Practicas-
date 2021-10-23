/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */
package laberinto;

import java.util.LinkedList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * Laberinto generado con el algoritmo de retractación.  Se puede resolver
 * siguiendo un algoritmo de la misma familia.
 * @author blackzafiro
 */
public class Laberinto extends GridPane {

	/**
	 * Agente que puede moverse libremente en laberinto.
	 */
	public class Agente extends Region {
		private Circle avatar;

		private int iniRen;
		private int iniCol;

		private int ren;
		private int col;

		private Translate translate;
		private SequentialTransition secuencia;

		public Agente(int ren, int col) {
			this.iniRen = this.ren = ren;
			this.iniCol = this.col = col;

			avatar = new Circle(tamCelda/3, Color.ALICEBLUE);
			this.getChildren().add(avatar);

			translate = new Translate(x(), y());
			avatar.getTransforms().add(translate);

			secuencia = new SequentialTransition();
		}

		/**
		 * Coodenada x sobre el canvas del laberinto.
		 * @return posición x
		 */
		private double x() {
			return tamCelda * (0.5 + col);
		}

		/**
		 * Coodenada y sobre el canvas del laberinto.
		 * @return posición y
		 */
		private double y() {
			return tamCelda * (0.5 + ren);
		}

		/**
		 * Desplaza al agente en la dirección indicada si no está bloqueada.
		 * @param dirección
		 */
		public boolean avanza(Dirección dirección) {
			Celda c = celdas[ren][col];
			if(c.paredes[dirección.índice()]) return false;

			TranslateTransition tt = new TranslateTransition(Duration.millis(100), avatar);
			switch(dirección) {
				case NORTE:
					tt.setByY(-tamCelda);
					ren--;
					break;
				case SUR:
					tt.setByY(tamCelda);
					ren++;
					break;
				case ESTE:
					tt.setByX(tamCelda);
					col++;
					break;
				case OESTE:
					tt.setByX(-tamCelda);
					col--;
					break;
			}
			secuencia.getChildren().add(tt);
			return true;
		}

		/**
		 * Indica las direcciones en las que hay pasillos abiertos.
		 * @return lista de direcciones.
		 */
		public LinkedList<Dirección> direccionesPasillos() {
			LinkedList<Dirección> l = new LinkedList<>();
			Celda c = celdas[ren][col];
			for(Dirección d : Dirección.DIRECCIONES) {
				if (!c.paredes[d.índice()]) {
					l.add(d);
				}
			}
			return l;
		}

		/**
		 * Indica si el agente se encuentra parado en una celda meta.
		 */
		public boolean estáEnMeta() {
			return celdas[ren][col].esMeta();
		}

		/**
		 * Marca la celda donde está parado el agente como una celda visitada.
		 */
		public void marcaCelda() {
			celdas[ren][col].visitada(true);
		}

		/**
		 * Desmarca la celda donde está parado el agente como una celda visitada.
		 */
		public void desmarcaCelda() {
			celdas[ren][col].visitada(false);
		}

		/**
		 * Devuelve al agente a su posición inicial y desmarca todas las
		 * casillas.
		 */
		public void reinicia() {
			secuencia.getChildren().clear();
			ren = iniRen;
			col = iniCol;
			avatar.setTranslateX(0);
			avatar.setTranslateY(0);
			desmarcaLaberinto();
		}

		/**
		 * Mueve al avatar siguiendo las búsquedas.
		 */
		public void ejecutaAnimación() {
			secuencia.play();
		}

		/**
		 * Mira a la celda en la dirección indicada.
		 * Si hay un pasillo en esa dirección y la celda no ha sido visitada
		 * devuelve True.
		 * @param d Dirección en la cual mirar desde la celda donde está el
		 *          agente.
		 * @return Si hay un pasillo sin vistar en esa dirección.
		 */
		public boolean miraSiNoVisitada(Dirección d) {
			if (celdas[ren][col].paredes[d.índice()]) return false;
			Celda v = devuelveVecina(ren, col, d);
			if (v == null) return false;
			else return !v.visitada();
		}
	}

	private double ancho;
	private double alto;

	private double tamCelda;

	private int rens;
	private int cols;
	private Celda[][] celdas;

	private Agente agente;

	/**
	 * Construye un laberinto generándolo aleatoriamente y como nodo de JavaFX.
	 * @param padre <code>Pane</code> que contiene al laberinto.  Es necesario
	 *        para calcular las dimensiones.
	 * @param rens Número de renglones del laberinto.
	 * @param cols Número de columnas del laberinto.
	 */
	public Laberinto(Pane padre, int rens, int cols) {
		this.rens = rens;
		this.cols = cols;

		asignaTamaños(padre);
		llenaCeldas();
		genera();
	}

	/**
	 * Asigna los tamaños de renglones y columnas.
	 */
	private void asignaTamaños(Pane padre) {
		ancho = padre.getWidth();
		alto = padre.getHeight();

		double altoCelda = alto/rens;
		double anchoCelda = ancho/cols;
		tamCelda = (altoCelda <= anchoCelda) ? altoCelda : anchoCelda;
		// ¿Vaciar las restricciones si cambia el tamaño?
		RowConstraints rowCons = new RowConstraints(tamCelda);
		for(int ren = 0; ren < rens; ren++) {
			this.getRowConstraints().add(rowCons);
		}
		ColumnConstraints colCons = new ColumnConstraints(tamCelda);
		for(int col = 0; col < cols; col++) {
			this.getColumnConstraints().add(colCons);
		}
	}

	/**
	 * Coloca una celda en cada posición de la rejilla.
	 */
	private void llenaCeldas() {
		Sprites sprites = new Sprites(tamCelda);
		celdas = new Celda[rens][cols];
		Celda celda;
		for(int ren = 0; ren < rens; ren++) {
			for(int col = 0; col < cols; col++) {
				celda = new Celda(sprites);
				this.add(celda, col, ren);
				celdas[ren][col] = celda;
			}
		}
	}

	/**
	 * Devuelve la celda en la dirección indicada, a partir del renglón y
	 * columna de referencia.
	 * @param ren
	 * @param col
	 * @param d
	 * @return Celda vecina, <code>null</code> si no hay vecina en esa
	 *         dirección por estar cerca del borde.
	 */
	private Celda devuelveVecina(int ren, int col, Dirección d) {
		switch(d) {
			case NORTE:
				if (ren > 0) return celdas[ren-1][col];
				else return null;
			case SUR:
				if(ren < rens - 1) return celdas[ren+1][col];
				else return null;
			case ESTE:
				if(col < cols - 1) return celdas[ren][col+1];
				else return null;
			case OESTE:
				if(col > 0) return celdas[ren][col-1];
				else return null;
		}
		return null;
	}

	/**
	 * Calcula las direcciones con paredes que aún pueden ser derribadas.
	 * @param ren
	 * @param col
	 * @return
	 */
	private LinkedList<Dirección> calculaDireccionesDerribables(int ren, int col) {
		Celda c = celdas[ren][col];
		LinkedList<Dirección> rutas = new LinkedList<>();
		// Norte
		if (ren > 0
				&& c.paredes[0]
				&& !celdas[ren-1][col].visitada()) {
			rutas.add(Dirección.NORTE);
		}
		// Este
		if (col < cols - 1
				&& c.paredes[1]
				&& !celdas[ren][col+1].visitada()) {
			rutas.add(Dirección.ESTE);
		}
		// Sur
		if (ren < rens - 1
				&& c.paredes[2]
				&& !celdas[ren+1][col].visitada()) {
			rutas.add(Dirección.SUR);
		}
		// Oeste
		if (col > 0
				&& c.paredes[3]
				&& !celdas[ren][col-1].visitada()) {
			rutas.add(Dirección.OESTE);
		}
		return rutas;
	}

	/**
	 * Derriba paredes aleatoriamente siguiendo el algoritmo de retractación.
	 */
	private void genera() {
		// Selecciona aleatoriamente una posición inicial:
		int renAzar = ThreadLocalRandom.current().nextInt(0, rens);
		int colAzar = ThreadLocalRandom.current().nextInt(0, cols);
		derribaPared(renAzar, colAzar);
		desmarcaLaberinto();
		renAzar = ThreadLocalRandom.current().nextInt(0, rens);
		colAzar = ThreadLocalRandom.current().nextInt(0, cols);
		celdas[renAzar][colAzar].esMeta(true);

		do {
			renAzar = ThreadLocalRandom.current().nextInt(0, rens);
			colAzar = ThreadLocalRandom.current().nextInt(0, cols);
		} while(celdas[renAzar][colAzar].esMeta());

		agente = new Agente(renAzar, colAzar);
	}

	/**
	 * Selecciona una pared para derribar y pasa el trabajo al descendiente.
	 * @param ren    Renglón de la celda actual.
	 * @param col    Columna de la celda actual.
	 */
	private void derribaPared(int ren, int col) {
		celdas[ren][col].visitada(true);

		boolean tengoVecinos = true;
		LinkedList<Dirección> rutas;
		int tam;

		while(tengoVecinos) {
			rutas = calculaDireccionesDerribables(ren, col);
			tam = rutas.size();

			// Caso base
			if (tam == 0) { return; }
			Dirección d = rutas.get(ThreadLocalRandom.current().nextInt(0, tam));
			switch(d) {
				case NORTE:
					celdas[ren-1][col].derribaPared(Dirección.SUR);
					celdas[ren][col].derribaPared(Dirección.NORTE);
					derribaPared(ren-1, col);
					break;
				case SUR:
					celdas[ren+1][col].derribaPared(Dirección.NORTE);
					celdas[ren][col].derribaPared(Dirección.SUR);
					derribaPared(ren+1, col);
					break;
				case ESTE:
					celdas[ren][col+1].derribaPared(Dirección.OESTE);
					celdas[ren][col].derribaPared(Dirección.ESTE);
					derribaPared(ren, col+1);
					break;
				case OESTE:
					celdas[ren][col-1].derribaPared(Dirección.ESTE);
					celdas[ren][col].derribaPared(Dirección.OESTE);
					derribaPared(ren, col-1);
					break;
			}
		}
	}

	/**
	 * Quita las marcas de visitada de todas las celdas.
	 */
	public void desmarcaLaberinto() {
		for(Celda[] r : celdas) {
			for(Celda c : r) {
				c.visitada(false);
			}
		}
	}

	/**
	 * Devuelve al agente que navegará el laberinto.
	 * @return el agente.
	 */
	public Agente agente() {
		return agente;
	}
}
