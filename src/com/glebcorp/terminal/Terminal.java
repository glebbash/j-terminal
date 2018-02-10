package com.glebcorp.terminal;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal{

    private final ArrayList<Command> commands = new ArrayList<>();

    private final Scanner scanner;

    private boolean running;

    private Command defaultCommand;

    public Terminal(InputStream in){
        scanner = new Scanner(in);
        setDefaultCommand(new Command(){
            @Override
            public void call(){
                System.out.println("Command not found");
            }
        });
    }

    public ArrayList<Command> getAllCommands(){
        return new ArrayList<>(commands);
    }

    public void setDefaultCommand(Command defaultCommand){
        this.defaultCommand = defaultCommand;
    }

    public void addCommand(Command c){
        commands.add(c);
    }

    public void addCommands(Command... cs){
        for(Command c : cs)
            addCommand(c);
    }

    public void start(){
        running = true;
        while(running)
            check:{
                String in = scanner.nextLine();
                for(Command com : commands)
                    if(com.check(in))
                        break check;
                defaultCommand.call();
            }
    }

    public void stop(){
        running = false;
    }

}
