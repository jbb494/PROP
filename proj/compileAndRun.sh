javac $(find | grep .java | sed "/main/ d " | sed "/LZW/ d" | sed "/JPEG/ d" | tr "\n" " ")

java src/main.java
