package Controller.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectDAO extends DAO{
    public Connection link() throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        String url = "jdbc:oracle:thin:@localhost:1521:orcle";
        String user = "c##zhou";
        String password = "nbawade03";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public class SqlCondition{
        public SqlCondition(){

        }
        String attr;
        String[] ops;

        public String getAttr() {
            return attr;
        }

        public String[] getOps() {
            return ops;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        public void setOps(String[] ops) {
            this.ops = ops;
        }
    }

    public HashMap<String, Object> split(String tablename, String data){
        String[] ts = tablename.split("&");
        String[] ds = data.split("\n");
        HashMap<String,Object> map = new HashMap<String, Object>();
        ArrayList<SqlCondition> ops = new ArrayList<SqlCondition>();
        map.put("tablename",ts);

        for(String d : ds){
            SqlCondition sc = new SqlCondition();
            sc.setAttr(d.split(":")[0]);
            sc.setOps(d.split(":")[1].split("&"));
            ops.add(sc);
        }
        map.put("ops",ops);
        return map;
    }

    public String Concat(String [] tablename,ArrayList<SqlCondition> ops){
        StringBuilder s = new StringBuilder("select * from ");
        for(int i = 0;i < tablename.length;i++){
            if(i < tablename.length - 1)
                s.append(tablename[i] + ",");
            else
                s.append(tablename[i] + " ");
        }
        if(ops.size() == 0)
            return s.toString();
        else{
            s.append("where ");
            int count = 0;
            for(SqlCondition sc:ops){
                String[] cs = sc.getOps();
                if(count == 0) {
                    s.append(cs[0] + " " + cs[1] + " " + "'" + cs[2] + "'");
                }
                else {
                    s.append(sc.getAttr()+" ");
                    s.append(cs[0] + " " + cs[1] + " " + "'" + cs[2] + "'");
                }
                count ++;
            }
        }
        return s.toString();
    }

    @Override
    public ArrayList<String[]> GetDisplayData(ResultSet rs) throws SQLException {
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

    public HashMap select(String tablename,String data) throws Exception{
        Connection conn = link();
        HashMap map = split(tablename,data);
        String s = Concat((String[])map.get("tablename"),(ArrayList<SqlCondition>)map.get("ops"));
        System.out.println(s);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(s);
        ArrayList<String> namelist = GetColumns(rs);
        ArrayList<String []> datas = GetDisplayData(rs);
        System.out.println("共有"+datas.size()+"条数据");
        ArrayList<String []> arr = new ArrayList<String[]>();
        ResultSetMetaData rmd = rs.getMetaData();
        while(rs.next()){
            String [] st = new String[rmd.getColumnCount()];
            for(int i = 1;i <= rmd.getColumnCount();i++)
            {
                st[i - 1] = String.valueOf(rs.getObject(i));
            }
            arr.add(st);
        }
        System.out.println("共有"+arr.size()+"条数据");
        HashMap<String,Object> res = new HashMap<String, Object>();
        res.put("namelist",namelist);
        res.put("data",datas);
        rs.close();
        conn.close();
        return res;
    }

}
