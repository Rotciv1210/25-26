package tareaProgramaciondeProcesos;
/*Realizar un programa que llame al proceso tasklist y a partir de los datos recibidos mediante la llamada a un proceso que me devuelva
 * cada linea como una tupla de datos con la que crear un objeto de los mismos y a partir de ellos realizar
 *  una lista o mapa ordenada por la cantidad de memoria usada.
 * Pista: Hacer un override del metodo compareTo
 */
public class Procesos implements Comparable<Procesos>{

    private String nombre;
    private int memoria;
    private int pid;

    public Procesos(String nombre, int memoria, int pid){

        this.nombre = nombre;
        this.memoria = memoria;
        this.pid = pid;

    }

        public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getMemoria() {
        return memoria;
    }
    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public int compareTo(Procesos    otroProceso){

        return Integer.compare(otroProceso.memoria, this.memoria);
    }

    @Override
    public String toString(){

        return String.format("nombre:"+nombre+"Memoria:"+memoria+"Pid:"+pid);
    }
}

