package JavaToUML;

import net.sourceforge.plantuml.SourceFileReader;
import net.sourceforge.plantuml.GeneratedImage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
// TODO: Add comments when complete.
public class Visualizer
{
    public static char parseAccessLevel(String al)
    {
        switch(al)
        {
            case "private":
                return '-';
            case "protected":
                return '#';
            case "":
                return '~';
            case "public":
                return '+';
            default:
                return '!';
        }
    }

    public static String parseClassType(boolean isAbstract, boolean isInterface)
    {
        if(!isAbstract && !isInterface)
        {
            return "class";
        }
        else if(isAbstract && !isInterface)
        {
            return "abstract class";
        }
        else if(!isAbstract && isInterface)
        {
            return "interface";
        }
        else
        {
            return "abstract interface";
        }
    }

    public static String parseIsStatic(boolean isStatic)
    {
        if(isStatic)
        {
            return "{static} ";
        }
        else
        {
            return "";
        }
    }

    public static String parseIsAbstract(boolean isAbstract)
    {
        if(isAbstract)
        {
            return "{abstract} ";
        }
        else
        {
            return "";
        }
    }
    public static void makeImage(HashMap<String, ClassInfo> classes) throws IOException
    {
        LocalDateTime date = LocalDateTime.now();

        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();


        String fileName = String.format("%02d_%02d_%02d_%02d_%02d", month, day, hour, minute, second);

        File directory = new File("Output");
        if(!directory.exists())
        {
            directory.mkdir();
        }

        File plantFile = new File("Output/" + fileName + ".puml");
        FileWriter tempWriter = new FileWriter(plantFile);
        BufferedWriter writer = new BufferedWriter(tempWriter);

        writer.write("@startuml");
        writer.newLine();

        String[] classNames = classes.keySet().toArray(new String[0]);
        for(int i = 0; i < classNames.length; i++)
        {
            String className = classNames[i];
            ClassInfo currentClass = classes.get(className);

            writer.write(parseAccessLevel(currentClass.getAccessLevel()));
            writer.write(parseClassType(currentClass.isAbstract(), currentClass.isInterface()) + " ");
            writer.write(className + " {");
            writer.newLine();

            ArrayList<Variable> classVariables = currentClass.getClassVariables();

            for(int i2 = 0; i2 < classVariables.size(); i2++)
            {
                Variable currentVariable = classVariables.get(i2);
                writer.write("\t");
                writer.write(parseAccessLevel(currentVariable.getAccessLevel()));
                writer.write(parseIsStatic(currentVariable.isStatic()));
                writer.write(currentVariable.getType() + " ");
                writer.write(currentVariable.getVarName());
                writer.newLine();
            }

            ArrayList<MethodInfo> classMethods = currentClass.getMethods();
            for(int i2 = 0; i2 < classMethods.size(); i2++)
            {
                MethodInfo currentMethod = classMethods.get(i2);
                writer.write("\t");
                writer.write(parseAccessLevel(currentMethod.getAccessLevel()));
                writer.write(parseIsStatic(currentMethod.isStatic()));
                writer.write(parseIsAbstract(currentMethod.isAbstract()));
                writer.write(currentMethod.getReturnType() + " ");
                writer.write(currentMethod.getMethodName() + "(");

                ArrayList<Parameter> methodParameters = currentMethod.getParameters();
                for (int i3 = 0; i3 < methodParameters.size(); i3++)
                {
                    Parameter currentParam = methodParameters.get(i3);
                    writer.write(currentParam.getDataType() + " " + currentParam.getParamName());

                    if(i3 + 1 < methodParameters.size())
                    {
                        writer.write(", ");
                    }
                }
                writer.write(")");

                writer.newLine();
            }
            writer.write("}");

            writer.newLine();
            writer.newLine();
        }






        writer.write("@enduml");
        writer.close();
        tempWriter.close();
    }

    public static void main(String[] args) throws IOException
    {
        makeImage(new HashMap<String, ClassInfo>());
    }
}
