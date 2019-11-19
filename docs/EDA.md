# Estructures de Dades
 
## Dict_Encode
Estructura de dades on cada posició representarà una seqüencia de bytes b0,b1,...,bn-1,bn. Per entendre com es genera i s'obté aquesta seqüencia es recomanable mirar la descripció de la classe *Node* donat que és l'estructura de dades de cada posició del nostre Array.

Les propietats principals son les següents.

### reset_dictionary()
S'encarrega de reinicialitzar el diccionari, el qual passarà a tenir 256 posicions (codi ASCII). Només el cridarem quan el diccionari sigui ple (de manera automàtica) o en la construcció del qual.

### Ascii_value(byte c)
Retorna el valor númeric corresponent al byte c.

### Integer search_and_insert_BST(Integer i, byte c)
És la funció principal de la classe i s'encarrega de les següents funcions:
>En primer cas mira si cal reiniciar el diccionari, en aquest cas ho fa directament sense haver de cancel·lar tot el procés, etc.

>Cada cop que es crida es mira si ja tenim la cadena al diccionari indicada per (i,c).<br>
-En cas afirmatiu retornem la seva posició.<br>
-Si teniem el prefix però no la cadena, busquem exactament on s'insereix.<br>
-En cas contrari, l'afegim al diccionari.

## Dict_Decode
Estructura de dades on cada posició indicarà una seqüencia de bytes b0,b1,...,bn-1,bn. El primer valor de l'Array indica la posició del següent byte de la subcadena b0,...,bn-1 mentre que el segon valor és bn.

### reset_dictionary(Boolean b)
S'encarrega de reinicialitzar el diccionari, el qual passarà a tenir 256 posicions (codi ASCII) només en cas de que b sigui true. Només el cridarem quan el diccionari sigui ple (de manera automàtica) o en la construcció del qual.

### ArrayList<Byte> getWord(Integer i)
S'encarrega de recorrer de manera iterativa l'Array de la classe fins que el valor de i sigui -1. Amb això el que aconseguim és retornar la paraula que correspon a la seqüència encadenada de bytes a partir de i.

## Pair
És un template de la classe Pair de C++ el qual ha sigut creat per no haver d'utilitzar llibreries externes de Java.

## Node
Representa un node emprat per la representació de cadenes de caràcters, utilitzat en la classe LZW.

Consta de 4 atributs de classe:
- First -> Serà l'index al primer node que utilitzi la cadena de bytes que representi aquest.
- c -> Valor del node
- Left -> Index dels següents nodes(x) que utilitzin el mateix prefix que aquest i on x <= c
- Right -> Index dels següents nodes(x) que utilitzin el mateix prefix que aquest i on x > c

## ArrayCircular
Té la mateixa idea que una Array, però no hi ha cap remove. I, si s'excedeix el size del array, s'anira sobreescrivint els valors del inici i així seqüencialment amb un punter (anomenat start). És a dir, utilitza una política circular.
Té diferents funcions:
 
### setValue(byte value)
Que afegeix al array circular el valor value
 
### getValue(int index)
Que retorna el valor que es troba a la posició index de l'array circular. En el cas que es trobi fora de rang, es crea una excepció.
 
### getValueAmbDespl(int despl)
Utilitzat pel descompressor. El qual va despl vegades enrere des de la posició del punter, retornant el valor en aquella posició.
 
### isIn(byte value)
Retorna un boolean indicant si value es troba o no a l'array circular.
<br>
<br>
<br>
També hi ha operacions simples com retornar la posició del punter a la primera posició o el punter a la última posició, el nombre de valors afegits, que, com es obvi, té un màxim del valor que se li ha pasat per parametre al constructor, el qual serà la mida de la array.
 
## IntorByte
En aquesta classe es tracta de construir un objecte per obtenir una unitat mínima per al descompressor.
El constructor admetrà com a paràmetre un boolean, que indicarà si és un Byte (true) o la tupla despl-mida (false).
Per tant, té les operacions òbvies de set i get de els atributs byte, despl i mida.
Per últim, també té la consultora de si és un byte o dos Int's.

## Trie
Aquesta classe és una implementació general de l'estructura de dades Trie. Cada node representa una seqüencias de carácetres. Cada connexió entre nodes (pare-fill), representa un caràcter. I un node és la seqüencia de caràcters des d'ell fins a la raiz.
A més, cada node té un enter que identifica el seu seqüencia. (això serà útil per al compressor)

### insert(ArrayList<T> list, Integer index)
Inserta al Trie la ArrayList list amb un Integer index

### Integer indexNode(ArrayList<T> list)
Retorna l'índex de la ArrayList list.

