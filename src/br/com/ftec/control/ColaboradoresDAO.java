/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ftec.control;

import br.com.ftec.chaves.model.Colaborador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 018904
 */
public class ColaboradoresDAO {
    
    public void salvar(Colaborador colaborador) throws Exception{
        String sql = "INSERT INTO colaborador(nome,cpf,senha,telefone,email)"
                + "VALUES (?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        //verdadeiro catch-->errado
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, colaborador.getNome());
            pstm.setString(2,colaborador.getCpf());
            pstm.setString(3,colaborador.getSenha());
            pstm.setString(4,colaborador.getTelefone());
            pstm.setString(5,colaborador.getEmail());
            pstm.execute();
            
        
    
    }
}
