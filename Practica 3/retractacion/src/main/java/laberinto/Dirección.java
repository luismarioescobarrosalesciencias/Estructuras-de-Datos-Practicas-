/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */
package laberinto;

/**
 * Direcciones alrededor de cada celda.
 * @author blackzafiro
 */
public enum Dirección {
	NORTE(0),
	ESTE(1),
	SUR(2),
	OESTE(3);

	/**
	 * Índice asociado con la dirección.
	 */
	private int ind;
	
	/**
	 * Caché de las direcciones en un arreglo con sus índices.
	 */
	public static final Dirección[] DIRECCIONES = Dirección.values();

	/**
	 * Constructor para los elementos de la enumeración.
	 * @param ind 
	 */
	private Dirección(int ind) {
		this.ind = ind;
	}

	/**
	 * Devuelve el índice numérico asociado con la dirección.
	 * @return 
	 */
	public int índice() {
		return ind;
	}

	/**
	 * Devuelve el objeto enum de la dirección opuesta.
	 * @return 
	 */
	public Dirección opuesta() {
		return DIRECCIONES[(this.índice() + 2) % 4];
	}
}
