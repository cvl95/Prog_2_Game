package de.hsa.games.fatsquirrel.util.commandscanner;

public enum GameCommandType implements CommandTypeInfo {


// 	CommandTypes in here 
//    HELP("help", "  * list all commands"),
//    EXIT("exit", "  * exit program"),
//    ADDI("addi", "<param1>  <param2>   * simple integer add ",int.class, int.class ),
//    ADDF("addf", "<param1>  <param2>   * simple float add ",float.class, float.class ),
//    ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ",String.class, int.class ),

    HELP("help", " \t * list all commands"),
    EXIT("exit", " \t * exit program"),
    MOVE("move", " \t * <direction> moves in direction", String.class),
    MASTER_ENERGY("master_energy", " \t* get energy of MasterSquirrel"),
    SPAWN_MINI("space", " \t* <energy> spawns a mini squirrel", String.class),
	UP("w", "move up"),
	DOWN("s", "move down"),
	LEFT("a", "move left"),
	RIGHT("d", "move right"),
	UP_LEFT("up_left", "move up_left"),
	UP_RIGHT("up_right", "move up_right"),
	DOWN_LEFT("down_left", "move down_left"),
	DOWN_RIGHT("down_right", "move down_right"),
	STAY("stay", "stay on position");
	
	

  // CommandTypeInfo[] commandTypeInfos
   private String name;
   private String beschreibung;
   private Class<?> parameterype;
   private Class<?> paramtertype2;


    GameCommandType(String name, String beschreibung, Class<?> parametertype){
            this.name = name;
            this.beschreibung = beschreibung;
            this.parameterype = parametertype;
    }
    GameCommandType(String name, String beschreibung, Class<?> parametertype, Class<?> parametertype2){
        this.name = name;
        this.beschreibung = beschreibung;
        this.parameterype = parametertype;
        this.paramtertype2 = parametertype2;
}

    GameCommandType(String name, String beschreibung){
            this.name = name;
            this.beschreibung = beschreibung;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getHelpText() {
        return this.beschreibung;
    }

    @Override
    public Class<?>[] getParams() {
        Class<?>[] classes = new Class[2];
        classes[0] = this.parameterype;
        classes[1] = this.paramtertype2;
        return classes;
    }

//    public String toString(){
//        return name + " :Name" + beschreibung + " :Beschreibung" + parameterype.toString() + " :Parameter 1" + paramtertype2.toString() + " :Parameter2";
//
//    }
}

