package com.glebcorp.terminal;

public abstract class Command{

    private CommandSyntax syntax;

    protected String[] args;

    public Command(){}

    public Command(String name){
        setSyntax(new NameSyntax(name));
    }

    public Command(String... args){
        if(args==null || args.length == 0)
            return;
        setSyntax(new RegexSyntax(args));
    }

    public Command(CommandSyntax syntax){
        this.syntax = syntax;
    }

    public void setSyntax(CommandSyntax syntax){
        this.syntax = syntax;
    }

    public CommandSyntax getSyntax(){
        return syntax;
    }

    protected int getInt(int pos){
        return Integer.parseInt(args[pos]);
    }

    protected boolean check(String in){
        if(syntax == null)
            return false;
        args = syntax.checkArgs(in);
        if(args == null)
            return false;
        else
            call();
        args = null;
        return true;
    }

    public abstract void call();

}