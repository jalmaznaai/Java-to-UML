package JavaToUML;

import java.io.File;
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
}

