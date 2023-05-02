package co.ncleguizamon.r2dbc;

import co.ncleguizamon.filesGenerate;
import co.ncleguizamon.model.ConfigAction;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

public class generateMapper {

    private String DEFAULT="/infrastructure/driven-adapters/r2dbc-postgresql/src/main/java";
    public ConfigAction.ConfigActionBuilder generateMapper(ConfigAction.ConfigActionBuilder result) {

        filesGenerate fsave = new filesGenerate();

        ClassName mapper = ClassName.get("org.mapstruct", "Mapper");
        ClassName dataLombot = ClassName.get("lombok", "Data");
        ClassName modelExpect = ClassName.get(result.build().modelPackage, result.build().modelClass);
        ClassName data = ClassName.get(result.build().DataTablePackage, result.build().DataTableClass);

        MethodSpec toEntity = MethodSpec.methodBuilder("toEntity")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(modelExpect)
                .addParameter(data, "data")
                .build();
        MethodSpec toData = MethodSpec.methodBuilder("toData")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(data)
                .addParameter(modelExpect, "data")
                .build();

        String className = result.build().modelName + "Mapper";

        TypeSpec classJava = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(mapper)
                        .addMember("componentModel", "$S", "spring")
                        .build())
                .addMethod(toEntity)
                .addMethod(toData)
                .build();

        JavaFile javaFile = JavaFile.builder(result.build().packageName + "." + result.build().modelName.toLowerCase(), classJava)
                .build();

        result.javaFile(javaFile);
        result.directoryExport(result.build().directory +DEFAULT);
        fsave.filesSaveF(result.build());
        return result;
    }
}
