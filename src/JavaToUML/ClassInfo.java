package JavaToUML;

import java.util.ArrayList;

public class ClassInfo
{
    private String className;
    private boolean isAbstract;
    private boolean isInterface;
    private String accessLevel;
    private ArrayList<Variable> classVariables;
    private ArrayList<Relation> classRelations;
    private ArrayList<MethodInfo> methods;

    public ClassInfo(String className, boolean isAbstract, boolean isInterface, String accessLevel, ArrayList<Variable>
            classVariables, ArrayList<Relation> classRelations, ArrayList<MethodInfo> methods)
    {
        this.className = className;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.accessLevel = accessLevel;
        this.classVariables = classVariables;
        this.classRelations = classRelations;
        this.methods = methods;
    }

    public ClassInfo()
    {
        this.className = "";
        this.isAbstract = false;
        this.isInterface = false;
        this.accessLevel = "";
        this.classVariables = new ArrayList<Variable>();
        this.classRelations = new ArrayList<Relation>();
        this.methods = new ArrayList<MethodInfo>();
    }
}