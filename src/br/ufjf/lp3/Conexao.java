package br.ufjf.lp3;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

   private static Connection instancia;

   public static Connection getInstance() throws ClassNotFoundException, Exception {
      if (instancia == null) {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
         instancia = DriverManager.getConnection("jdbc:derby://localhost:1527/2016-3-dcc", "usuario", "senha");
      }
      return instancia;
   }

}
