package co.ncleguizamon;

import co.ncleguizamon.model.ConfigAction;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;


public class InterfacesGenerate {


    private String DEFAULT_MODEL_INT="/domain/model/src/main/java";
    public ConfigAction.ConfigActionBuilder createInterFaseservice(ConfigAction.ConfigActionBuilder result) {
        filesGenerate fsave= new filesGenerate();

        ClassName classModel= ClassName.get( result.build().modelPackage, result.build().modelClass);
        ClassName mono= ClassName.get("reactor.core.publisher", "Mono");

        TypeName mapofMono = ParameterizedTypeName.get(mono, classModel);

        MethodSpec create = MethodSpec.methodBuilder("create")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(mapofMono)
               // .addParameter(data, "data")
                .build();
        String className= result.build().modelName +"Repository";
        TypeSpec classJava = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(create)
/*
                .addField(FieldSpec.builder(mapofMono, "create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)

                        //.initializer("$S", "change")
                        .build())

 */
                .build();
        JavaFile javaFile = JavaFile.builder( result.build().packageName +".model."+result.build().modelName.toLowerCase() +".gateways" , classJava)
                .build();

        result.intfModelPackage(javaFile.packageName);
        result.intfModelClass(className);
        result.javaFile(javaFile);
        result.directoryExport(result.build().directory +DEFAULT_MODEL_INT);
        // result.JavaFile()
        fsave.filesSaveF(result.build());
        return result;

    }
}
