package CoreSystem;

import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

/**
 * Gestion des fichiers JSON.
 * Ouverture d'un fichier JSON et recuperation des donnees du fichier.
 * @author Maxime Emonnot
 */
public class JSONReader {
    /**
     * Constructeur pour l'ouverture du fichier JSON.
     * @author Maxime Emonnot
     * @param path Chemin du fichier JSON à lire
     * @throws Exception Si le fichier ne peut être lu (mauvais nom ou erreur dans le fichier)
     */
    public JSONReader(String path) throws Exception{
        Object obj = new JSONParser().parse(new FileReader(path));
        jo = (JSONObject) obj;
    }

    /**
     * Recuperation d'une valeur selon un nom de cle
     * @author Maxime Emonnot
     * @param <T> Genericite pour s'adapter au type de donnee enregistree
     * @param key Cle de la donnee a recuperer
     * @return Valeur de la donnee recuperee
     */
    public <T> T GetValue(String key){
        T out = (T)jo.get(key);
        return out;
    }  

    /**
     * Recuperation d'un groupe membre selon un nom de cle
     * @author Maxime Emonnot
     * @param key Cle du groupe a recuperer
     * @return Une map contenant tous les elements du groupe recupere
     */
    public TreeMap<String, JSONArray> GetObjectMember(String key){
        Map<String, JSONArray> map = ((Map<String, JSONArray>)jo.get(key));
        
        TreeMap<String, JSONArray> out = new TreeMap<String, JSONArray>();
        out.putAll(map);
        return out;
    }

    private JSONObject jo;
}   
