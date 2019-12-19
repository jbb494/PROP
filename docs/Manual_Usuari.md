<p align="center">

# Manual de Usuario

Cuando abramos el ejecutable veremos la siguiente interfície:

![image-20191219165041009](/home/miguel/.config/Typora/typora-user-images/image-20191219165041009.png)



## Secciones de la interficíe

### 1.  Opción de previsualización

Si tenemos la casilla marcada observaremos que aparece en la pantalla la zona para la previsualización.

### 2. Zona de previsualización

En el caso de que tengamos la casilla de previsualización marcada, veremos la previsualización (descompresión) de un archivo.

### 3.  Seleccionar fichero/directorio

Al pinchar sobre el icono de la carpeta se abrirá un panel donde podremos escoger cualquier archivo visible de nuestro directorio, cuya dirección será mostrada en la barra de "path".

![image-20191219170131047](/home/miguel/.config/Typora/typora-user-images/image-20191219170131047.png)

### 4. Barra de path

Una vez hayamos seleccionado el archivo con el que queremos trabajar, su ruta será mostrada aquí.

### 5. Compresión Automática

Una vez tengamos un fichero seleccionado, si no se trata de un archivo ya comprimido, podremos comprimirlo de manera automática al hacer click. Será el propio programa quien se encargue de tomar todas las decisiones.

### 6. Compresión Manual

De la misma manera que en la **compresión Automática**, una vez tengamos seleccionado un fichero (a excepción de los ya comprimidos) podremos hacer click en esta opción. Una vez hecho se nos abrirá una ventana donde podremos escoger como queremos que sea tratado nuestro archivo durante el proceso.

![image-20191219170647072](/home/miguel/.config/Typora/typora-user-images/image-20191219170647072.png)

Por defecto viene seleccionado un *grado de compresión bajo* para los archivos ***.ppm*** (los cuales serán procesados con *JPEG*) y el algoritmo *LZW* para el resto de archivos. En caso de que queramos modificar la decisión simplemente tendremos que escoger lo que deseemos y darle a **Aceptar**. 

### 7. Descompresión

De la misma manera que en la compresión, una vez tengamos seleccionado un archivo (en este caso únicamente podrá ser ***.ppm***) se nos habilitará la opción de descomprimirlo.

A diferencia de la compresión, únicamente se nos proporcionará una opción automática, dado que será el propio programa quien se encargue de decidir el algoritmo para la descompresión según el que hayamos utilitzado para la compresión.

### 8. Salir

Una vez hayamos acabado de realizar todo lo que necesitásemos, no tenemos más que hacer click sobre este botón para que el programa finalice y se cierre.



## Funcionamiento

Para empezar a usar el programa basta con seguir los pasos siguientes.

Lo primero que tendremos que hacer siempre será seleccionar el archivo con el que queramos trabajar (directorios incluídos). Una vez seleccionado el fichero podremos observar en nuestra **barra de path** su ruta. 

*Si por algún error no hemos seleccionado el fichero deseado simplemente tenemos que empezar este paso desde el inicio.*

Una vez tengamos nuestro archivo seleccionado, tendremos varias posibilidades según el tipo de fichero:

1. ***Fichero comprimido***: En este caso se nos habilitará la opción de descomprimirlo. En el supuesto caso de que no estemos seguros de que sea el archivo que queremos descomprimir o por cualquier otra razón, tendremos también la opción de habilitar la casilla **Visualizar** y se nos mostrará el fichero sin dejarlo guardado en el correspondiente directorio.
2. ***Fichero descomprimido***: En este caso podremos escoger entre una compresión manual o automática. Si escogemos la opción manual simplemente tenemos que seguir los pasos detallados en su correspondiente apartado (explicado en la sección anterior).

Una vez seleccionada nuestra opción, en el caso de que haya sido compresión/descompresión una vez haya acabado el proceso se nos mostrarán las estadistícas, como bien pueden ser el grado de compresión/descompresión o el tiempo que haya tardado.

Una vez acabado el proceso podemos volver a empezar de 0 seleccionando un nuevo archivo o salir del programa.



## Aclaraciones

* Una vez tengamos seleccionado un archivo y lo comprimamos o descomprimamos, el resultado generado será guardado en el mismo directorio donde se encuentre el original.
* Si nos disponemos a descomprimir un fichero con su original (o uno con el mismo nombre) en ese mismo directorio, es importante tener en cuenta que el nuevo fichero **sobreescribirá** al anterior.







