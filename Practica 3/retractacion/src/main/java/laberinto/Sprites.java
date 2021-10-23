/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */
package laberinto;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import svgrender.BufferedImageTranscoder;
import svgrender.TileBufferedImage;

/**
 * Clase para cargar los mosaicos desde el archivo de imágen svg.
 * @author blackzafiro
 */
public class Sprites {
	
	private enum SpriteCode {
		C,                     // Sin paredes
		N, S, E, O,            // Una pared
		NE, ES, SO, ON,        // Dos paredes esquina
		NS, EO,                // Dos paredes paralelas
		NES, ESO, SON, ONE,    // Tres paredes
		NESO;                  // Cuatro paredes
	}
	
	private static final int X_TILES = 3, Y_TILES = 3;
	private static final String TILES_FILE = "/data/sprites_laberinto.svg";
	private Image[] sprites = new Image[9];
	private double tamCelda;
	
	public Sprites(double tamCelda) {
		this.tamCelda = tamCelda;
		BufferedImageTranscoder trans = new BufferedImageTranscoder(X_TILES, Y_TILES);
		try (InputStream file = getClass().getResourceAsStream(TILES_FILE)) {
			TranscoderInput transIn = new TranscoderInput(file);
            try {
                trans.transcode(transIn, null);
                // Use WritableImage if you want to further modify the image (by using a PixelWriter)
				BufferedImage img = trans.getBufferedImage();
				int spriteWidth = img.getWidth() / 3;
				int spriteHeight = img.getHeight() / 3;
				BufferedImage sub;
				for(int i = 0, ren = 0, col = 0; i < 9; i++, col = i % 3, ren = i / 3) {
					sub = img.getSubimage(spriteWidth * col, spriteHeight * ren, spriteWidth, spriteHeight);
					BufferedImage tile = TileBufferedImage.deepCopy(sub);
					/*
					System.out.format("[%d, %d] - [%d, %d], Inicia (%d, %d) en (%d, %d) de (%d, %d) tiles tamaño (%d, %d).%n",
						  ren, col, spriteHeight * ren, spriteWidth * col,
				          sub.getMinTileX(), sub.getMinTileY(),
						  sub.getTileGridXOffset(), sub.getTileGridYOffset(), sub.getNumXTiles(), sub.getNumYTiles(),
						  sub.getTileWidth(), sub.getTileHeight());
					*/
					sprites[i] = SwingFXUtils.toFXImage(tile, null);
				}
                /*
				System.out.format("Dims de sprites (%d, %d) con (%d, %d) tiles.%n",
						img.getWidth(), img.getHeight(), img.getNumXTiles(), img.getNumYTiles());
				for(int i = 0; i < 9; i++){
					System.out.format("Dims del sprite[%d] (%s, %s).%n", i, sprites[0].getWidth(), sprites[0].getHeight());
				}
				*/
            } catch (TranscoderException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
		}
		catch (IOException io) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, io);
        }
	}
	
	/**
	 *  Utiliza un árbol de decisión para saber qué sprite/view corresponde.
	 */
	private SpriteCode fromParedesToCode(boolean[] paredes) {
		if (paredes[Dirección.NORTE.índice()]) {
			if (paredes[Dirección.ESTE.índice()]) {
				if (paredes[Dirección.SUR.índice()]) {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.NESO;
					} else {
						return SpriteCode.NES;
					}
				} else {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.ONE;
					} else {
						return SpriteCode.NE;
					}
				}
			} else {
				if (paredes[Dirección.SUR.índice()]) {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.SON;
					} else {
						return SpriteCode.NS;
					}
				} else {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.ON;
					} else {
						return SpriteCode.N;
					}
				}
			}
		} else {
			if (paredes[Dirección.ESTE.índice()]) {
				if (paredes[Dirección.SUR.índice()]) {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.ESO;
					} else {
						return SpriteCode.ES;
					}
				} else {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.EO;
					} else {
						return SpriteCode.E;
					}
				}
			} else {
				if (paredes[Dirección.SUR.índice()]) {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.SO;
					} else {
						return SpriteCode.S;
					}
				} else {
					if (paredes[Dirección.OESTE.índice()]) {
						return SpriteCode.O;
					} else {
						return SpriteCode.C;
					}
				}
			}
		}
	}
	
	private ImageView createImageView(int índiceDelSprite,
			                          int rotación) {
		ImageView temp = new ImageView(this.sprites[índiceDelSprite]);
		if (rotación != 0) temp.setRotate(rotación);
		temp.setFitWidth(tamCelda);
		temp.setFitHeight(tamCelda);
		return temp;
	}
	
	public ImageView getSprites(boolean[] paredes) {
		SpriteCode code = fromParedesToCode(paredes);
		switch(code) {
			// Sin paredes
			case C:
				return createImageView(4, 0);
				
			// Una pared
			case N:
				return createImageView(7, 180);
			case E:
				return createImageView(7, 270);
			case S:
				return createImageView(7, 0);
			case O:
				return createImageView(7, 90);
				
			// Dos paredes esquina
			case NE:
				return createImageView(6, 180);
			case ON:
				return createImageView(6, 90);
			case ES:
				return createImageView(6, 270);
			case SO:
				return createImageView(6, 0);
			// Dos paredes paralelas
			case EO:
				return createImageView(3, 0);
			case NS:
				return  createImageView(3, 90);
				
			// Tres paredes
			case SON:
				return createImageView(0, 0);
			case ONE:
				return createImageView(0, 90);
			case NES:
				return createImageView(0, 180);
			case ESO:
				return createImageView(0, 270);
				
			// Cuatro paredes
			case NESO:
			default:
				return createImageView(8, 0);
		}
	}
	
	public double getTamCelda() {
		return tamCelda;
	}
}
