public class Proceso implements Comparable<Proceso>{

    private String nombre;
    private int pid;
    private long memoria;

    public Proceso(String nombre, int pid, long memoria){

        this.nombre= nombre;
        this.pid=pid;
        this.memoria=memoria;
    }

    public int compareTo(Proceso p){
        return Long.compare(p.memoria,this.memoria);
    }

    public String toString(){
        return nombre+" "+pid+" "+memoria+ "KB";
    }
}
