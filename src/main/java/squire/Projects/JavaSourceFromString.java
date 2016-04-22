package squire.Projects;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by Domn Werner on 4/19/2016.
 */

/**
 * A file object used to represent source coming from a string.
 * This example follows the JavaDocs for the JavaCompiler API.
 */
public class JavaSourceFromString extends SimpleJavaFileObject
{
    private String fileName;
    private String filePath;
    private  String sourceCode;

    /**
     * The constructor of a new code file.
     * @param name The file name.
     * @param path The fully qualified path to the file.
     * @param code The source code text inside the file.
     */
    public JavaSourceFromString(String name, String path, String code)
    {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        fileName = name;
        filePath = path;
        sourceCode = code;
    }

    /**
     * Constructs a new Java source file with default code.
     * @param name The file name.
     * @param path The fully qualified path to the file.
     */
    public JavaSourceFromString(String name, String path)
    {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
    }

    /**
     * The function to retrieve the source code of this file.
     * @param ignoreEncodingErrors Whether to ignore any encoding errors in the code.
     * @return A CharSequence of the source code in this file.
     */
    @Override public CharSequence getCharContent(boolean ignoreEncodingErrors)
    {
        return sourceCode;
    }

    public void setFileName(String name) { fileName = name; }
    public void setFilePath(String path) { filePath = path; }
    public void setFilePath(CharSequence code) { sourceCode = code.toString(); }
    public String getFileName() { return fileName; }
    public String getFilePath() { return filePath; }
}
