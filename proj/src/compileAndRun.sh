ArxiusAcompilar=$(find | grep .java | sed "/main/ d " | sed "/LZW/ d" | sed "/JPEG/ d" | tr "\n" " ")

echo $ArxiusAcompilar | sed "s/ /\n/g"

javac $ArxiusAcompilar

java main.java
