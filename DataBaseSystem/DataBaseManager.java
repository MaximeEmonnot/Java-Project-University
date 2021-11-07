package DataBaseSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseManager {
    public DataBaseManager(String _databaseName, String password) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        databaseName = _databaseName;
        String url = "jdbc:mysql://localhost:3306/" + _databaseName + "?useUnicode=true&characterEncoding=utf8&useSSL=false"; //&serverTimezone=CTT";
        con = DriverManager.getConnection(url, "root", password);
        stmt = con.createStatement();
    }

    public ResultSet GetResultFromSQLRequest(String request) throws SQLException{
        return stmt.executeQuery(request);
    }

    public void SendSQLRequest(String request) throws SQLException{
        stmt.executeUpdate(request);
    }

    public ArrayList<ArrayList<Object>> GetArrayFrom(ResultSet rs) throws SQLException{
        ArrayList<ArrayList<Object>> out = new ArrayList<ArrayList<Object>>();
        //Add labels for each column
        ArrayList<Object> labels = new ArrayList<Object>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
            labels.add(rs.getMetaData().getColumnLabel(i));
        }
        out.add(labels);

        //Add values
        while(rs.next()){
            ArrayList<Object> line = new ArrayList<Object>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                line.add(rs.getObject(i));
            }
            out.add(line);
        }
        return out;
    }

    public String GetDatabaseName(){
        return databaseName;
    }

    private String databaseName;
    private Statement stmt;
    private Connection con;
}
