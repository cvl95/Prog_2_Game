package de.hsa.games.fatsquirrel.util.commandscanner;

public class ScanException extends RuntimeException{
    public ScanException(String message){
        super(message);
    }

    public ScanException(){
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}