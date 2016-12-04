package br.ufjf.lp3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAOJDBC implements PessoaDAO {

   private PreparedStatement opInsere;
   private PreparedStatement opExcluir;
   private PreparedStatement opListar;

   public PessoaDAOJDBC() throws Exception {
      opInsere = Conexao.getInstance().prepareStatement("INSERT INTO pessoa(nome, telefone) VALUES (?, ?)");
      opExcluir = Conexao.getInstance().prepareStatement("DELETE FROM pessoa WHERE nome=? AND telefone = ?");
      opListar = Conexao.getInstance().prepareStatement("SELECT nome,telefone FROM pessoa");
   }

   @Override
   public void insere(Pessoa p) throws Exception {
      opInsere.clearParameters();
      opInsere.setString(1, p.getNome());
      opInsere.setString(2, p.getTelefone());
      opInsere.executeUpdate();
   }

   @Override
   public void excluir(Pessoa pessoaAExcluir) throws Exception {
      opExcluir.clearParameters();
      opExcluir.setString(1, pessoaAExcluir.getNome());
      opExcluir.setString(2, pessoaAExcluir.getTelefone());
      opExcluir.executeUpdate();

   }

   @Override
   public List<Pessoa> listaTodos() throws Exception {
         List<Pessoa> todos = new ArrayList<>();
         ResultSet resultado = opListar.executeQuery();
         int l = 0;
         while (resultado.next()) {
            String nome = resultado.getString(1);
            String telefone = resultado.getString(2);
            todos.add(new Pessoa(nome, telefone));

         }
         return todos;

   }

}
