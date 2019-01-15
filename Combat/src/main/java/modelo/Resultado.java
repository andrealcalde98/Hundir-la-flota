package modelo;


public class Resultado
{
  private boolean exito;
  private int tirada;
  private float extra;
  
  public Resultado(boolean exito, int tirada, float extra)
  {
    this.exito = exito;
    this.tirada = tirada;
    this.extra = extra;
  }
  
  public boolean obtenerExito()
  {
    return exito;
  }
  
  public int obtenerTirada() {
    return tirada;
  }
  
  public float obtenerExtra() {
    return extra;
  }
}
