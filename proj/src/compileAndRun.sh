
if [[ $# -eq 0 ]]; then
    echo "Compilem els seguents arxius: "
    ArxiusAcompilar=$(find | grep .java | tr "\n" " ")
    echo $ArxiusAcompilar | sed "s/ /\n/g"
    javac -Xlint:unchecked $ArxiusAcompilar -d ../bin
elif [[ $1 == "-driver" ]]; then
    echo "Executem driver"
    cd ../bin
    java $2
    cd ../src
elif [[ $1 == "-ctest" ]]; then 
    echo "Compilem tests"
    TestsAcompilar=$(find | grep .java | sed "/.*Test\./! d" | tr "\n" " ")
    echo $TestsAcompilar | sed "s/ /\n/g"
    javac -cp .:junit.jar:hamcrest-core-1.3.jar $TestsAcompilar -d ../bin
elif [[ $1 == "-etest" ]]; then
    echo "Executem tests"
    cd ../bin
    java -cp .:junit.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore $2
    cd ../src
fi

if [[ $1 == "-run" || $1 == "-r" ]]; then
    echo ""
    echo ""
    cd ../bin
    java Main
    cd ../src
fi
