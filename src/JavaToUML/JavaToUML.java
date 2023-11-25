package JavaToUML;

import java.io.File;
import java.io.FileNotFoundException;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
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
        return true;
    }

    // TODO: 11/25/2023 make a getInfo method to store info in a ClassInfo Object

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("C:\\Users\\aaaaa\\Documents\\Git\\School\\Software Engineering 1\\Project\\Java To UML\\Sample.java");

        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> result = javaParser.parse(file);

        System.out.println(result.isSuccessful());
        CompilationUnit cu = result.getResult().get();




    }
}

