package com.glebcorp.terminal;

public final class NameSyntax implements CommandSyntax{

    private final String name;

    private final String[] arg = new String[1];

    public NameSyntax(String name){
        this.name = name;
    }

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
