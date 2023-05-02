package co.ncleguizamon.r2dbc;

import co.ncleguizamon.filesGenerate;
import co.ncleguizamon.model.ConfigAction;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.Serializable;

public class generateDataTable {
    private String DEFAULT="/infrastructure/driven-adapters/r2dbc-postgresql/src/main/java";
    ClassName id = ClassName.get("org.springframework.data.annotation", "Id");
    ClassName column = ClassName.get("org.springframework.data.relational.core.mapping", "Column");

    public ConfigAction.ConfigActionBuilder generateDataTable(ConfigAction.ConfigActionBuilder result) {
        generateDataTable g = new generateDataTable();
        String className = result.build().modelName + "Data";
        filesGenerate fsave = new filesGenerate();

        ClassName lombokNoCons = ClassName.get("lombok", "NoArgsConstructor");
        ClassName dataLombot = ClassName.get("lombok", "Data");
        ClassName table = ClassName.get("org.springframework.data.relational.core.mapping", "Table");



        TypeSpec.Builder classJava = TypeSpec.classBuilder(className);
        classJava.addSuperinterface(Serializable.class)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(table)
                        .addMember("value", "$S", "tbl_" + result.build().modelName + "s")
                        .build())
                .addAnnotation(dataLombot)
                .addAnnotation(lombokNoCons)
                .build();

        TypeSpec.Builder resultss = g.getField(classJava, result.build());
        if (resultss != null) {
            TypeSpec clas = resultss.build();

            JavaFile javaFile = JavaFile.builder(result.build().packageName + "." + result.build().modelName.toLowerCase(), clas)
                    .build();

            result.javaFile(javaFile);
            result.directoryExport(result.build().directory +DEFAULT);
            result.DataTablePackage(javaFile.packageName);
            result.DataTableClass(className);

            // result.JavaFile()
            fsave.filesSaveF(result.build());
            return result;
        }
        return null;


    }

    public TypeSpec.Builder getField(TypeSpec.Builder classJava, ConfigAction result){
        try {
            for (String key : result.model.keySet()) {
                System.out.println(key + ": " + result.model.get(key));
                ClassName className = ClassName.get(Class.forName(result.model.get(key)));
                if (key.equals("id")){

                    classJava.addField(FieldSpec.builder(className, key, Modifier.PRIVATE).addAnnotation(
                            AnnotationSpec.builder(id).build()
                    ).build());
                }else {
                    classJava.addField(FieldSpec.builder(className, key, Modifier.PRIVATE).addAnnotation(
                            AnnotationSpec.builder(column)
                                    .addMember("value", "$S", key)
                                    .build()
                    ).build());
                }

            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return classJava;
    }
}
