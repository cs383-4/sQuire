package squire;

import com.avaje.ebeaninternal.server.lib.util.Str;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MattDaniel on 4/10/16.
 */
public class FileList
{
    private ArrayList<String> fileList = new ArrayList<String>();
    private String projectPath;
    private String projectName;

    public FileList()
    {
        ArrayList<String> fileList = new ArrayList<String>();
    }

    public void addFile(String f)
    {
        fileList.add(f);
    }

    public void copy(FileList fl)
    {
        fileList = fl.getFileList();
        projectPath = fl.getProjectPath();
        projectName = fl.getProjectName();
    }

    public void setProjectPath(String s)
    {
        projectPath = s;
    }

    public String getProjectPath()
    {
        return projectPath;
    }

    public void setProjectName(String s)
    {
        projectPath = s;
    }

    public String getProjectName()
    {
        return projectPath;
    }

    public void print()
    {
        System.out.println(fileList);
    }

    public ArrayList<String> getFileList()
    {
        return fileList;
    }

    public String getMatchingFile(String s)
    {
        String file = "";
        String t;
        for (String fileName: this.getFileList())
        {
           t = fileName.substring(fileName.lastIndexOf("/")+1);

            if (s.equals(t))
            {
           //     System.out.println(fileName.substring(file.lastIndexOf("/")+1));
                file = fileName;
            }
        }
     //   System.out.println(file);
        return file;
    }

}
