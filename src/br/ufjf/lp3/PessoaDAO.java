/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.lp3;

import java.util.List;

/**
 *
 * @author igor
 */
public interface PessoaDAO {

   void excluir(Pessoa pessoaAExcluir) throws Exception;

   void insere(Pessoa p) throws Exception;

   List<Pessoa> listaTodos() throws Exception;
   
}
