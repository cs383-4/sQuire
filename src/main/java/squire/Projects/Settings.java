package squire.Projects;

import squire.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Domn Werner on 4/22/2016.
 * Stores project settingsMap.
 */
public class Settings
{
    private static File settingsFile;
    private static Map<String, String> settingsMap = new HashMap<String, String>();

    public static File getSettingsFile()
    {
        if (settingsFile == null)
        {
            String settingsFilePath = Main.settingsFilePath;
            settingsFile = new File(settingsFilePath);
        }
        return settingsFile;
    }

    public static void setSettingsFile(File f) { settingsFile = f; }
    public static Map<String, String> getSettingsMap() { return settingsMap; }

    public static void setSettings(String key, String val)
    {
        settingsMap.put(key, val);
    }

    // Saves settings from settingsMap to settingsFile.
    public static void saveSettingsToFile()
    {
        File f = getSettingsFile();
        for (Map.Entry<String, String> entry : settingsMap.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();
            try
            {
                PrintWriter writer = new PrintWriter(f.getName(), "UTF-8");
                writer.println(key);
                writer.println(value);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
    }
}
