package co.ncleguizamon;

import co.ncleguizamon.model.ConfigAction;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class configPro {
    public ConfigAction.ConfigActionBuilder getConFig() {
        String prodP = "C:\\\\Users\\\\ncleguizamo\\\\Desktop\\\\personal\\\\test_javapocket\\\\app\\\\src\\\\main\\\\resources\\\\config.properties";
        //  try (OutputStream output = new FileOutputStream(prodP)) {
        Map<String, String> model = new HashMap<>();
        ConfigAction.ConfigActionBuilder result  =ConfigAction.builder();
        try (InputStream input = new FileInputStream(prodP)) {
            Properties prop = new Properties();
            prop.load(input);
            System.out.println(prop);


            prop.forEach((key, value) -> {
                if (key.equals("config.action")) {
                    result.action((String) value);
                }else if (key.equals("config.modelName")){
                    result.modelName((String) value);
                }else if (key.equals("config.package")){
                    result.packageName((String) value);
                }else if (key.equals("config.pk")){
                result.idName((String) value);
                }else if (key.equals("config.directory")){
                result.directory((String) value);
            }
                else {
                    model.put((String) key, (String) value);
                }
            });

            result.model(model);

        } catch (IOException io) {
            io.printStackTrace();
        }

        return result;
    }
}
