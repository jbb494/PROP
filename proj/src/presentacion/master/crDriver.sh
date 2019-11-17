#!/bin/bash 

NomArxiuInp=$1
NomClasse=$(echo $1 | sed -e "s/\.java//" )
NomDriver="Driver__$NomClasse"
NomArxiuOut="Driver__"$NomArxiuInp
if [[ -f $NomArxiuOut ]]; then
    echo "The driver $NomArxiuOut already exists"
    exit 1
fi
RegexPerNomsValids="\(\w\|\d\|,\|<\|>\|_\|-\|[\|]\)"
RegexPerTotMenysPar="\(\w\|\d\|,\|<\|>\|_\|-\|\s\|[\|]\)"
IFS=$'\n'
ArrayFuncions=($(sed -e "/\s*public\(\s*$RegexPerNomsValids*\)*($RegexPerTotMenysPar*).*/! d; ; /\* @/ d; s/public\s*//g; s/static\s*//g;/^\s*class/ d; /^\s*$RegexPerNomsValids*(/ d ;s/{\s*//g; s/^\s*$RegexPerNomsValids*\s*\(.*\)$/\2/g; /^()/ d" $NomArxiuInp))
Constructores=($(sed -e "/\s*public\s*$RegexPerNomsValids*($RegexPerTotMenysPar*).*/! d; /\* @/ d;s/\s*{//g;s/\s*}//g; s/^\s*//g; s/^public\s*//g; s/public\s*//g;" $NomArxiuInp))
Package=$(sed "/^package/! d" $NomArxiuInp)

echo -n "" > $NomArxiuOut
echo -e "$Package \n" >> $NomArxiuOut
echo -e "import java.io.BufferedReader;\nimport java.io.InputStreamReader;\n" >> $NomArxiuOut

echo -e "/**\n * @class $NomDriver\n * @brief Driver de $NomClasse\n * @author Joan Bellavista Bartroli\n */" >> $NomArxiuOut

echo -e "public class $NomDriver {\n" >> $NomArxiuOut

echo -e "\t/**\n\t * @fn private static void showOptions()\n\t * @brief Mostra les accions a realitzar durant l'execució\n\t*/\n\tprivate static void showOptions(){\n\t\tSystem.out.println(\"Driver de $NomClasse\");" >> $NomArxiuOut
echo -e "\t\tSystem.out.println(\"Constructores: \");" >> $NomArxiuOut
i=1
for key in "${Constructores[@]}"; do
    echo -e "\t\tSystem.out.println(\"\t $i. $key\");" >> $NomArxiuOut
    echo -e "\t\tSystem.out.println(\"\t Input: $i\");" >> $NomArxiuOut

    i=$((i+1))
done
nombreConstructors=$((i-1))
echo >> $NomArxiuOut 
echo -e "\t\tSystem.out.println(\"Funciones: \");" >> $NomArxiuOut

for key in "${ArrayFuncions[@]}"; do
    echo -e "\t\tSystem.out.println(\"\\t $i. $key\");" >> $NomArxiuOut
    echo -e "\t\tSystem.out.println(\"\t Input: $i\");" >> $NomArxiuOut

    i=$((i+1))
done
NomVariableClasse=$(echo "$NomClasse" | tr '[:upper:]' '[:lower:]')
echo -e "\t\tSystem.out.println();" >> $NomArxiuOut

echo >> $NomArxiuOut
echo -e "\t\tSystem.out.println(\"\\t 0. Sortir\");" >> $NomArxiuOut
echo -e "\t\tSystem.out.println(\"\t Input: 0\");" >> $NomArxiuOut
echo -e "\t\tSystem.out.println(\"----------------------------------------\");" >> $NomArxiuOut
echo -e "\t}" >> $NomArxiuOut
echo -e "\t/**\n\t * @fn private static void comprovarExcepcions($NomClasse $NomVariableClasse, String linea)\n\t * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe\n\t * @param $NomVariableClasse\n\t * @param linea\n\t */" >> $NomArxiuOut
echo -en "\tprivate static void comprovarExcepcions($NomClasse $NomVariableClasse, String linea){\n\t\tint op = Integer.parseInt(linea);\n\t\tif($NomVariableClasse == null &&" >> $NomArxiuOut
echo -e " op > $nombreConstructors) {\n\t\t\tthrow new IllegalArgumentException(\"Debes llamar al constructor antes\");\n\t\t}\n\t}" >> $NomArxiuOut

echo -e "\n\tpublic static void main(String[] args) {" >> $NomArxiuOut
echo -e "\ttry {\n\t\t$NomClasse $NomVariableClasse = null;" >> $NomArxiuOut
echo -e "\t\tshowOptions();\n\t\tBufferedReader reader = new BufferedReader(new InputStreamReader(System.in));\n\t\tString linea = \"\";\n\t\twhile(linea != null){" >> $NomArxiuOut
echo -e "\t\t\tSystem.out.println(\"Selecciona una opción:\");\n\t\t\tlinea = reader.readLine().trim();\n\t\t\tSystem.out.println(\"Opción: \" + linea + \" seleccionada\");\n\t\t\tcomprovarExcepcions($NomVariableClasse, linea);" >> $NomArxiuOut
echo -e "\t\t\tswitch(linea){" >> $NomArxiuOut


