#!/bin/bash 


Arxius=($(find | grep -e ".*\.java"))

Bellavi=""
Lape=""
Man=""
Leyen=""
Ningu=""
for arxiu in ${Arxius[@]}; do
    nom=$(cat $arxiu | sed "/@author/! d; s/^\s*\*\s*@author\s*//g; s/Joan\s*//g")
    if [[ $nom =~ B.* ]]; then
        Bellavi="$Bellavi\n$arxiu"
    elif [[ $nom =~ Ma.* ]]; then
        Man="$Man\n$arxiu"
    elif [[ $nom =~ Mi.* ]]; then
        Leyen="$Leyen\n$arxiu"
    elif [[ $nom =~ L.* ]]; then
        Lape="$Lape\n$arxiu"
    else
        Ningu="$Ningu\n$arxiu"
    fi
done
echo "Joan Bellavista: "
    echo -e "$Bellavi\n"
    echo "Miguel Paracuellos: "
    echo -e "$Leyen\n"
    echo "Joan Lapeyra: "
    echo -e "$Lape\n"
    echo "Manel Aguilar: "
    echo -e "$Man\n"
    echo "Ningu: "
    echo -e "$Ningu\n"