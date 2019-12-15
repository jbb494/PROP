package global;

import java.util.Enumeration;

public abstract class global {
    public enum type{
        folder,
        text,
        image
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
