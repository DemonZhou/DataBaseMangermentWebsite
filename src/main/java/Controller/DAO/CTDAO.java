package Controller.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CTDAO extends DAO{
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
        String sql = "select * from CT";
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

    public boolean delete(Integer id)throws Exception{
        System.out.println(id);
        Connection conn = link();
        String  sql = "delete from CT where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean update(Integer id ,String Cid , String Tid)throws Exception{
        String sql = "update CT set Cid = ?,Tid = ? where id = ?";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Cid);
        ps.setString(2,Tid);
        ps.setInt(3,id);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean add(String Cid , String Tid) throws Exception{
        String sql = "insert into CT (id,Cid,Tid) values(seq_CTId.Nextval,?,?) ";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Cid);
        ps.setString(2,Tid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean updateOradd(Integer id,String Cid , String Tid)throws Exception{
        if(id != null && isExist("select * from CT where id = '" + id +"'"))
            return update(id,Cid,Tid);
        else
            return add(Cid,Tid);
    }
}
