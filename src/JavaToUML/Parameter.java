package JavaToUML;
// This class has our relevant information for the storage of parameters.
public class Parameter
{
    // For a UML diagram, we only really need the parameter's name and data type.
    private String  dataType;
    private String paramName;
    // And this method sets them appropriately.
    public Parameter(String dataType, String paramName)
    {
        this.dataType = dataType;
        this.paramName = paramName;
    }
}