package de.hsa.games.fatsquirrel.util.commandscanner;

public interface CommandTypeInfo {

    public String getName();
    public String getHelpText();
    public Class<?> []  getParams();
}
