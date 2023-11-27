package JavaToUML;
// This class handles the storage of variables and their relevant info.
public class Variable
{
    private String varName;
    private String type;
    private String accessLevel;
    private boolean isStatic;
    private boolean isFinal;

    public Variable(String varName, String type, String accessLevel, boolean isStatic, boolean isFinal) {
        this.varName = varName;
        this.type = type;
        this.accessLevel = accessLevel;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
    }

    public String getVarName()
    {
        return varName;
    }

    public void setVarName(String varName)
    {
        this.varName = varName;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAccessLevel()
    {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel)
    {
        this.accessLevel = accessLevel;
    }

    public boolean isStatic()
    {
        return isStatic;
    }

    public void setStatic(boolean aStatic)
    {
        isStatic = aStatic;
    }

    public boolean isFinal()
    {
        return isFinal;
    }

    public void setFinal(boolean aFinal)
    {
        isFinal = aFinal;
    }
}

