package DataBaseSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Gestion de bases de donnees
 * Permet la connexion a une base de donnees
 * Permet egalement l'envoi de requetes
 * @author Maxime Emonnot
 */
public class DataBaseManager {
    /**
     * Constructeur du Manager. Se connecte a la base de donnees decrites
     * @author Maxime Emonnot
     * @param _databaseName Nom de la base de donnees 
     * @param password Mot de passe pour se connecter a la base de donnees
     * @throws ClassNotFoundException Erreur lors de la recherche du Driver SQL
     * @throws SQLException Erreur SQL lors de la connexion et de l'initialisation de l'objet Statement
     */
    public DataBaseManager(String _databaseName, String password) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        databaseName = _databaseName;
        String url = "jdbc:mysql://localhost:3306/" + _databaseName + "?useUnicode=true&characterEncoding=utf8&useSSL=false"; //&serverTimezone=CTT";
        con = DriverManager.getConnection(url, "root", password);
        stmt = con.createStatement();
    }

    /**
     * Envoi d'une requete de recuperation de donnees (SELECT)
     * @author Maxime Emonnot
     * @param request Requete SQL a envoyer
     * @return Le resultat obtenu
     * @throws SQLException Erreur si la requete est erronnee
     */
    public ResultSet GetResultFromSQLRequest(String request) throws SQLException{
        return stmt.executeQuery(request);
    }

    /**
     * Envoi d'une requete de mise à jour de base de donnees (UPDATE, INSERT, DELETE)
     * @author Maxime Emonnot
     * @param request Requete SQL a envoyer
     * @throws SQLException Erreur si la requete est erronnee
     */
    public void SendSQLRequest(String request) throws SQLException{
        stmt.executeUpdate(request);
    }

    /**
     * Conversion en ArrayList des ResultSet des requetes
     * @author Maxime Emonnot
     * @param rs Resultat d'une requete SQL
     * @return L'ArrayList correspondant au resulat
     * @throws SQLException Lie a la manipulation d'un objet ResultSet
     * @see DataBaseManager#GetResultFromSQLRequest(String)
     */
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

    /**
     * Recuperation du nom de la base de donnees
     * @author Maxime Emonnot
     * @return Le nom de la base de donnees inscrit lors de la connexion
     */
    public String GetDatabaseName(){
        return databaseName;
    }

    private String databaseName;
    private Statement stmt;
    private Connection con;
}
