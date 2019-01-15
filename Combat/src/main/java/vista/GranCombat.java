package vista;


import java.io.BufferedReader;
import java.io.PrintStream;
import modelo.*;

public class GranCombat
{
  
  public static void main(String[] args)
  {
    boolean continuar = true;

    BufferedReader stdin = new BufferedReader(new java.io.InputStreamReader(System.in));
    
    System.out.println("Define a tus luchadores: ");
    System.out.println();
    LuchadorBase luchadorA = definirLuchador(60);
    luchadorA.calcularSecundarias();
    
    while (continuar)
    {
      LuchadorBase luchadorB = definirLuchador(60);
      luchadorB.calcularSecundarias();
      
      System.out.println("Los candidatos son:");
      System.out.println();
      mostrarLuchador(luchadorA);
      mostrarLuchador(luchadorB);
      
      System.out.println("Que empiece el combate!!!");
      System.out.println();
      while ((luchadorA.obtenerPR() > 0.0F) && (luchadorB.obtenerPR() > 0.0F))
      {
        combatir(luchadorA, luchadorB);
        if (luchadorB.obtenerPR() <= 0.0F)
        {
          System.out.println("EL LUCHADOR " + luchadorA.obtenerNombre() + " GANA EL COMBATE!!!");
        }
        else
        {
          combatir(luchadorB, luchadorA);
          if (luchadorA.obtenerPR() <= 0.0F)
          {
            System.out.println("EL LUCHADOR " + luchadorB.obtenerNombre() + " GANA EL COMBATE!!!");
          }
        }
      }
      
      if (luchadorA.obtenerPR() <= 0.0F)
      {
        luchadorA = luchadorB;
      }
      System.out.println();
      System.out.print("Deseas que el ganador se enfrente a un nuevo contrincante (S/N)?");
      
      try
      {
        String entrada = stdin.readLine();
        if ((entrada.equals("N")) || (entrada.equals("n")))
        {
          continuar = false;
        }
        else
        {
          luchadorA.incrementarPER();
          luchadorA.calcularSecundarias();
          System.out.println();
          System.out.println("Define un nuevo luchador para enfrentarse al ganador del anterior combate:");
          System.out.println();
        }
        

      }
      catch (Exception exc)
      {
        System.err.println("\nError: " + exc.getMessage());
        System.err.println();
      }
    }
  }
  



  public static LuchadorBase definirLuchador(int maxCaract)
  {
    String nombre = "Sin Nombre";
    int fue = 1;
    int con = 1;
    int tam = 1;
    int des = 1;
    int per = 1;
    LuchadorBase unLuchador = new LuchadorBase(nombre, fue, con, tam, des, per);
    boolean valoresOK = false;
    


    BufferedReader stdin = new BufferedReader(new java.io.InputStreamReader(System.in));
    
    try
    {
      System.out.print("Nombre del luchador: ");
      nombre = stdin.readLine();
      
      while (!valoresOK)
      {
        valoresOK = true;
        
        System.out.print("FUE: ");
        String entrada = stdin.readLine();
        fue = Integer.parseInt(entrada);
        System.out.print("CON: ");
        entrada = stdin.readLine();
        con = Integer.parseInt(entrada);
        System.out.print("TAM: ");
        entrada = stdin.readLine();
        tam = Integer.parseInt(entrada);
        System.out.print("DES: ");
        entrada = stdin.readLine();
        des = Integer.parseInt(entrada);
        System.out.print("PER: ");
        entrada = stdin.readLine();
        per = Integer.parseInt(entrada);
        
        if ((fue < 3) || (fue > 18) || (con < 3) || (con > 18) || (tam < 3) || 
          (tam > 18) || (des < 3) || (des > 18) || (per < 3) || (per > 18))
        {
          valoresOK = false;
          System.out.println("Las caracteristicas deben estar en el rango de 3 a 18");
        }
        if (fue + con + tam + des + per > maxCaract)
        {
          valoresOK = false;
          System.out.println("La suma de caracteristicas ha de ser menor o igual a " + maxCaract);
        }
        System.out.println();
      }
      
      System.out.println("Escuela: ");
      System.out.println("\t\t  0) Follet Tortuga");
      System.out.println("\t\t  1) Corb Genial");
      System.out.println("\t\t  2) Hoi-Poi");
      System.out.println("\t\t  3) Namac");
      System.out.println("\t\t  4) Ninguna");
      String entrada = stdin.readLine();
      int escuela = Integer.parseInt(entrada);
      System.out.println();
      
      switch (escuela)
      {

      case 0: 
        unLuchador = new LuchadorFolletTortuga(nombre, fue, con, tam, des, per);
        break;
      
      case 1: 
        unLuchador = new LuchadorCorbGenial(nombre, fue, con, tam, des, per);
        break;
      
      case 2: 
        unLuchador = new LuchadorHoiPoi(nombre, fue, con, tam, des, per);
        break;
      
      case 3: 
        unLuchador = new LuchadorNamac(nombre, fue, con, tam, des, per);
        break;
      
      default: 
        unLuchador = new LuchadorBase(nombre, fue, con, tam, des, per);
      

      }
      
    }
    catch (Exception exc)
    {
      System.err.println("\nError: " + exc.getMessage());
      System.err.println();
    }
    
    return unLuchador;
  }
  

