package CoreSystem;

import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class JSONReader {
    public JSONReader(String path) throws Exception{
        Object obj = new JSONParser().parse(new FileReader(path));
        jo = (JSONObject) obj;
    }

    public <T> T GetValue(String key){
        T out = (T)jo.get(key);
        return out;
    }  

    public TreeMap<String, JSONArray> GetObjectMember(String key){
        Map<String, JSONArray> map = ((Map<String, JSONArray>)jo.get("animations"));
        
        TreeMap<String, JSONArray> out = new TreeMap<String, JSONArray>();
        out.putAll(map);
        return out;
    }

    private JSONObject jo;
}   
