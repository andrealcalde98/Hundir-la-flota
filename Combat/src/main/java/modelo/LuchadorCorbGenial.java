package modelo;

public class LuchadorCorbGenial
  extends LuchadorBase
{
  public LuchadorCorbGenial(String nombre, int fue, int con, int tam, int des, int per)
  {
    super(nombre, fue, con, tam, des, per);
    establecerEscuela("del Corb Genial");
  }
  

  public void calcularPA()
  {
    establecerPA(obtenerDES() + obtenerFUE() + obtenerCON() + obtenerPER());
  }
}
