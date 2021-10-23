/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */
package svgrender;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Espacio para la función auxiliar que permite crear copias en memoria de
 * cada mosaico dentro de la hoja de sprites.
 * @author blackzafiro
 */
public class TileBufferedImage {
	
	/**
	 * Copia los datos en <code>original</code> de modo que la memoria ya
	 * no se comparte con la copia.
	 * 
	 * Funciona con subimágenes.
	 * @link https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
	 * 
	 * @param original Imágen o subimágen.
	 * @return Copia con memoria nueva para ella.
	 */
	public static BufferedImage deepCopy(BufferedImage original) {
		ColorModel cm = original.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = original.copyData(
				original.getRaster().createCompatibleWritableRaster());
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
}