i=1
for constr in "${Constructores[@]}"; do
    echo -e "\t\t\t\tcase \"$i\":" >> $NomArxiuOut
    Arguments=($(echo $constr | sed "s/^\(\w\|_\|\d\|\s\)*(//g; s/)//g; s/^\s*//g"))
    Name=($(echo $constr | sed "s/(.*//g"))
    IFS=',' read -r -a atributs <<< "$Arguments"
    if [[ ${#atributs[@]} -ge 2 ]]; then
        echo -e "\t\t\t\t\tSystem.out.println(\"\");" >> $NomArxiuOut
        echo -e "\t\t\t\t\tString[] aux$i = reader.readLine().trim().split(\" \");" >> $NomArxiuOut
    elif [[ ${#atributs[@]} -eq 1 ]]; then
        echo -e "\t\t\t\t\tSystem.out.println(\"\");" >> $NomArxiuOut
        echo -e "\t\t\t\t\tString aux$i = reader.readLine().trim();" >> $NomArxiuOut
    fi

    for atr in "${atributs[@]}"; do
        echo -ne "\t\t\t\t\t$(echo $atr | sed 's/^\s*//g; s/\s*$//g')" >> $NomArxiuOut
        echo ";" >> $NomArxiuOut
    done
    echo -ne "\t\t\t\t\t$NomVariableClasse = new $Name(" >> $NomArxiuOut
    j=1
    for atr in "${atributs[@]}"; do
        atr=$(echo $atr | sed "s/\s*$//g")
        IFS=' ' read -r -a aux1 <<< "$atr"
        if [[ j -eq ${#atributs[@]} ]]; then
        echo -ne "${aux1[1]}" >> $NomArxiuOut
        else
        echo -ne "${aux1[1]}, " >> $NomArxiuOut
        fi
        j=$((j+1))
    done
    echo ");">> $NomArxiuOut
    echo -e "\t\t\t\tbreak;" >> $NomArxiuOut
    i=$((i+1))
done
for func in "${ArrayFuncions[@]}"; do
    echo -e "\t\t\t\tcase \"$i\":" >> $NomArxiuOut
    Arguments=($(echo $func | sed "s/^\(\w\|_\|\d\|\s\)*(//g; s/)//g; s/^\s*//g"))
    Name=($(echo $func | sed "s/(.*//g"))
    IFS=',' read -r -a atributs <<< "$Arguments"
    if [[ ${#atributs[@]} -ge 2 ]]; then
        echo -e "\t\t\t\t\tSystem.out.println(\"\");" >> $NomArxiuOut
        echo -e "\t\t\t\t\tString[] aux$i = reader.readLine().trim().split(\" \");" >> $NomArxiuOut
    elif [[ ${#atributs[@]} -eq 1 ]]; then
        echo -e "\t\t\t\t\tSystem.out.println(\"\");" >> $NomArxiuOut
        echo -e "\t\t\t\t\tString aux$i = reader.readLine().trim();" >> $NomArxiuOut
    fi

    for atr in "${atributs[@]}"; do
        echo -ne "\t\t\t\t\t$(echo $atr | sed 's/^\s*//g; s/\s*$//g')" >> $NomArxiuOut
        echo ";" >> $NomArxiuOut
    done
    echo -ne "\t\t\t\t\t$NomVariableClasse.$Name(" >> $NomArxiuOut
    j=1
    for atr in "${atributs[@]}"; do
        atr=$(echo $atr | sed "s/\s*$//g")
        IFS=' ' read -r -a aux1 <<< "$atr"
        if [[ j -eq ${#atributs[@]} ]]; then
        echo -ne "${aux1[1]}" >> $NomArxiuOut
        else
        echo -ne "${aux1[1]}, " >> $NomArxiuOut
        fi
        j=$((j+1))
    done
    echo ");">> $NomArxiuOut
    echo -e "\t\t\t\tbreak;" >> $NomArxiuOut
    i=$((i+1))
done
echo -e "\t\t\t\tcase \"0\":" >> $NomArxiuOut
echo -e "\t\t\t\t\treturn;" >> $NomArxiuOut
echo -e "\t\t\t\tdefault:\n\t\t\t\t\tSystem.out.println(\"La opción no es válida\");\n\t\t\t\tbreak;" >> $NomArxiuOut
echo -e "\t\t\t}\n\t\t}\n\t}catch (Exception e) {\n\t\te.printStackTrace();\n\t}" >> $NomArxiuOut
echo -e '\t}\n}' >> $NomArxiuOut
