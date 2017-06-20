package Controller.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MajorsDAO extends DAO {
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
        String sql = "select * from majors";
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

    public boolean delete(String Mid)throws Exception{
        Connection conn = link();
        String  sql = "delete from majors where Mid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Mid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean update(String Mid , String Mname,String Did)throws Exception{
        String sql = "update majors set Mname = ?, Did = ? where Mid = ?";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Mname);
        ps.setString(2,Did);
        ps.setString(3,Mid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean add(String Mid , String Mname,String Did) throws Exception{
        String sql = "insert into majors (Mid,Mname,Did) values(?,?,?) ";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Mid);
        ps.setString(2,Mname);
        ps.setString(3,Did);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean updateOradd(String Mid , String Mname,String Did)throws Exception{
        if(isExist("select * from majors where Mid = '" + Mid +"'"))
            return update(Mid,Mname,Did);
        else
            return add(Mid,Mname,Did);
    }
}
