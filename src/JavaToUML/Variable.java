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
}

