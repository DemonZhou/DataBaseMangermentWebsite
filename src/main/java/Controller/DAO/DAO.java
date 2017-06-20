package Controller.DAO;

import java.sql.*;
import java.util.ArrayList;

public class DAO {
    public DAO()
    {


    }

    public ArrayList<String> GetColumns(ResultSet rs) throws SQLException {
        ArrayList<String> arr = new ArrayList<String>();
        ResultSetMetaData rmd = rs.getMetaData();
        for(int i = 1;i <= rmd.getColumnCount();i++){
            arr.add(rmd.getColumnName(i));
        }
        return arr;
    }

    public ArrayList<String[]> GetDisplayData(ResultSet rs) throws SQLException {
        int count = 0;
        while(rs.next())
            count ++;
        System.out.println("共有"+count+"条数据");
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
        return arr;
    }
}
