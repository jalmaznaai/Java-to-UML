package JavaToUML;
// Welcome to the main file for our Java to UML converter.
// This file encompasses the logic that represents us taking in a file, ensuring that it is a valid
// syntax-correct java file, and then using javaparser to parse the code into AST.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
// Obviously, we are importing javaparser to be able to use its functionality.
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ParseResult;
// We also import apache commons' IO and general packages in order to get filename utilities and
// to be able to use a tuple pair data structure.
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.Pair;

public class JavaToUML
{
    // This boolean checks to ensure the user input a file that exists (i.e., not a directory or incorrect filepath)
    public static boolean fileExists(File file)
    {
        return (file.exists() && file.isFile());
    }
    // This boolean performs a check so that only .java files are accepted.
    public static boolean isJavaFile(File file)
    {
        return FilenameUtils.getExtension(file.getName()).equals("java");
    }
    // This boolean makes sure that our inputted java file is syntax correct.
    // It does so by taking the results parameter of javaparser's output.
    public static boolean hasCorrectSyntax(ParseResult<CompilationUnit> result)
    {
        return result.isSuccessful();
    }
    // This function allows for associations between classes to be found. It accepts a javaparser compilationunit
    // and a target class name to search for associations.
    private static ArrayList<String> getAssociations(CompilationUnit cu, String targetClassName) {
        ArrayList<String> result = new ArrayList<>();

        // Here, we set up a lambda expression to represent searching through all classes...
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterface -> {
            // Then iterates through the class' fields
            classOrInterface.findAll(FieldDeclaration.class).forEach(field -> {
                // Then if we do find the target class name within the associations...
                if (field.getElementType() instanceof ClassOrInterfaceType &&
                        ((ClassOrInterfaceType) field.getElementType()).getNameAsString().equals(targetClassName))
                {
                    // We add the class where the association was found to our associated classes.
                    result.add(classOrInterface.getNameAsString());
                }
            });
        });

        return result;
    }

    // Get info represents the main way that we grab the information related to classes such as name, abstract status, etc.
    // We store this info in a hashmap in order to be able to retrieve it easily later.
    public static HashMap<String, ClassInfo> getInfo(CompilationUnit cu)
    {
        HashMap<String, ClassInfo> classes = new HashMap<String, ClassInfo>();

        // For every class declaration in our javaparser AST...
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterface -> {
            // We obtain the relevent information for our class, such as name, abstract status, etc.
            String className = classOrInterface.getNameAsString();
            boolean isAbstract = classOrInterface.isAbstract();
            boolean isInterface = classOrInterface.isInterface();
            String accessLevel = classOrInterface.getAccessSpecifier().asString();
            // And save it into our hashmap for retrieval.
            classes.put(className, new ClassInfo(className, isAbstract, isInterface, accessLevel));

            classes.get(className).addAssociations(getAssociations(cu, className));

            // These two lambda expressions handle cases where extended or implemented classes need to be stored.
            classOrInterface.getExtendedTypes().forEach(type -> {
                classes.get(className).getClassRelations().put(type.getNameAsString(), "Extends");
            });

            classOrInterface.getImplementedTypes().forEach(type -> {
                classes.get(className).getClassRelations().put(type.getNameAsString(), "Implements");
            });

            // This lambda expression then grabs all of the information about the variables in a class...
            classOrInterface.findAll(FieldDeclaration.class).forEach(field -> {
                // Extract variable information
                String variableName = field.getVariable(0).getNameAsString();
                String variableType = field.getVariable(0).getType().asString();
                String variableAccessLevel = field.getAccessSpecifier().asString();
                // Including static and final statuses...
                boolean isStatic = field.getModifiers().contains(Modifier.staticModifier());
                boolean isFinal = field.getModifiers().contains(Modifier.finalModifier());
                // and a field value if a variable is final..
                String fieldValue = (isFinal) ? field.getVariable(0).getInitializer().get().toString() : null;

                // And then assigns those variables onto the class.
                if(isFinal)
                {
                    classes.get(className).addFinalClassVariable(variableName, variableType, variableAccessLevel,
                            isStatic, isFinal, fieldValue);
                }
                else
                {
                    classes.get(className).addClassVariable(variableName, variableType, variableAccessLevel,
                            isStatic, isFinal);
                }

                // Maybe process this information (association, composition, aggregation, etc.)
            });

            // This expression extracts methods from our classes
            classOrInterface.findAll(MethodDeclaration.class).forEach(method -> {
                // Extract method information
                String methodName = method.getNameAsString();
                String returnType = method.getTypeAsString();
                // Including static and abstract statuses
                boolean isStatic = method.isStatic();
                boolean isAbstractMethod = method.isAbstract();
                String methodAccessLevel = method.getAccessSpecifier().asString();
                ArrayList<Pair<String, String>> parameters = new ArrayList<Pair<String, String>>();

                // And then grabs the parameters of the method...
                method.getParameters().forEach(parameter -> {
                    String parameterType = parameter.getType().asString();
                    String parameterName = parameter.getNameAsString();
                    parameters.add(Pair.of(parameterType, parameterName));
                });
                // And assigns them to the class.
                classes.get(className).addClassMethod(methodName, returnType, isStatic, isAbstractMethod,
                        methodAccessLevel, parameters);

            });
        });


        return classes;
    }

    // Our main method addresses the running tasks.
    public static void main(String[] args) throws IOException
    {
        /* TODO: Since full main functionality is not yet complete, finish main comments when functionality is complete */
        boolean fileinput = true;
        File file;
        Scanner input = new Scanner(System.in);
        JavaParser javaParser = new JavaParser();
        while (fileinput) {
            System.out.println("Welcome, please input the absolute file path of the java file you wish to convert to a UML diagram:");
            String filepath = input.nextLine();
            file = new File(filepath);
            if (!fileExists(file)){
                System.out.println("Oops! The file you entered doesn't exist. Please try again.");
                System.out.println();
            }
            else {
                if (!isJavaFile(file)){
                    System.out.println("Oops, the file entered is not a java file. Please try again.");
                }
                else {
                    ParseResult<CompilationUnit> result = javaParser.parse(file);
                    if (!hasCorrectSyntax(result)){
                        System.out.println("Oops, the file entered has syntax errors. Please fix them and try again.");
                    }
                    else {
                        CompilationUnit cu = result.getResult().get();
                        HashMap<String, ClassInfo> classes = JavaToUML.getInfo(cu);
                        int x = 0;
                        Visualizer.makeImage(classes);
                        fileinput = false;
                    }
                }
            }
        }






    }
}

