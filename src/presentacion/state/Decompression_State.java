package presentacion.state;

import domini.utils.Pair;

public class Decompression_State extends State {
    
    public Decompression_State(String Path) {
        super(Path, false);
    }

    public String work() {
        Pair<String,String> ret = CAlg.Auto_Decoder(super.Path_In);
        
        super.est.work_done();
        
        super.Path_Out += ret.L;
        
        return ret.R;
    }

}