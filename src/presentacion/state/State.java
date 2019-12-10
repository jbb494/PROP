package presentacion.state;

import domini.algorithm.Ctrl_Algorithm;
import domini.estadistica.Estadistica;
//import domini.utils.Pair;

public abstract class State {

    protected Estadistica est;

    protected Ctrl_Algorithm CAlg;
    
    protected String Path_In;

    protected String Path_Out;

    public State(String Path, Boolean b) {
        this.Path_In = Path;
        int i = Path.lastIndexOf(".");
        this.Path_Out = Path.substring(0, i+1);

        est = new Estadistica(b);

        CAlg = new Ctrl_Algorithm();
    }

    public void finished() {
        est.show_estadistica(Path_In, Path_Out);
    }

    public abstract String work();


    

      

}