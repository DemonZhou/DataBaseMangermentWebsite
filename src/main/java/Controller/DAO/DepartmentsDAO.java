package Controller.DAO;

import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class DepartmentsDAO extends DAO{
    public Connection link() throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        String url = "jdbc:oracle:thin:@localhost:1521:orcle";
        String user = "c##zhou";
        String password = "nbawade03";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }


    public HashMap selectAll() throws Exception{
        Connection conn = link();
        String sql = "select * from departments";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> namelist = GetColumns(rs);
        ArrayList<String []> arr = new ArrayList<String[]>();
        ResultSetMetaData rmd = rs.getMetaData();
        while(rs.next()){
            String [] s = new String[rmd.getColumnCount()];
            for(int i = 1;i <= rmd.getColumnCount();i++)
            {
                s[i - 1] = String.valueOf(rs.getObject(i));
            }
            arr.add(s);
        }
        HashMap<String,Object> res = new HashMap<String, Object>();
        res.put("namelist",namelist);
        res.put("data",arr);
        rs.close();
        conn.close();
        return res;
    }

    public boolean isExist(String sql)throws Exception{
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while(rs.next()){
            count ++;
        }
        rs.close();
        conn.close();
        return count > 0;
    }

    public boolean delete(String Did)throws Exception{
        Connection conn = link();
        String  sql = "delete from departments where Did = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Did);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean update(String Did , String Dname)throws Exception{
        String sql = "update departments set Dname = ? where Did = ?";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Dname);
        ps.setString(2,Did);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean add(String Did , String Dname) throws Exception{
        String sql = "insert into departments (Did,Dname) values(?,?) ";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Did);
        ps.setString(2,Dname);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean updateOradd(String Did , String Dname)throws Exception{
        if(isExist("select * from departments where Did = '" + Did +"'"))
            return update(Did,Dname);
        else
            return add(Did,Dname);
    }


}
