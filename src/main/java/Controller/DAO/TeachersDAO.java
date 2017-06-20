package Controller.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TeachersDAO extends DAO{
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
        String sql = "select * from teachers";
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

    public boolean delete(String Sid)throws Exception{
        Connection conn = link();
        String  sql = "delete from teachers where Tid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Sid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean update(String Tid , String Tname, String Sex, String Did)throws Exception{
        String sql = "update teachers set Tname = ?,Sex = ?,Did = ? where Tid = ?";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Tname);
        ps.setString(2,Sex);
        ps.setString(3,Did);
        ps.setString(4,Tid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean add(String Tid , String Tname, String Sex,String Did) throws Exception{
        String sql = "insert into teachers (Tid, Tname ,Sex,Did ) values(?,?,?,?) ";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Tid);
        ps.setString(2,Tname);
        ps.setString(3,Sex);
        ps.setString(4, Did);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean updateOradd(String Tid , String Tname, String Sex, String Did)throws Exception{
        if(isExist("select * from teachers where Tid = '" + Tid +"'"))
            return update(Tid,Tname,Sex,Did);
        else
            return add(Tid,Tname,Sex,Did);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
