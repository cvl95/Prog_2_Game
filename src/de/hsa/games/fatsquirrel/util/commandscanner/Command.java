package de.hsa.games.fatsquirrel.util.commandscanner;

public class Command{
    private String name;
    private String beschreibung;
    private Object[] params;
    private CommandTypeInfo info;

    public Command(CommandTypeInfo info, Object[] params) {
       //this.name= type.getName();
        //this.beschreibung = type.getHelpText();
        this.info = info;
        this.params = params;

    }

/**
 * getter for command parameter
 * @return Object[]
 */
    public Object[] getParams(){
        return this.params;
    }

    /**
     * getter for commandtype
     * @return commandtypeinfo
     */
    public CommandTypeInfo getCommandType() {
        return this.info;
    }
   
   
}
