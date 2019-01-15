package modelo;

public class LuchadorNamac
  extends LuchadorBase
{
  public LuchadorNamac(String nombre, int fue, int con, int tam, int des, int per)
  {
    super(nombre, fue, con, tam, des, per);
    establecerEscuela("de Namac");
  }
  

  public void calcularPR()
  {
    establecerPR(obtenerCON() + obtenerTAM() + obtenerPER());
  }
}
