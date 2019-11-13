
if [[ $1 != "-remove" || $1 == "-clean" ]]; then
    ArxiusAcompilar=$(find | grep .java | tr "\n" " ")
    echo $ArxiusAcompilar | sed "s/ /\n/g"
    javac $ArxiusAcompilar -d ../bin
fi

if [[ $1 == "-run" || $1 == "-r" ]]; then
    echo ""
    echo ""
    cd ../bin
    java Main
    cd ../src
fi