  public static void mostrarLuchador(LuchadorBase unluchador)
  {
    System.out.print("Luchador " + unluchador.obtenerNombre());
    if (unluchador.obtenerEscuela() != "Ninguna")
    {
      System.out.print(", de la escuela " + unluchador.obtenerEscuela());
    }
    System.out.println();
    System.out.print("FUE = " + unluchador.obtenerFUE() + ", ");
    System.out.print("CON = " + unluchador.obtenerCON() + ", ");
    System.out.print("TAM = " + unluchador.obtenerTAM() + ", ");
    System.out.print("DES = " + unluchador.obtenerDES() + ", ");
    System.out.println("PER = " + unluchador.obtenerPER());
    System.out.print("PR = " + unluchador.obtenerPR() + ", ");
    System.out.print("PD = " + unluchador.obtenerPD() + ", ");
    System.out.print("PA(%) = " + unluchador.obtenerPA() + ", ");
    System.out.println("PE(%) = " + unluchador.obtenerPE());
    System.out.println();
  }
  




  public static void combatir(LuchadorBase luchadorAtaca, LuchadorBase luchadorEsquiva)
  {
    Resultado ataque = luchadorAtaca.atacar();
    if (!ataque.obtenerExito())
    {
      System.out.println(luchadorAtaca.obtenerNombre() + " ataca con un " + ataque.obtenerTirada() + "% y falla el golpe");
    }
    else
    {
      System.out.println(luchadorAtaca.obtenerNombre() + " ataca con un " + ataque.obtenerTirada() + "% y acierta el golpe");
      Resultado esquiva = luchadorEsquiva.esquivar(ataque.obtenerExtra());
      if (esquiva.obtenerExito())
      {
        System.out.println(luchadorEsquiva.obtenerNombre() + " esquiva con un " + esquiva.obtenerTirada() + "% y acierta la esquiva");
      }
      else
      {
        System.out.println(luchadorEsquiva.obtenerNombre() + " esquiva con un " + esquiva.obtenerTirada() + "% y falla la esquiva");
        if (esquiva.obtenerExtra() > 0.0F)
        {
          System.out.println(luchadorEsquiva.obtenerNombre() + " recibe un golpe que le baja la resistencia a " + esquiva.obtenerExtra());
        }
        else
        {
          System.out.println(luchadorEsquiva.obtenerNombre() + " cae vencido del golpe!");
        }
      }
    }
    System.out.println();
  }
}
