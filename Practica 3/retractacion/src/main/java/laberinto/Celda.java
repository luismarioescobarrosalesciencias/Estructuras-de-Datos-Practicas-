/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */
package laberinto;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;

import javafx.scene.shape.Line;

/**
 * Una celda del laberinto, puede tener una pared en cada dirección.
 * @author blackzafiro
 */
class Celda extends Region {
	
	private static final Color colorDeFondo = Color.BEIGE;
	private Rectangle fondo;
	
	private Sprites sprites;
	private ImageView sprite;
	
	/**
	 * Paredes en el orden:
	 * 0 - norte
	 * 1 - este
	 * 2 - sur
	 * 3 - oeste
	 * según el enum Dirección.
	 */
	boolean[] paredes = new boolean[4];
	private Line[] paredesL = new Line[4];
	private final boolean DIBUJA_PAREDES = false;
	
	private boolean visitada;
	private boolean esMeta;
	
	/**
	 * Construye una celda cuadrada con cuatro paredes alrededor.
	 * @param lado Longitud en pixeles del lado.
	 */
	Celda(Sprites sprites) {
		this.sprites = sprites;
		double lado = sprites.getTamCelda();
		fondo = new Rectangle(lado, lado);
		fondo.setFill(colorDeFondo);
		for(int i = 0; i < paredes.length; i++) paredes[i] = true;
		asignaSprite();
		this.getChildren().add(fondo);
		
		if (DIBUJA_PAREDES) {
			paredesL[Dirección.NORTE.índice()] = new Line(0, 0, lado, 0);
			paredesL[Dirección.ESTE.índice()] = new Line(lado, 0, lado, lado);
			paredesL[Dirección.SUR.índice()] = new Line(0, lado, lado, lado);
			paredesL[Dirección.OESTE.índice()] = new Line(0, 0, 0, lado);
		
			for(Line l : paredesL) {
				l.setStrokeWidth(lado/5);
			}
			this.getChildren().addAll(paredesL);
		}
	}
	
	private void asignaSprite() {
		if (sprite != null) this.getChildren().remove(sprite);
		sprite = sprites.getSprites(paredes);
		this.getChildren().add(sprite);
	}
	
	/**
	 * Marca si esta celda ya fue visitada por el algoritmo en ejecución.
	 * @param v El marcaje deseado.
	 */
	public void visitada(boolean v) {
		visitada = v;
		if(v) {
			if(esMeta) {
				fondo.setFill(Color.ALICEBLUE);
			} else {
				fondo.setFill(Color.AQUAMARINE);
			}
		} else {
			if (esMeta) {
				fondo.setFill(Color.CORAL);
			} else {
				fondo.setFill(colorDeFondo);
			}
		}
	}
	
	/**
	 * Marca si esta celda ya fue visitada por el algoritmo en ejecución.
	 * @return El estado actual de la celda.
	 */
	public boolean visitada() {
		return visitada;
	}
	
	/**
	 * Designa si esta celda es meta.
	 */
	public void esMeta(boolean es) {
		this.esMeta = es;
		if(es) fondo.setFill(Color.CORAL);
		else fondo.setFill(colorDeFondo);
	}
	
	/**
	 * Indica si esta celda fue designada como meta.
	 */
	public boolean esMeta() {
		return esMeta;
	}
	
	/**
	 * Borra la pared en la Dirección indicada.
	 * @param d 
	 */
	public void derribaPared(Dirección d) {
		paredes[d.índice()] = false;
		asignaSprite();
		
		if (DIBUJA_PAREDES) {
			this.getChildren().remove(paredesL[d.índice()]);
			paredesL[d.índice()] = null;
		}
	}
}
