package br.ufjf.lp3;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

class PessoaTableModel extends AbstractTableModel {

   private final Connection conexao;
   private ArrayList<Pessoa> pessoas;

   public PessoaTableModel(Connection conexao) {
      this.conexao = conexao;
   }

   @Override
   public int getRowCount() {
      if (pessoas != null) {
         return pessoas.size();
      } else {
         return 0;

      }
   }

   @Override
   public int getColumnCount() {
      return 2;
   }

   @Override
   public Object getValueAt(int rowIndex, int columnIndex) {

      atualizaDados();
      switch (columnIndex) {
         case 0:
            return pessoas.get(rowIndex).getNome();
         case 1:
            return pessoas.get(rowIndex).getTelefone();
         default:
            return "?";
      }
   }

   @Override
   public String getColumnName(int column) {
      switch (column) {
         case 0:
            return "Nome";
         case 1:
            return "Telefone";
         default:
            return "?";
      }

   }

   private void atualizaDados() {
      pessoas = new ArrayList<>();
      try {
         Statement operacao = conexao.createStatement();
         ResultSet resultado = operacao.executeQuery("SELECT nome,telefone FROM pessoa");
         int l = 0;
         while (resultado.next()) {
            String nome = resultado.getString(1);
            String telefone = resultado.getString(2);
            pessoas.add(new Pessoa(nome, telefone));

         }
      } catch (SQLException ex) {
         Logger.getLogger(PessoaTableModel.class
                 .getName()).log(Level.SEVERE, null, ex);

      }
   }

}
