package Controller.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursesDAO extends DAO {
    public CoursesDAO()
    {

    }

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
        String sql = "select * from courses";
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
        System.out.println(arr.size());
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

    public boolean delete(String Cid)throws Exception{
        Connection conn = link();
        String  sql = "delete from courses where Cid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Cid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean update(String Cid , String Cname,Double Credit,Integer Cnum,String Did,Integer Startweek,Integer Endweek,String info)throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        String url = "jdbc:oracle:thin:@localhost:1521:orcle";
        String user = "c##zhou";
        String password = "nbawade03";
        String sql = "update courses set Cname = ?,Credit = ?,Cnum = ?,Did = ?,Startweek = ?,Endweek = ?,info = ? where Cid = ?";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Cname);
        ps.setDouble(2,Credit);
        ps.setInt(3,Cnum);
        ps.setString(4,Did);
        ps.setInt(5,Startweek);
        ps.setInt(6,Endweek);
        ps.setString(7,info);
        ps.setString(8,Cid);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean add(String Cid , String Cname,Double Credit,Integer Cnum,String Did,Integer Startweek,Integer Endweek,String info) throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        String url = "jdbc:oracle:thin:@localhost:1521:orcle";
        String user = "c##zhou";
        String password = "nbawade03";
        String sql = "insert into courses (Cid, Cname ,Credit ,Cnum ,Did ,Startweek ,Endweek ,info) values(?,?,?,?,?,?,?,?) ";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,Cid);
        ps.setString(2,Cname);
        ps.setDouble(3,Credit);
        ps.setInt(4,Cnum);
        ps.setString(5,Did);
        ps.setInt(6,Startweek);
        ps.setInt(7,Endweek);
        ps.setString(8,info);
        boolean res =  ps.executeUpdate() > 0;
        conn.close();
        return res;
    }

    public boolean updateOradd(String Cid , String Cname,Double Credit,Integer Cnum,String Did,Integer Startweek,Integer Endweek,String info)throws Exception{
        if(isExist("select * from Courses where Cid = '" + Cid +"'"))
            return update(Cid,Cname,Credit,Cnum,Did,Startweek,Endweek,info);
        else
            return add(Cid,Cname,Credit,Cnum,Did,Startweek,Endweek,info);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
