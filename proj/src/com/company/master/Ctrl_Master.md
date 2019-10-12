# Class Ctrl_Master

## Atributs

### Function

Depenent del seu valor realitzarem una funció o una altre:
- 1 -> Comprimir
- 2 -> Descomprimir 
- 3 -> Comprimir de manera manual

```java
Integer Function;
```

### Auto

```java
Boolean Auto;
```

### Path

```java
String Path;
```

### Input

```java
Input Inp;
```

### Output

```java
Output Out;
```






## Constructora

```java
public Ctrl_Master();
```



## Funcions privades





## Funcions públiques

### Context

S'encarregarà de demanar tot l'input necessari; serà compressió o descompressió?; en cas de que sigui compressió, serà manual o automàtica?; quin és el path de l'arxiu?.

```java
public void Context();
```

### Read

S'encarregarà de llegir l'arxiu *Path* i guardar-lo a *Inp*. 

```java
public void Read(); 
```

### Execution

S'encarregarà d'aplicar l'algorisme corresponent per a l'arxiu i guardar el resultat a *Out*.

```java
public void Execution();
```

### Write

Crearà un nou fitxer amb el resultat de la compressió / descompressió.

```java
public void Write();
```





