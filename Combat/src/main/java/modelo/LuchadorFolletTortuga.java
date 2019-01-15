package modelo;

public class LuchadorFolletTortuga
  extends LuchadorBase
{
  public LuchadorFolletTortuga(String nombre, int fue, int con, int tam, int des, int per)
  {
    super(nombre, fue, con, tam, des, per);
    establecerEscuela("del Follet Tortuga");
  }
  

  public void calcularPD()
  {
    establecerPD((obtenerFUE() + obtenerTAM() + obtenerPER()) / 4.0F);
  }
}
