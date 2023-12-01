package JavaToUML;
// This class helps establish the use of final variables in our code.
public class FinalVariable extends Variable
{
    // An important feature of the final variable is its final value.
    private String finalValue;


    // as an extension of the variable type,
    // we just super the parameters and then add on our final value.
    public <E> FinalVariable(String varName, String type, String accessLevel, boolean isStatic, boolean isFinal, Object finalValue)
    {
        super(varName, type, accessLevel, isStatic, isFinal);
        this.finalValue = finalValue.toString();
    }
}
