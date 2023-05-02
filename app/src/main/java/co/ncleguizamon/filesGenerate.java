package co.ncleguizamon;

import co.ncleguizamon.model.ConfigAction;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;

public class filesGenerate {


    public void filesSave(JavaFile javaFile) {

        try {
            javaFile.writeTo(System.out);
            File file = new File("target/directory");
            javaFile.writeTo(file);
        } catch (IOException e) {
            System.out.println(e);

        }

    }


    public void filesSaveF(ConfigAction configAction) {

        try {
            configAction.javaFile.writeTo(System.out);
            File file = new File(configAction.directoryExport);
            configAction.javaFile.writeTo(file);
        } catch (IOException e) {
            System.out.println(e);

        }

    }

    public void filesSave( ConfigAction configAction) {
        filesSaveF(configAction);
    }

}
