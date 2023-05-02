/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package co.ncleguizamon;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException {
        filesGenerate fsave= new filesGenerate();

        ClassName list = ClassName.get("java.util", "List");
        ClassName dataLombot = ClassName.get("lombok", "Data");
        ClassName builderLombo = ClassName.get("lombok", "Builder");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        ClassName interG= ClassName.get("com.example.helloworld.test", "IHelloWorld");

        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!dsdf")
                .build();

        TypeSpec classJava = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(interG , "test" , Modifier.PRIVATE,Modifier.FINAL  ) // se agrega los imports
                .addAnnotation(dataLombot)
                    .addAnnotation(AnnotationSpec.builder(builderLombo).addMember("toBuilder" ,  "$L", true).build())
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", classJava)
                .build();

        fsave.filesSave(javaFile);
    }
}
