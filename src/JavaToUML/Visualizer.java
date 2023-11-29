package JavaToUML;

import net.sourceforge.plantuml.SourceFileReader;
import net.sourceforge.plantuml.GeneratedImage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// This class represents our visualizer class, which generates our UML diagram by converting
// Javaparser's output to plantuml's syntax.
public class Visualizer
{
    // We first establish a filename variable for our output later.
    private static String fileName;

    public static String getFileName()
    {
        return fileName;
    }
    // In this function, we parse the access level of a variable or method to PlantUML
    public static char parseAccessLevel(String al)
    {
        return switch (al)
        {
            case "private" -> '-';
            case "protected" -> '#';
            case "" -> '~';
            case "public" -> '+';
            default -> '!';
        };
    }
    // This function helps parse class types to PlantUML
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
    // This function parses static methods
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
    // This function parses abstract methods
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
    // This function parses class relations to PlantUML, keeping in mind it's arrow syntax.
    public static String parseRelation(String relation)
    {
        return switch (relation)
        {
            case "Implements" -> " -[dashed]-|> ";
            case "Extends" -> " --|> ";
            case "Is Associated By" -> " <-- ";
            default -> "";
        };
    }
//    This method handles relations of classes
    public static void addAssociations(ArrayList<Relation> relations, ArrayList<String> classes, String targetClass)
    {
        for (int i = 0; i < classes.size(); i++)
        {
            relations.add(new Relation(targetClass, classes.get(i), "Is Associated By"));
        }
    }

    // This is the main method that creates our image specifically
    public static void makeImage(HashMap<String, ClassInfo> classes) throws IOException
    {
        // For our file name,
        // we figured the best method would be to name files on the current timestamp
        // so that the user knows exactly what file
        // to check based on when they ran the program.
        // This also
        // should prevent any duplicate files
        // unless the user is messing with their system clock or something
        // strange like that.
        LocalDateTime date = LocalDateTime.now();

        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();


        fileName = String.format("%02d_%02d_%02d_%02d_%02d", month, day, hour, minute, second);
        // We also place the files into an output folder for further clarity,
        // and because nobody likes files in the
        // program root.
        File directory = new File("Output");
        if(!directory.exists())
        {
            directory.mkdir();
        }
        // For the plantUML module to function, we also need to create a .puml file
        // Theoretically most users can just ignore this,
        // but if someone wanted to save that
        // for future purposes, they also get that file as a bonus.
        File plantFile = new File("Output/" + fileName + ".puml");
        FileWriter tempWriter = new FileWriter(plantFile);
        BufferedWriter writer = new BufferedWriter(tempWriter);
        // We then start our file...
        writer.write("@startuml");
        writer.newLine();
        // Here we are first going to grab all of the classes and their relations
        String[] classNames = classes.keySet().toArray(new String[0]);
        ArrayList<Relation> relations = new ArrayList<>();
        // Then for every class
        for(int i = 0; i < classNames.length; i++)
        {
            String className = classNames[i];
            ClassInfo currentClass = classes.get(className);
            // We get relations...
            relations.addAll(currentClass.createRelationList());
            // Associations...
            addAssociations(relations, currentClass.getAssociations(), className);
            // Access level, abstraction/interface status, and the name itself...
            writer.write(parseAccessLevel(currentClass.getAccessLevel()));
            writer.write(parseClassType(currentClass.isAbstract(), currentClass.isInterface()) + " ");
            writer.write(className + " {");
            writer.newLine();
            // Then we grab the variables...
            ArrayList<Variable> classVariables = currentClass.getClassVariables();

            for(int i2 = 0; i2 < classVariables.size(); i2++)
            {
                // Including name, access level, static status and type
                Variable currentVariable = classVariables.get(i2);
                writer.write("\t");
                writer.write(parseAccessLevel(currentVariable.getAccessLevel()));
                writer.write(parseIsStatic(currentVariable.isStatic()));
                writer.write(currentVariable.getType() + " ");
                writer.write(currentVariable.getVarName());
                writer.newLine();
            }
            // For methods...
            ArrayList<MethodInfo> classMethods = currentClass.getMethods();
            for(int i2 = 0; i2 < classMethods.size(); i2++)
            {
                // We follow a similar pattern to grab info...
                MethodInfo currentMethod = classMethods.get(i2);
                writer.write("\t");
                writer.write(parseAccessLevel(currentMethod.getAccessLevel()));
                writer.write(parseIsStatic(currentMethod.isStatic()));
                writer.write(parseIsAbstract(currentMethod.isAbstract()));
                writer.write(currentMethod.getReturnType() + " ");
                writer.write(currentMethod.getMethodName() + "(");
                // As well as grab the info for the parameters
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

        // Lastly, we grab the relation info
        for (int i = 0; i < relations.size(); i++)
        {
            // grabbing relation type, and the classes involved in the relation.
            Relation currentRelation = relations.get(i);
            writer.write(currentRelation.getClass1());
            writer.write(parseRelation(currentRelation.getRelationType()));
            writer.write(currentRelation.getClass2());
            writer.newLine();
        }



        // We then finish writing the file...
        writer.write("@enduml");
        writer.close();
        tempWriter.close();

        // and use PlantUML to generate our image...
        SourceFileReader reader = new SourceFileReader(plantFile);
        List<GeneratedImage> list = reader.getGeneratedImages();
        // and save it as a png.
        File png = list.get(0).getPngFile();
    }

//    public static void main(String[] args) throws IOException
//    {
////        makeImage(new HashMap<String, ClassInfo>());
//    }
}
