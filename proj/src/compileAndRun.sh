
if [[ $1 != "-remove" && $1 != "-clean" && $1 != "-driver" ]]; then
    ArxiusAcompilar=$(find | grep .java | tr "\n" " ")
    echo $ArxiusAcompilar | sed "s/ /\n/g"
    javac $ArxiusAcompilar -d ../bin
    cp -r ../bin/* ../'drivers y tests exe'
fi
if [[ $1 == "-driver" ]]; then
    cd ../'drivers y tests exe'
    java $2
    cd ../src
fi

if [[ $1 == "-run" || $1 == "-r" ]]; then
    echo ""
    echo ""
    cd ../bin
    java Main
    cd ../src
fi
