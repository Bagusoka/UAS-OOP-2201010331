/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewamotor;

import java.sql.SQLException;

/**
 *
 * @author Bagus Oka
 */
public class Sewamotor {

    static PenggunaFrame penggunaFrame;
    static MotorFrame motorFrame;
    static SewamotorFrame sewamotorFrame;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
       
        
        penggunaFrame = new PenggunaFrame();
        motorFrame = new MotorFrame();
        sewamotorFrame = new SewamotorFrame();
        
        penggunaFrame.setVisible(true);
        
        
    }
    
}
