/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */
package laberinto;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Aplicación que genera laberintos aleatoriamente para que un agente
 * virtual los resuelva.
 * @author blackzafiro
 */
public class App extends Application {

	@Override
	public void start(Stage primaryStage)  throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/data/vista.fxml"));
		primaryStage.setTitle("Laberinto");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/data/icon.png")));
		primaryStage.setScene(new Scene(root));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	
}
