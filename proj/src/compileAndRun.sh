ArxiusAcompilar=$(find | grep .java | sed "/LZW/ d" | sed "/JPEG/ d" | tr "\n" " ")

echo $ArxiusAcompilar | sed "s/ /\n/g"

javac $ArxiusAcompilar

if [[ $1 == "-r" ]]; then
    java Main
fi
