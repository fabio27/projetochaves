/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ftec.controll;

import br.com.ftec.chaves.model.Colaborador;
import br.com.ftec.chaves.model.Reserva;
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
public class ReservaDAO {

public void salvar(Reserva reserva) {

        //criação do SQL para salvar valores da sala no banco
        //cada ? representa os valores que serão colocados para cada campo
        //em ordem.
        String sql = "insert into reserva(id_sala,id_colaborador, turno, dia)"
                + " values (?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {// o try é oque acontece se existir sucesso na conexão
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, reserva.getSala().getId()); //cada pstm.set coloca valor em uma das? na ordem
            pstm.setInt(2, reserva.getColaborador().getId());
            pstm.setString(3, reserva.getTurno());
            pstm.setString(4, reserva.getDia());
            pstm.execute(); // executa o sql


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public List<Reserva> listaReservas() throws ClassNotFoundException, SQLException{
    
     String sqli = "SELECT * FROM RESERVA";
     
     Connection conn = null;
       PreparedStatement pstm = null;
       // classe que vai recuperar dados
       ResultSet rset = null;
       ArrayList<Reserva> listareservas = new ArrayList<Reserva>();
       
       conn = ConnectionFactory.createConnectionToMySQL();
       pstm = conn.prepareStatement(sqli);
       rset= pstm.executeQuery();
       
       while(rset.next()){
           
       Reserva reserva = new Reserva();
       Colaborador c =new Colaborador();
       Sala s = new Sala();
       c.setId(rset.getInt("id_colaborador"));
       reserva.setColaborador (c);
       s.setId(rset.getInt("id_sala"));
       reserva.setSala(s);
       reserva.setDia(rset.getString("dia"));
       reserva.setTurno(rset.getString("turno"));
       listareservas.add(reserva);
           
           
       }
        return listareservas; 
    }
}
