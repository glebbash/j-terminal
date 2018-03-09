package com.glebcorp.terminal;

/**
 * Simple command syntax. Checks name match (case insensitive)
 * @author Glebbash
 */

public final class NameSyntax implements CommandSyntax{

    private final String name;

    private final String[] arg = new String[1];

    public NameSyntax(String name){
        this.name = name;
    }

    /**
     * @param input command to check syntax
     * @return command name as the only argument
     */

    @Override
    public String[] checkArgs(String input){
        if(!input.trim().equalsIgnoreCase(name))
            return null;
        arg[0] = name;
        return arg;
    }

    @Override
    public String toString(){
        return name;
    }

}
