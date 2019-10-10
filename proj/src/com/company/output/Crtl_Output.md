# Class Ctrl_Output

Adaptarà els paràmetres d'entrada per a la classe ***Output***. 

## Atributs

### Output_class

Es derivaràn els paràmetres cap a la classe ***Output*** en format Byte.

``` java
Output Output_class;
```



## Constructura

Inicialitzarà *Output_class*;

``` java
public static Ctrl_Output();
```



## Funcions privades



## Funcions públiques

### Add

Ens trobem amb quatre possibles casos segons els paràmetres d'entrada, tot i que totes elles ho afegiran a *Output_class* en el format adient:

En aquest primer cas no caldrà cap transformació:

```java
public void add(Byte, Integer);
```

En els següents casos caldrà aplicar la corresponent transformació:

```java
//Per cada caràcter al String farem un add(Character)
public void add(String);
```

```java
public void add(Character);
```

```java
public void add(Integer);	
```



  