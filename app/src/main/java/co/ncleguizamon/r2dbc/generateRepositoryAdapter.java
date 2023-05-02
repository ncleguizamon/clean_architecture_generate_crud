package co.ncleguizamon.r2dbc;

import co.ncleguizamon.filesGenerate;
import co.ncleguizamon.model.ConfigAction;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

public class generateRepositoryAdapter {
    private String DEFAULT="/infrastructure/driven-adapters/r2dbc-postgresql/src/main/java";
    public ConfigAction.ConfigActionBuilder generateRepositoryAdapter(ConfigAction.ConfigActionBuilder result) {
        filesGenerate fsave = new filesGenerate();
        ClassName interG = ClassName.get(result.build().intfModelPackage, result.build().intfModelClass);
        ClassName lombokCons = ClassName.get("lombok", "RequiredArgsConstructor");
        ClassName springfrService = ClassName.get("org.springframework.stereotype", "Service");
        String className= result.build().modelName +"RepositoryAdapter";

        TypeSpec classJava = TypeSpec.classBuilder(className)
                .addSuperinterface(interG)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(springfrService)
                .addAnnotation(lombokCons)
                .build();

        JavaFile javaFile = JavaFile.builder( result.build().packageName +"."+result.build().modelName.toLowerCase(), classJava)
                .build();


        result.javaFile(javaFile);
        result.directoryExport(result.build().directory +DEFAULT);
        // result.JavaFile()
        fsave.filesSaveF(result.build());
        return result;
    }
}
