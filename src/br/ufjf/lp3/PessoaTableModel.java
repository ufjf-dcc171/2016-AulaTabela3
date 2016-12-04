package br.ufjf.lp3;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

class PessoaTableModel extends AbstractTableModel {

   private List<Pessoa> pessoas;
   private final PessoaDAO dao;

   public PessoaTableModel() throws Exception {
      dao = new PessoaDAOJDBC();
   }

   @Override
   public int getRowCount() {
      atualizaDados();
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
         pessoas = dao.listaTodos();
      } catch (Exception ex) {
         Logger.getLogger(PessoaTableModel.class
                 .getName()).log(Level.SEVERE, null, ex);

      }
   }

}
