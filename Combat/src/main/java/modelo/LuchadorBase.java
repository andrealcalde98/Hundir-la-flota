package modelo;

public class LuchadorBase {

    private String nombre;
    private String escuela;
    private int fue;
    private int con;
    private int tam;
    private int des;
    private int per;
    private float pr;
    private float pd;
    private int pa;
    private int pe;

    public LuchadorBase(String nombre, int fue, int con, int tam, int des, int per) {
        this.nombre = nombre;
        escuela = "Ninguna";
        this.fue = fue;
        this.con = con;
        this.tam = tam;
        this.des = des;
        this.per = per;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obtenerEscuela() {
        return escuela;
    }

    public int obtenerFUE() {
        return fue;
    }

    public int obtenerCON() {
        return con;
    }

    public int obtenerTAM() {
        return tam;
    }

    public int obtenerDES() {
        return des;
    }

    public int obtenerPER() {
        return per;
    }

    public float obtenerPR() {
        return pr;
    }

    public float obtenerPD() {
        return pd;
    }

    public int obtenerPA() {
        return pa;
    }

    public int obtenerPE() {
        return pe;
    }

    public void establecerEscuela(String escuela) {
        this.escuela = escuela;
    }

    public void establecerPR(float pr) {
        this.pr = pr;
    }

    public void establecerPD(float pd) {
        this.pd = pd;
    }

    public void establecerPA(int pa) {
        this.pa = pa;
    }

    public void establecerPE(int pe) {
        this.pe = pe;
    }

    public void calcularPR() {
        pr = (con + tam);
    }

    public void calcularPD() {
        pd = ((fue + tam) / 4.0F);
    }

    public void calcularPA() {
        pa = (des + fue + con);
    }

    public void calcularPE() {
        pe = (des * 3);
    }

    public void calcularSecundarias() {
        calcularPR();
        calcularPD();
        calcularPA();
        calcularPE();
    }

    public Resultado atacar() {
        boolean exito = false;

        float daño = 0.0F;

        int tirada = 1 + (int) (Math.random() * 99.0D);
        if (tirada <= pa) {
            exito = true;
            daño = pd;
        }
        Resultado ataque = new Resultado(exito, tirada, daño);
        return ataque;
    }

    public Resultado esquivar(float daño) {
        boolean exito = true;

        float pr = this.pr;

        int tirada = 1 + (int) (Math.random() * 99.0D);
        if (tirada > pe) {
            exito = false;
            pr -= daño;
            this.pr = pr;
        }
        Resultado esquiva = new Resultado(exito, tirada, pr);
        return esquiva;
    }

    public void incrementarPER() {
        per += 1;
    }
}
