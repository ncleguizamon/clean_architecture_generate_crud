package co.ncleguizamon.model;



import com.squareup.javapoet.JavaFile;
import lombok.Builder;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;



@Builder
@ToString
public class ConfigAction {

    private final String action;
    public final String modelName;
    public  String idName;
    public  String idClass;
    public final String packageName;
    public final  Map<String  ,String> model;
    public final String modelPackage;
    public final String modelClass;

    public final String intfModelPackage;
    public final String intfModelClass;

    public final String DataTablePackage;
    public final String DataTableClass;

    public final String repositoryJpaPackage;
    public final String repositoryJpaClass;

    public final String directory;
    public final String directoryExport;
    public final JavaFile javaFile;



}
