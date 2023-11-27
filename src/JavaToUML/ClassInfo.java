package JavaToUML;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
// This class handles the class info that is set and stored.
public class ClassInfo
{
    // the variables here are fairly self-explanatory.
    private String className;
    private boolean isAbstract;
    private boolean isInterface;
    private String accessLevel;
    private ArrayList<Variable> classVariables;
    private HashMap<String, String> classRelations;
    private ArrayList<String> associations;
    private ArrayList<MethodInfo> methods;
    // This method acts as a baseline for a class with info to be added.
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
        this.associations = new ArrayList<String>();
    }
    // This handles our main cases, as we want to be able to have the structures ready to insert data into.
    public ClassInfo(String className, boolean isAbstract, boolean isInterface, String accessLevel)
    {
        this.className = className;
        this.isAbstract = isAbstract;
        this.isInterface = isInterface;
        this.accessLevel = accessLevel;
        this.classVariables = new ArrayList<Variable>();
        this.classRelations = new HashMap<String, String>();
        this.methods = new ArrayList<MethodInfo>();
        this.associations = new ArrayList<String>();
    }
    // This is a default constructor.
    public ClassInfo()
    {
        this.className = "";
        this.isAbstract = false;
        this.isInterface = false;
        this.accessLevel = "";
        this.classVariables = new ArrayList<Variable>();
        this.classRelations = new HashMap<String, String>();
        this.methods = new ArrayList<MethodInfo>();
        this.associations = new ArrayList<String>();
    }
    // Below are pretty much standard setters and getters for all the variables above.
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

    public ArrayList<Relation> createRelationList()
    {
        ArrayList<Relation> relationList = new ArrayList<Relation>();

        String[] keys = classRelations.keySet().toArray(new String[0]);

        for (int i = 0; i < keys.length; i++)
        {
            relationList.add(new Relation(this.className, keys[i], this.classRelations.get(keys[i])));
        }

        return relationList;
    }

    public ArrayList<String> getAssociations()
    {
        return this.associations;
    }
    public void addAssociations(ArrayList<String> classes)
    {
        this.associations.addAll(classes);
    }
}