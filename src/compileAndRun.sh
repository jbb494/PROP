if [[ $1 == "make" ]]; then
    echo "Compilem els seguents arxius: "
    ArxiusAcompilar=$(find | grep .java | sed "/Test\.java$/ d" | tr "\n" " ")
    echo $ArxiusAcompilar | sed "s/ /\n/g;"
    cd ..
    if [[ ! -d "bin" ]]; then
        mkdir bin
    fi
    cd src
    javac -Xlint:unchecked $ArxiusAcompilar -d ../bin
elif [[ $1 == "-driver" ]]; then
    echo "Executem driver"
    cd ../bin
    java $2
    cd ../src
elif [[ $1 == "-Idriver" ]]; then
    cat 
elif [[ $1 == "-ctest" ]]; then 
    echo "Compilem tests"
    TestsAcompilar=$(find | grep .java | sed "/.*Test\./! d" | tr "\n" " ")
    echo $TestsAcompilar | sed "s/ /\n/g"
    javac -cp .:../bin/junit-4.12.jar:../bin/hamcrest-core-1.3.jar $TestsAcompilar -d ../bin
elif [[ $1 == "-etest" ]]; then
    echo "Executem tests"
    cd ../bin
    java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore $2
    cd ../src
elif [[ $1 == "-clean" || $1 == "-remove" ]]; then
    echo "Borrem els seguents arxius: "
    cd ../bin
    ArxiusABorrar=$(find | grep "\.class" | tr "\n" " ")
    echo $ArxiusABorrar | sed "s/ /\n/g;"
    rm $ArxiusABorrar
    cd ../src
elif [[ $1 == "run" ]]; then
    echo ""
    echo ""
    cd ../bin
    java Main
    cd ../src
else
    echo -e "Usage:  make compila el projecte."
    echo -e "\trun per executar el main."
    echo -e "\t-driver i el nom del driver que vols executar. Exemple: ./compileAndRun.sh -driver domini.algorithm.Driver__LZ78"
    echo -e "\t-ctest Compila els tests."
    echo -e "\t-etest i el nom del test que vols executar. Exemple: ./compileAndRun.sh -etest domini.algorithm.LZWTest"
    echo -e "\t-clean o -remove Borra els executables."
fi
