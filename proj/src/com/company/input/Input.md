# Class Input 

## Atributs

### In

Representa el contigut del fitxer llegit.

```java
ArrayList<Byte> In;
```

### Path

```java
String Path;
```

## Constructura

Assignarà **path** a *Path* i cridarà internament Read.

```java
public Input(String path);
```

## Funcions privades

### Read

Llegeix el fitxer amb ruta *path* i el guarda Byte a Byte a *In*

```java
private void Read();
```



## Funcions públiques

### GetIn

Retorna *In*

```java
public ArrayList<Byte> GetIn();
```

