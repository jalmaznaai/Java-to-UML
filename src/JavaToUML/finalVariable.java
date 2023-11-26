package JavaToUML;

public class finalVariable extends Variable
{
    private String finalValue;


    public <E> finalVariable(String varName, String type, String accessLevel, boolean isStatic, boolean isFinal, Object finalValue)
    {
        super(varName, type, accessLevel, isStatic, isFinal);
        this.finalValue = finalValue.toString();
    }
}
