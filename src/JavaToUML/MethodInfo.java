package JavaToUML;

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
}
