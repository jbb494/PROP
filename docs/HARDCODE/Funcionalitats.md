# Funcionalitats

## Comprimir i descomprimir fitxers txt

Ho fa mitjançant algoritmes LZ. 

Si l'usuari escull el mode manual pot triar quin algorisme emprar entre els LZ78, LZSS i LZW.

Si escull el mode automàtic el nostre programa tria l'algorisme en funció de la mida del fitxer: LZSS per a fitxers de menys de 1MB i LZW per a fitxers més grans. Aquesta heurística és nova d'aquesta entrega ja que a la primera entrega el mode automàtic sempre comprimia amb LZW.

## Comprimir i descomprimir imatges ppm

Ho fa mitjançant l'algoritme JPEG.

A més, si l'usuari escull mode manual, pot triar la qualitat amb què es comprimeix. Com més qualitat menys grau de compressió i com menys qualitat més grau de compressió.

Si l'usuari escull mode automàtic la qualitat serà del 50%.

La funcionalitat de triar la qualitat de compressió és nova respecte la primera entrega

## Comrpimir i descomprimir qualsevol fitxer (funcionalitat nova)

Si el fitxer que l'usuari vol comprimir no és ni ppm ni txt, es comprimirà amb un algoritme LZ, de la mateixa manera que si fos txt.

En descomprimir-se, recuperarà l'extensió original gràcies a la metadata del comprimit.

## Comprimir i descomprimir carpetes (funcionalitat nova)

També es poden comprimir i descomprimir carpetes. Per cada fitxer que contingui:
- Si és ppm serà comrpimit amb l'algoritme JPEG amb la qualitat que demani l'usuari (en cas de compressió manual) o amb una qualitat del 50% (en cas de compressió automàtica)
- Altrament, serà comrpimit amb un algoritme LZ, el que especifiqui l'usuari (en cas de compressió manual) o el que decideixi el programa mitjançant l'heurística explicada més amunt (en cas de compressió automàtica).

## Visualitzar fitxers de text (funcionalitat nova)

El nostre programa permet visualitxar fitxer de text. Si el fitxer de text que es volvisualitzar està comprimit, es descomprimeix automàticament (en un fitxer temporal) per poder ser mostrat a la interfície gràfica d'usuari.


## Extensió única (funcionalitat nova)

Tots els comprimits generats pel nostre programa tenen la mateixa extensió: .jm, a diferència de la primera entrega, en què l'extensió representava l'algorisme amb què s'havia comprimit. Ara el programa sap amb quin algorisme s'ha comprimit gràcies a la metadata del comprimit.
