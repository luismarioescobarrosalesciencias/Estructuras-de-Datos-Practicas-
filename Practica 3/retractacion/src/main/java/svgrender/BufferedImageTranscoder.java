/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */

package svgrender;

import java.awt.image.BufferedImage;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

/**
 * Permite utilizar Batik para traducir la imagen svg a una representación
 * en memoria basada en pixeles.
 * 
 * Many thanks to bb-generation for sharing this code!
 * @author bb-generation
 * @link http://bbgen.net/blog/2011/06/java-svg-to-bufferedimage/
 * @link In case the link above is still down: https://web.archive.org/web/20131215231214/http://bbgen.net/blog/2011/06/java-svg-to-bufferedimage/
 * @link https://gist.github.com/ComFreek/b0684ac324c815232556
 */
public class BufferedImageTranscoder extends ImageTranscoder {
	private BufferedImage img = null;
	private int rens, cols;
	
	public BufferedImageTranscoder(int cols, int rens) {
		super();
		this.cols = cols;
		this.rens = rens;
	}

    @Override
    public BufferedImage createImage(int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return bi;
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput to) throws TranscoderException {
        this.img = (BufferedImage)img;
    }

    public BufferedImage getBufferedImage() {
        return img;
    }
}
