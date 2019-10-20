package emma.handler;

import org.ini4j.Ini;
import org.ini4j.Wini;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class FileHandler {

    private Wini ini;
    private String path;

    public FileHandler(String path){
        try {
            ini = new Wini(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(String key, String optionName, Class cl){
        return ini.get(key, optionName, cl);
    }

    public void set(String section, String option, Object value){
        ini.put(section, option, value);
        try {
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
