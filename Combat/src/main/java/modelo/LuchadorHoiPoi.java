package modelo;

public class LuchadorHoiPoi
  extends LuchadorBase
{
  public LuchadorHoiPoi(String nombre, int fue, int con, int tam, int des, int per)
  {
    super(nombre, fue, con, tam, des, per);
    establecerEscuela("de Hoi-Poi");
  }
  

  public void calcularPE()
  {
    establecerPE(obtenerDES() * 3 + obtenerPER());
  }
}
