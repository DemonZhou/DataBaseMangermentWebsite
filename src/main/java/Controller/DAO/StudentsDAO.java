package Controller.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentsDAO extends DAO{

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
        String sql = "select * from students";
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
        String  sql = "delete from students where Sid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Sid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean update(String Sid , String Sname, String Sex, Integer recruitdate, String Mid)throws Exception{
        String sql = "update students set Sname = ?,Sex = ?,recruitdate = ?,Mid = ? where Sid = ?";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Sname);
        ps.setString(2,Sex);
        ps.setInt(3, recruitdate);
        ps.setString(4,Mid);
        ps.setString(5,Sid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean add(String Sid , String Sname, String Sex, Integer recruitdate, String Mid) throws Exception{
        String sql = "insert into students (Sid, Sname ,Sex ,recruitdate ,Mid ) values(?,?,?,?,?) ";
        Connection conn = link();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Sid);
        ps.setString(2,Sname);
        ps.setString(3,Sex);
        ps.setInt(4, recruitdate);
        ps.setString(5,Mid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean updateOradd(String Sid , String Sname, String Sex, Integer recruitdate, String Mid)throws Exception{
        if(isExist("select * from students where Sid = '" + Sid +"'"))
            return update(Sid,Sname,Sex,recruitdate,Mid);
        else
            return add(Sid,Sname,Sex,recruitdate,Mid);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