## TrieNode
Aquesta estructura de dades representa els nodes del Trie.
L'atribut index representa l'índex des del root del Trie fins el mateix TrieNode. Després tenim children, que es un HashMap que representa els TrieNode's que són fills d'aquest. Després tenim dos consultores, una que retorna el map que conté els fills (children) i l'altre l'index d'aquest mateix TrieNode. Per últim, el constructor, que li passa com a paràmetre l'índex del TrieNode que s'acaba de crear.

 
# Algoritmes
 
## LZW (Lempel–Ziv–Welch, 1984)
 
El mètode LZW crea sobre la marxa, de manera automàtica i en una única passada, un diccionari de cadenes que es trobin dins del text a comprimir mentre al mateix temps es procedeix a la seva codificació. Aquest diccionari no és transmès amb el text comprimit, ja que el descompressor pot reconstruir-lo usant la mateixa lògica amb què el fa el compressor i, si està codificat correctament, tindrà exactament les mateixes cadenes que el diccionari del compressor tenia.
 
### Compressió
En la compressió amb LZW s'ha emprat l'estructura de dades *Dict_Encode*  (la qual ha estat definida a l'apartat de **Estructures de Dades**).
En un principi es va utilitzar un *Map\<Integer,String>*  i això feia que la compressió fos massa lenta. En aquest moment i tenint en compte els dos factors següents, es va prendre la decisió d'eliminar els Strings i simplificar la representació de les cadenes:
   - No necessitem emmagatzemar la cadena sencera de Strings.
   - Qualsevol nou String que tinguem es pot representar com un append d'un byte al String anterior.
Ara mateix ens quedariem doncs amb un *Map\<Integer,Byte>* on cada entrada representa l'index a la seqüència previa i el byte actual, inicialitzat amb (-1,*) on * són els primers 256 valors ASCII .
 
En aquest moment el compressor funcionava relativament bé però (inspirat en l'article de Juha Nieminen), es va decidir substituir el map per crear una estructura de dades propia que millores el funcionament, *Dict_Encode*. La definició de la qual, com bé s'ha dit al començament, ja està explicada més amunt, però la idea per voler utilitzar-la es que d'aquesta manera podiem buscar si una paraula ja estava al diccionari o en cas contrari afegir-la en una mateixa crida, sense haver de recorrer el contingut dos cops. A més, amb la representació d'arbre aconseguim agilitzar el procés donat que tindrem un byte ordenat amb tots els altres que accedeixin a un mateix prefixe.

D'aquesta manera el que farem es per cada byte que llegim del fitxer d'entrada, buscarem si la cadena que representa ja existeix, en aquest cas llegirem el següent amb la intenció de generar una cadena més gran, en cas contrari afegim aquesta cadena al diccionari i passem al byte següent.
 
 
### Descompressió
En la descompressió amb LZW hem emprat l'estructura de dades *Dict_Decode* (la qual ha estat definida a l'apartat de **Estructures de Dades**).
En un primer moment es va utilitzar un *ArrayList<String>* però aixó no era eficient per unes raons molt semblants a les de la compressió:
   - No necessitem emmagatzemar tota l'estona el text descomprimit.
   - Podem anar ajuntant els bytes que anem descomprimint amb els que ja estaven descomprimits.
 
Per aixó es van eliminar els Strings pel que es va quedar l'estructura de dades
*ArrayList\< Pair\<Integer,Byte> >* amb la qual només necessitariem anar unint els bytes segons l'index al que apunta cada qual.

D'aquesta manera el que farem es anar generant de nou un diccionari a mesura que anem descomprimint cada byte i retornant la cadena que representa cada decodificació.

## LZSS (Lempel–Ziv–Storer–Szymansk, 1982)
 
En aquesta classe es tracta la compressió mitjançant l'algorisme LZSS i la descompressió d'un input el qual ha estat comprimit amb aquest mateix algoritme. Per la part de la compressió es té una finestra corredissa de 2048 posicions (11 bits), ja que s'han fet proves estimant un equilibri eficiència/temps. Després la mida de 5 bits, per tant, es buscarà una compressió mínima de 3B. Per la part de la descompressió, s'agafarà la unitat mínima representada per IntorByte, on, com bé especifica aquella classe, seran dos Int's o un Byte.
 
### Compressió
 
Es crean dos estructures de dades: ArrayCircular, per la finestra corredissa i ArrayList, per la subparaula que es voldrà trobar a la finestra. <br>
L'ArrayList és de Bytes per la qüestió que s'ha de tractar utf-8, ja que si es posa de char's, java farà una interpretació ASCII. <br>
En el cas de l'ArrayCircular s'ha utilitzat per reduir el cost a nivell de temps del programa, ja que, anteriorment, s'utilitzava un TreeMap\<Integer, Byte> el qual, quan arribava al size màxim de la finestra, s'anava fent remove de les primeres posicions afegides (molt costós). Per tant, amb un array amb política circular, no cal fer cap remove i reestructurar (molt menys costós). <br>
A continuació, es fa un while, on, a cada iteració, s'agafa un byte del input. Amb aquest byte nou, s'afegeix primerament a l'estructura de dades ArrayList (subparaula). Aquesta, es buscarà si està o no a la finestra, mitjançant l'algorisme Knuth-Morris-Pratt, amb cost lineal. Per tant, es tenen dos casos: <br>
>S'ha trobat a la finestra: <br>
>>Llavors es continuarà la busqueda d'una subparaula més gran a la finestra afegint bytes a aquesta (amb un màxim de mida de 34, ja que, com s'utilitzen 5 bits de mida, 2⁵-1 == 31 (rang), llavors, se sap que 0, 1 i 2 no s'utilitzarà, per una qüestió que no es comprimiran bytes de mida més petita a 3, es pot afegir +3 a la mida per comprimir). <br>
 
> No s'ha trobat a la finestra: <br>
>>Llavors, si la subparaula, es compleix que mida-1 >= 3 (-1 ja que al inici s'afegeix un byte a aquesta subparaula que no s'ha trobat), s'haurà d'afegir al Output el substring de la subparaula (de la posició 0 a size-2) i continuar buscant "match's" per l'últim byte afegit a la subparaula.<br>
En el cas que no es compleixi la condició anterior, s'haurà d'afegir el byte sense comprimir al Output.
 
### Descompressió
 
Es crea una estructura de dades ArrayCircular, per representar la finestra corredissa ja introduïda, la qual, s'anirà omplint a mesura que estem al while agafant byte's del input. També es crea un IntorByte, ja introduït a apartats anteriors.
Dins del while, s'aniran agafant instàncies IntorByte, les quals, representaran, o bé un byte i per tant, s'afegeix a la finestra i al output, o bé dos Int's, els quals seran una tupla punter/tamany, que s'aniran afegint al Output i a la vegada a la finestra.
 
 
## LZ78 (Lempel-Ziv, 1978)
LZ78 és un algoritme *lossless*, i que en temps lineal comprimeix un arxiu qualsevol. La estructura de dades que utilitza és un diccionari, el qual és una col·lecció potencialment il·limitada de frases vistes anteriorment. Cada frase en el diccionari té associada un índex que codificarà aquesta frase.

### Compressió
La compressió té el següent funcionament: A mesura que va llegint, va introduïnt frases a un diccionari, i després, quan es troba una repetició d'alguna frase que estigui al diccionari, *imprimeix* l'índex de l'entrada del diccionari en lloc de la frase.
En la meva implementació de l'algoritme, vaig començar utilitzant un HashMap<String,Integer>. La clau era la frase, i el valor era el índex que representava aquella frase en concret. El algoritme funcionava bé, a una bona velocitat (seguia sent lineal). Però utilitzava molt espai de memòria (RAM). 
Vaig decidir canviar el HashMap per un altre tipus de diccionari: El Trie. L'aventatge que té el Trie respecte el HashMap és que, tot i mantenir la funció *find* a cost temporal constant, utilitza molta menys memòria. Ja que cada frase emmagatzemada al Trie ocupa sempre 1 byte, mentre que el HashMap ocupava tants bytes com la mida de la frase. Això es deu a la estructura que segueix el Trie, que cada node representa la seqüència de bytes que va des d'ells fins l'arrel.

### Descompressió
El text descomprimit, per la natura de la compressió, és sempre una seqüència de Pair<Integer,Byte>. Tenint això en compte, la descompressió segueix aquest funcionament: Anem generant un nou diccionari a mesura que llegim la entrada. A més, els integers que llegim són entrades al diccionari que existeixen (a no ser que sigui 0, que aleshores no està al diccionari i hem d'afegir-lo). Quan llegim un integer, *imprimim* la frase apuntada per aquest. Els bytes els *imprimim* tal i com els llegim.

L'estructura de dades emperada a la descompressió ha estat *Dict_Decode* (la qual ha estat definida a l'apartat de **Estructures de Dades**).

D'aquesta manera el que farem es anar generant de nou un diccionari a mesura que anem descomprimint cada byte i retornant la cadena que representa cada decodificació.

## JPEG (Joint Photographic Experts Group, 1992)


### Compressió


### Descompressió
