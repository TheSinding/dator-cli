package main.java.model;

/**
 * Created by thesinding on 4/2/17.
 */
public enum Command {
    INSERT("INSERT INTO ")
    , UPDATE("UPDATE ")
    , SELECT("SELECT ")
    , DROP("DROP ");

    private String command;

    Command(String s) {
        this.command = s;
    }

    public String getCommand() {
        return command;
    }
}
