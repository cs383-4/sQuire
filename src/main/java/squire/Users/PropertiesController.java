package squire.Users;

import java.io.*;
import java.util.Properties;

/**
 * Created by Domn Werner on 4/22/2016.
 * Stores project settingsMap.
 */
public class PropertiesController
{
    private Properties prop = new Properties();
    private OutputStream output = null;
    private InputStream input = null;
    final String configFileName = "squire_config.properties";
    private static PropertiesController pc = null;

    public static PropertiesController getPropertiesController()
    {
        if (pc == null)
        {
            PropertiesController pc = new PropertiesController();
            pc.loadProps();
            return pc;
        }
        else
        {
            return pc;
        }
    }

    /**
     * Sets a property as a key, value pair to be saved to the config file.
     * @param key The key to assign the value to.
     * @param val The value assigned to the key.
     */
    public void setProp(String key, String val)
    {
        prop.setProperty(key, val);
    }

    /**
     * Gets a property value using a key.
     * @param key The key with which to retrieve the desired config value.
     * @return The config value matching the entered key.
     */
    public String getProp(String key)
    {
        return prop.getProperty(key);
    }

    /**
     * Saves squire settings to squire_config.properties file.
     */
    public void saveProps()
    {
        try
        {
            output = new FileOutputStream(configFileName);
            prop.store(output, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Loads the properties from the config file.
     */
    public void loadProps()
    {
        try
        {
            input = new FileInputStream(configFileName);
            prop.load(input);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (input != null)
            {
                try
                {
                    input.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}