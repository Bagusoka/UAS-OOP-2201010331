/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewamotor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sewamotor.Koneksi.buatkoneks;

/**
 *
 * @author Bagus Oka
 */
public class State {
    /**
     * key = nama
     * value = id_kewarganegaraan
     * */
    static HashMap<String, Integer> kewarganegaraan = new HashMap<String, Integer>();
    static HashMap<String, Integer> pengguna = new HashMap<String, Integer>();
    static HashMap<String, Integer> motor = new HashMap<String, Integer>();

    static void init() {
        initKewarganegaraan();
        initMotor();
        initPengguna();
    }
    
    static void initMotor() {
        motor.clear();
        Connection cnn = buatkoneks();
        try {
            if( !cnn.isClosed() ){
               
                String sql = "SELECT CONCAT(merek, ' - ', plat_motor) AS nama, id_motor from motor;";                
                PreparedStatement PS = cnn.prepareStatement(sql);
                ResultSet rs = PS.executeQuery();
                while( rs.next() ){
                    State.motor.put(
                            rs.getString("nama"),
                            rs.getInt("id_motor") 
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void initPengguna() {
        pengguna.clear();
        Connection cnn = buatkoneks();
        try {
            if( !cnn.isClosed() ){
               
                String sql = "SELECT id_pengguna, nama_lengkap from pengguna";                
                PreparedStatement PS = cnn.prepareStatement(sql);
                ResultSet rs = PS.executeQuery();
                while( rs.next() ){
                    State.pengguna.put(
                            rs.getString("nama_lengkap"),
                            rs.getInt("id_pengguna") 
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void initKewarganegaraan() {
        Connection cnn = buatkoneks();
        kewarganegaraan.clear();
        try {
            if( !cnn.isClosed() ){
               
                String sql = "SELECT id_kewarganegaraan, nama from kewarganegaraan";                
                PreparedStatement PS = cnn.prepareStatement(sql);
                ResultSet rs = PS.executeQuery();
                while( rs.next() ){
                    State.kewarganegaraan.put(
                            rs.getString("nama"),
                            rs.getInt("id_kewarganegaraan") 
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
