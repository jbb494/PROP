package presentacion.state;

public class Compression_State extends State {

    protected String mode;

    public Compression_State(String Path) {
        super(Path, true);

        mode = CAlg.Auto_Encoder(Path);

        super.Path_Out += "jm";
    }

    public Compression_State(String Path, String mode) {
        super(Path, true);

        this.mode = mode;

        super.Path_Out += "jm";
    }

    public String work() {
        String tornada =  CAlg.Choose_Encoder(super.Path_In, mode);
        
        est.work_done();  
        
        return tornada;
    }
}