package global;

public abstract class global {
    public enum type{
        folder,
        text,
        image,
        comprimit,
        imageComprimit,
        textComprimit,
        folderComprimit
    }

    public enum method {
        lz78,
        lzw,
        lzss,
        jpeg
    }
    public enum jpegCompression {
        low,
        medium,
        high
    }
}