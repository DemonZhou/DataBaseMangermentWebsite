package Controller.DAO;

import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class AdminDAO {
    public boolean verfiy(String name,String psd) throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        String url = "jdbc:oracle:thin:@localhost:1521:orcle";
        String user = "c##zhou";
        String password = "nbawade03";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "select * from Admin where name = ? and psd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,psd);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()){
            count ++;
        }
        System.out.println(count);
        rs.close();
        conn.close();
        return count > 0;
    }
}
