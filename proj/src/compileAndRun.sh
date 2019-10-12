
if [[ $1 != "-remove" ]]; then
    ArxiusAcompilar=$(find | grep .java | tr "\n" " ")
    echo $ArxiusAcompilar | sed "s/ /\n/g"
    javac $ArxiusAcompilar
else
    ArxiusAborrar=$(find | grep .class | tr "\n" " ")
    echo $ArxiusAborrar | sed "s/ /\n/g"
    rm $ArxiusAborrar
fi

if [[ $1 == "-run" || $1 == "-r" ]]; then
    java Main
fi