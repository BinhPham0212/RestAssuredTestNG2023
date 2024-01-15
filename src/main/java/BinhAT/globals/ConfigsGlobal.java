package BinhAT.globals;

import BinhAT.helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String URI = PropertiesHelper.getValue("URI");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String HEADERACCEPT = PropertiesHelper.getValue("HEADERACCEPT");
    public static String CONTENTTYPE = PropertiesHelper.getValue("CONTENTTYPE");
}
