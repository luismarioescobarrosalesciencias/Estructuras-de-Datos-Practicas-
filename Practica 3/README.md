[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=5873020&assignment_repo_type=AssignmentRepo)
# Práctica 3 - Retractación
## Estructuras de Datos
### Semestre 2022-1

La entrega se realiza en este repositorio.
* Esta prohibido publicar las soluciones en reposiorios públicos.

## Instrucciones para compilar

Esta práctica tiene requerimientos adicionales.  Como utilizaremos una interfaz gráfica de usuario y se leerá un archivo svg, necesitaremos dos proyectos de Java adicionales: JavaFX y Batik.

Para hacer más sencillo el proceso de instalación, utilizaremos la herramienta _Maven_.

### Instalar Maven

En Ubuntu:
```
sudo apt install maven
```
En Fedora seguir las instrucciones de [How to Install Apache Maven on Fedora 34/33](https://tecadmin.net/install-apache-maven-on-fedora/).  Se puede hacer algo semejante si se quiere tener una versión más reciente en Ubuntu, pero no es necesaria para esta práctica.

### Compilar el proyecto

* Asegúrate de haber definido la variable de ambiente ```JAVA_HOME``` con la direcciónde tu instalación de Java en tu archivo ```.bashrc``` o ```.bash_profile```, según sea el caso.

* El archivo de configuración ```pom.xml```, que utiliza _Maven_, fue generado para _Java 11_. Te recomendamos usar esa versión de Java, pero si quieres usar otra necesitas realizar las siguientes modificaciones:

    Abre el archivo _pom.xml_ y cambia todos los tags que indiquen versión 11 por tu versión de Java, estos son:
    * ```<maven.compiler.source>11</maven.compiler.source>```
    * ```<maven.compiler.target>11</maven.compiler.target>```
    * ```<version>11</version>```
    * ```<release>11</release>```

* Entra a la carpeta ```retractacion``` y ejecuta el código que descargaste con:
    ```
    mvn clean javafx:run
    ```
    La primera vez que lo ejecutes notarás que se descargan un montón de archivos.  Concretamente se trata de JavaFX y Batik.  Esta es la magia de _Maven_, en el archivo _pom.xml_ se indican las dependencias y esta herramienta se encarga de instalarlas cuando es necesario.  No te preocupes, la instalación ocurre a nivel de tu cuenta de usuario.  Si creas otro proyecto con las mismas dependencias éstas no se volverán a descargar.

    Durante la instalación aparecerán varias advertencias, no hay nada que podamos hacer al respecto, Batik necesita arreglar algunas cosas, pero estas no nos afectan.

    Después de ello _Maven_ también mandará empacar y ejecutar el código de este proyecto.  Deberás poder ver la interfaz de usuario y generar algunos laberintos, pero si presionas el botón _resolver_ no pasará nada.  Eso es lo que te corresponde implementar.  Tu código va en la función ```public boolean resuelveLaberinto()``` en el archivo ```retractacion/src/main/java/laberinto/Control.java```.

* Si solamente deseas compilar tu código sin ejecutar la aplicación puedes utilizar:
```
mvn package
```


## Maven

Si deseas saber más sobre Maven, un buen lugar para comenzar es [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).

La estructura inicial para este proyecto se obtuvo con el siguiente comando:
```
mvn archetype:generate \
        -DarchetypeGroupId=org.openjfx \
        -DarchetypeArtifactId=javafx-archetype-simple \
        -DarchetypeVersion=0.0.3 \
        -DinteractiveMode=false \
        -DgroupId=laberinto \
        -DartifactId=retractacion \
        -Dversion=1.0.0 \
        -Djavafx-version=11
```
Posteriormente se agregaron las demás dependencias en el pom.xml y los archivos fuente.
