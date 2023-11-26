package JavaToUML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ParseResult;

import org.apache.commons.io.FilenameUtils;

public class JavaToUML
{
    public static boolean fileExists(File file)
    {
        return (file.exists() && file.isFile());
    }

    public static boolean isJavaFile(File file)
    {
        return FilenameUtils.getExtension(file.getName()).equals("java");
    }

    public boolean hasCorrectSyntax(ParseResult<CompilationUnit> result)
    {
        return result.isSuccessful();
    }

    private static ArrayList<String> getAssociations(CompilationUnit cu, String targetClassName) {
        ArrayList<String> result = new ArrayList<>();


        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterface -> {
            // Iterate through fields in the class/interface
            classOrInterface.findAll(FieldDeclaration.class).forEach(field -> {
                // Check if the field type is the target class
                if (field.getElementType() instanceof ClassOrInterfaceType &&
                        ((ClassOrInterfaceType) field.getElementType()).getNameAsString().equals(targetClassName))
                {
                    result.add(classOrInterface.getNameAsString());
                }
            });
        });

        return result;
    }

    // TODO: 11/25/2023 make a getInfo method to store info in a ClassInfo Object
    public static HashMap<String, ClassInfo> getInfo(CompilationUnit cu)
    {
        HashMap<String, ClassInfo> classes = new HashMap<String, ClassInfo>();
        ArrayList<Class<ClassOrInterfaceDeclaration>> classesToParse = (ArrayList) cu.findAll(ClassOrInterfaceDeclaration.class);

        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterface -> {
            // Extract class information
            String className = classOrInterface.getNameAsString();
            boolean isAbstract = classOrInterface.isAbstract();
            boolean isInterface = classOrInterface.isInterface();
            String accessLevel = classOrInterface.getAccessSpecifier().asString();

            classes.put(className, new ClassInfo(className, isAbstract, isInterface, accessLevel));



            // Extract superclasses and interfaces
            classOrInterface.getExtendedTypes().forEach(type -> {
                classes.get(className).getClassRelations().put(type.getNameAsString(), "Extends");
            });

            classOrInterface.getImplementedTypes().forEach(type -> {
                classes.get(className).getClassRelations().put(type.getNameAsString(), "Implements");
            });

            // Extract class variables
            classOrInterface.findAll(FieldDeclaration.class).forEach(field -> {
                // Extract variable information
                String variableName = field.getVariable(0).getNameAsString();
                String variableType = field.getVariable(0).getType().asString();
                String variableAccessLevel = field.getAccessSpecifier().asString();
                boolean isStatic = field.getModifiers().contains(Modifier.staticModifier());
                boolean isFinal = field.getModifiers().contains(Modifier.finalModifier());
                String fieldValue = (isFinal) ? field.getVariable(0).getInitializer().get().toString() : null;

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

            // Extract methods
            classOrInterface.findAll(MethodDeclaration.class).forEach(method -> {
                // Extract method information
                String methodName = method.getNameAsString();
                String returnType = method.getTypeAsString();
                boolean isStatic = method.isStatic();
                boolean isAbstractMethod = method.isAbstract();
                String methodAccessLevel = method.getAccessSpecifier().asString();

                // Extract parameters
                method.getParameters().forEach(parameter -> {
                    String parameterName = parameter.getNameAsString();
                    Type parameterType = parameter.getType();
                    // Process parameter information
                });

                // Process this information (association, composition, aggregation, etc.)
            });
        });


        return classes;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("C:\\Users\\aaaaa\\Documents\\Git\\School\\Software Engineering 1\\Project\\Java To UML\\Sample.java");

        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> result = javaParser.parse(file);


        CompilationUnit cu = result.getResult().get();

        HashMap<String, ClassInfo> classes = JavaToUML.getInfo(cu);




    }
}

