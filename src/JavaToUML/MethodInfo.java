package JavaToUML;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;

public class MethodInfo
{
    private String methodName;
    private String returnType;
    private boolean isStatic;
    private boolean isAbstract;
    private String accessLevel;
    private ArrayList<Parameter> parameters;

    public MethodInfo(String methodName, String returnType, boolean isStatic, boolean isAbstract, String accessLevel,
                      ArrayList<Parameter> parameters)
    {
        this.methodName = methodName;
        this.returnType = returnType;
        this.isStatic = isStatic;
        this.isAbstract = isAbstract;
        this.accessLevel = accessLevel;
        this.parameters = parameters;
    }

    public MethodInfo(String methodName, String returnType, boolean isStatic, boolean isAbstract, String accessLevel,
                      ArrayList<Pair<String, String>> parameters, boolean t)
    {
        this.methodName = methodName;
        this.returnType = returnType;
        this.isStatic = isStatic;
        this.isAbstract = isAbstract;
        this.accessLevel = accessLevel;
        this.parameters = new ArrayList<Parameter>();

        for(int i = 0; i < parameters.size(); i++)
        {
            this.parameters.add(new Parameter(parameters.get(i).getLeft(), parameters.get(i).getRight()));
        }
    }

//    public MethodInfo(String methodName, String returnType, boolean isStatic, boolean isAbstract, String accessLevel)
//    {
//        this.methodName = methodName;
//        this.returnType = returnType;
//        this.isStatic = isStatic;
//        this.isAbstract = isAbstract;
//        this.accessLevel = accessLevel;
//        this.parameters = new ArrayList<Parameter>();
//    }
//
//    public void addNewParameter(String dataType, String paramName)
//    {
//        this.parameters.add(new Parameter(dataType, paramName));
//    }
}
