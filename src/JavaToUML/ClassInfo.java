package JavaToUML;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassInfo
{
    private String className;
    private boolean isAbstract;
    private boolean isInterface;
    private String accessLevel;
    private ArrayList<Variable> classVariables;
    private HashMap<String, String> classRelations;
    private ArrayList<MethodInfo> methods;

    public ClassInfo(String className, boolean isAbstract, boolean isInterface, String accessLevel, ArrayList<Variable>
            classVariables, HashMap<String, String> classRelations, ArrayList<MethodInfo> methods)
    {
        this.className = className;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.accessLevel = accessLevel;
        this.classVariables = classVariables;
        this.classRelations = classRelations;
        this.methods = methods;
    }

    public ClassInfo(String className, boolean isAbstract, boolean isInterface, String accessLevel)
    {
        this.className = className;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.accessLevel = accessLevel;
        this.classVariables = new ArrayList<Variable>();
        this.classRelations = new HashMap<String, String>();
        this.methods = new ArrayList<MethodInfo>();
    }

    public ClassInfo()
    {
        this.className = "";
        this.isAbstract = false;
        this.isInterface = false;
        this.accessLevel = "";
        this.classVariables = new ArrayList<Variable>();
        this.classRelations = new HashMap<String, String>();
        this.methods = new ArrayList<MethodInfo>();
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public boolean isAbstract()
    {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract)
    {
        isAbstract = anAbstract;
    }

    public boolean isInterface()
    {
        return isInterface;
    }

    public void setInterface(boolean anInterface)
    {
        isInterface = anInterface;
    }

    public String getAccessLevel()
    {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel)
    {
        this.accessLevel = accessLevel;
    }

    public ArrayList<Variable> getClassVariables()
    {
        return classVariables;
    }

    public void addClassVariable(String varName, String type, String accessLevel, boolean isStatic, boolean isFinal)
    {
        this.classVariables.add(new Variable(varName, type, accessLevel, isStatic, isFinal));
    }

    public void addFinalClassVariable(String varName, String type, String accessLevel, boolean isStatic,
                                      boolean isFinal, Object finalValue)
    {
        this.classVariables.add(new FinalVariable(varName, type, accessLevel, isStatic, isFinal, finalValue));
    }

    public HashMap<String, String> getClassRelations()
    {
        return classRelations;
    }

    public ArrayList<MethodInfo> getMethods()
    {
        return methods;
    }

    public void addClassMethod(String methodName, String returnType, boolean isStatic, boolean isAbstract,
                               String accessLevel, ArrayList<Pair<String, String>> parameters)
    {
        this.methods.add(new MethodInfo(methodName, returnType, isStatic, isAbstract, accessLevel, parameters, true));
    }
}