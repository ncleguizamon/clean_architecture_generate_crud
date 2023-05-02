package co.ncleguizamon;

import co.ncleguizamon.model.ConfigAction;
import co.ncleguizamon.r2dbc.generateDataRepository;
import co.ncleguizamon.r2dbc.generateDataTable;
import co.ncleguizamon.r2dbc.generateMapper;
import co.ncleguizamon.r2dbc.generateRepositoryAdapter;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

public class generateModel {


    private String DEFAULT_MODEL="/domain/model/src/main/java";
    public ConfigAction.ConfigActionBuilder generateModel() {
        filesGenerate fsave = new filesGenerate();
        configPro cnf = new configPro();
         String className= cnf.getConFig().build().modelName +"s";

        generateModel g = new generateModel();

        TypeSpec.Builder classJava = TypeSpec.classBuilder(className);
        ClassName dataLombot = ClassName.get("lombok", "Data");
        ClassName builderLombo = ClassName.get("lombok", "Builder");
        classJava.addModifiers(Modifier.PUBLIC, Modifier.PUBLIC)
                .addAnnotation(dataLombot)
                .addAnnotation(AnnotationSpec.builder(builderLombo).addMember("toBuilder", "$L", true).build());
        ConfigAction.ConfigActionBuilder result = cnf.getConFig();

        TypeSpec.Builder resultss = g.getFilsd(classJava, result.build());
        if (resultss != null) {
            TypeSpec clas = resultss.build();
            JavaFile javaFile = JavaFile.builder(result.build().packageName +".model."+result.build().modelName.toLowerCase(), clas)
                    .build();
            result.modelPackage(javaFile.packageName);
            result.modelClass(className);
            result.javaFile(javaFile);
            result.directoryExport(result.build().directory +DEFAULT_MODEL);
            fsave.filesSaveF(result.build());
        }else {
            System.out.println("Error tipo de datos no valido");
            return null;
        }
        return result;
    }


    public TypeSpec.Builder getFilsd(TypeSpec.Builder classJava ,ConfigAction result)  {
        try {
        for (String key : result.model.keySet()) {
            System.out.println(key + ": " + result.model.get(key));

            //ClassName list = ClassName.get("java.util", "List");
         //   Class c=Class.forName("java.lang.String");
            ClassName className = ClassName.get( Class.forName(result.model.get(key)));
            classJava.addField( className, key, Modifier.PRIVATE);
        }
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        return classJava;
    }

    public static void main(String[] args) {
        generateModel a =new generateModel();
        ConfigAction.ConfigActionBuilder model = a.generateModel();
        if (model != null){
            InterfacesGenerate inter= new InterfacesGenerate();
            model= inter.createInterFaseservice(model);

            generateRepositoryAdapter genAdadter= new generateRepositoryAdapter();
            model=genAdadter.generateRepositoryAdapter(model);

            generateDataTable gtable= new generateDataTable();
            model = gtable.generateDataTable(model);


            generateDataRepository grepoDat= new generateDataRepository();
            try {
                model=grepoDat.generateDataRepository(model);
            }catch (Exception e){
                System.out.println(e);
            }
            generateMapper maper=new generateMapper();
            maper.generateMapper(model);




        }
    }

}
