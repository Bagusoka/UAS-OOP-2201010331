package sewamotor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author INSTIKI
 * TGL: 2024-05-30
 */
public class Koneksi {
    public static Connection buatkoneks(){
        Connection cnn = null;
        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // /sewamotor adalah nama databasenya
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sewamotor","root","");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Koneksi ke DBMS MySQL gagal");
        }
        return cnn;
    }
}
