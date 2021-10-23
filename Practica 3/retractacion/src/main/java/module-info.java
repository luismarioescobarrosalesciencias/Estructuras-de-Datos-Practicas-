/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o
 * potenciales pues se afectará su realización de los ejercicios.
 */

module laberinto {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.swing;
	requires jdk.xml.dom;
	requires batik.all;
	
	requires java.logging;
	
	exports laberinto to javafx.graphics;
	
	opens laberinto to javafx.fxml;
}

