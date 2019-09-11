/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ftec.controll;

import br.com.ftec.chaves.model.Colaborador;
import br.com.ftec.chaves.model.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADM
 */
public class ColaborarDAO {

    /* Isso é uma sql comum, os ? são os 
         * parâmetros que nós vamos adicionar
         * na base de dados
     */
    public void salvar(Colaborador colaborador) {

        //criação do SQL para salvar valores da sala no banco
        //cada ? representa os valores que serão colocados para cada campo
        //em ordem.
        String sql = "INSERT INTO COLABORADOR(nome, cpf, senha, telefone, email) "
                + "VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {// o try é oque acontece se existir sucesso na conexão
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, colaborador.getNome()); //cada pstm.set coloca valor em uma das? na ordem
            pstm.setString(2, colaborador.getCpf());
            pstm.setString(3, colaborador.getSenha());
            pstm.setString(4, colaborador.getTelefone());
            pstm.setString(4, colaborador.getEmail());

            pstm.execute(); // executa o sql

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ColaborarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ColaborarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public Colaborador buscaColaboradorCpf(String cpf) throws ClassNotFoundException, SQLException{
        
       String sql ="SELECT * FROM COLABORADOR WHERE CPF = '"+cpf+"'";
       Connection conn = null;
       PreparedStatement pstm = null;
       // classe que vai recuperar dados
       ResultSet rset = null;
       Colaborador colaborador = new Colaborador();
       conn = ConnectionFactory.createConnectionToMySQL();
       pstm = conn.prepareStatement(sql);
       rset= pstm.executeQuery();//executa o sql
       
       while(rset.next()){//jdbc
           colaborador.setId(rset.getInt("id"));
            colaborador.setEmail(rset.getString("email"));
            colaborador.setNome(rset.getString("nome"));
            colaborador.setSenha(rset.getString("Senha"));
            colaborador.setTelefone(rset.getString("telefone"));
           colaborador.setCpf(rset.getString("cpf"));
       }
       
        return colaborador;
    }

  public List<Colaborador> listaColaboradores() throws ClassNotFoundException, SQLException{
    
     String sqli = "SELECT * FROM COLABORADOR";
     
     Connection conn = null;
       PreparedStatement pstm = null;
       // classe que vai recuperar dados
       ResultSet rset = null;
       ArrayList<Colaborador> listaColaboradores = new ArrayList<Colaborador>();
       
       conn = ConnectionFactory.createConnectionToMySQL();
       pstm = conn.prepareStatement(sqli);
       rset= pstm.executeQuery();
       
       while(rset.next()){
           
       Colaborador colaborador = new Colaborador();
           colaborador.setId(rset.getInt("id"));
            colaborador.setEmail(rset.getString("email"));
            colaborador.setNome(rset.getString("nome"));
            colaborador.setSenha(rset.getString("Senha"));
            colaborador.setTelefone(rset.getString("telefone"));
           colaborador.setCpf(rset.getString("cpf"));
           listaColaboradores.add(colaborador);
           
           
       }
        return listaColaboradores;
  }
}
