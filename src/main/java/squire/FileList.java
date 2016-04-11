package squire;

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
        Collections.copy(fl.getFileList(), this.getFileList());
    }

    public void print()
    {
        System.out.println(fileList);
    }

    public ArrayList<String> getFileList()
    {
        return fileList;
    }

}
