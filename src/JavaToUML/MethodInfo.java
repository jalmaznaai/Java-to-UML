package JavaToUML;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
// This class establishes how the info for methods are stored.
public class MethodInfo
{
    // Below are the essential variables needed to be stored as part of a method
    private String methodName;
    private String returnType;
    private boolean isStatic;
    private boolean isAbstract;
    private String accessLevel;
    // Of special note is that our parameters are already a separate data type, meaning here they
    // will be stored in an arraylist.
    private ArrayList<Parameter> parameters;
    // And below are the constructors needed to form a method's information storage.
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
        // Note here the creation of the arraylist for the parameters.
        this.parameters = new ArrayList<Parameter>();
        // and this loop that obtains all of the parameters for the method.
        for(int i = 0; i < parameters.size(); i++)
        {
            this.parameters.add(new Parameter(parameters.get(i).getLeft(), parameters.get(i).getRight()));
        }
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getReturnType()
    {
        return returnType;
    }

    public void setReturnType(String returnType)
    {
        this.returnType = returnType;
    }

    public boolean isStatic()
    {
        return isStatic;
    }

    public void setStatic(boolean aStatic)
    {
        isStatic = aStatic;
    }

    public boolean isAbstract()
    {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract)
    {
        isAbstract = anAbstract;
    }

    public String getAccessLevel()
    {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel)
    {
        this.accessLevel = accessLevel;
    }

    public ArrayList<Parameter> getParameters()
    {
        return parameters;
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
