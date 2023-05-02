package co.ncleguizamon.r2dbc;

import co.ncleguizamon.filesGenerate;
import co.ncleguizamon.model.ConfigAction;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

public class generateDataRepository {
    private String DEFAULT="/infrastructure/driven-adapters/r2dbc-postgresql/src/main/java";
    public ConfigAction.ConfigActionBuilder generateDataRepository(ConfigAction.ConfigActionBuilder result) throws ClassNotFoundException {

        System.out.println("sda" + result.build().toString());

        String className = result.build().modelName + "DataRepository";
        filesGenerate fsave = new filesGenerate();

        ClassName repository = ClassName.get("org.springframework.stereotype", "Repository");
        ClassName data = ClassName.get(result.build().DataTablePackage, result.build().DataTableClass);
        TypeName  reactiveCrudRepository =ClassName.get("org.springframework.data.repository.reactive", "ReactiveCrudRepository");

        TypeName idDefault= result.build().idName == null?  ClassName.get(Long.class):
                ClassName.get(Class.forName(result.build().model.get(result.build().idName)));

        TypeName mapofMono = ParameterizedTypeName.get((ClassName) reactiveCrudRepository, data , idDefault  );

        TypeSpec classJava = TypeSpec.interfaceBuilder(className)
                .addSuperinterface(mapofMono)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(repository)
                .build();

        JavaFile javaFile = JavaFile.builder(result.build().packageName + "." + result.build().modelName.toLowerCase(), classJava)
                .build();

        result.javaFile(javaFile);
        result.directoryExport(result.build().directory +DEFAULT);
        result.repositoryJpaPackage(javaFile.packageName);
        result.repositoryJpaClass(className);
        // result.JavaFile()
        fsave.filesSaveF(result.build());
        return result;
    }
}
