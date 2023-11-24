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

    public boolean hasCorrectSyntax()
    {
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("C:\\Users\\aaaaa\\Documents\\Git\\School\\Software Engineering 1\\Project\\" +
                "Java To UML\\src\\SampleClasses\\Sample.java");

        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> cu = javaParser.parse(file);

        System.out.println(cu.isSuccessful());
    }
}

