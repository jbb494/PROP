package persistencia.input;

import persistencia.output.Ctrl_Output;

public class Ctrl_Input_JPEG extends Ctrl_Input {

    int width, height;

    public Ctrl_Input_JPEG(String path) {
        super(path);
        getMetadata();
        width = get(32);
        height = get(32);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
 
    
    public int get(int size) {
        if (size == 0) return 0;
        int sz;
        if (size < 8) sz = size;
        else sz = 8;

        int x = ((int)Input_class.getBits(sz)) & ~(0xffffffff << sz);
        if (size > 8) x = x + (get(size-8) << 8);
        return x;
    }
        
}