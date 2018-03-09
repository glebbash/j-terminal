package com.glebcorp.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <pre>
 * Extensible embeddable terminal to be used for advanced tests
 * Example usage :
 * <code>
 * final Terminal calc = new Terminal();
 * calc.addCommands(new Command("add", Args.NUMBER, "and", Args.NUMBER) {
 *     {@literal @}Override
 *     public void call() {
 *         System.out.println(getInt(0) + getInt(1));
 *     }
 * }, new Command("exit") {
 *     {@literal @}Override
 *     public void call() {
 *     calc.stop();
 *     }
 * });
 * calc.start(System.in);
 * </code>
 * </pre>
 * @author Glebbash
 * @version 0.3.0
 */

public class Terminal{

    private static final Command DEFAULT_COMMAND = new Command() {
        @Override
        public void call() {
            System.out.println("Command not found");
        }
    };

    private final ArrayList<Command> commands = new ArrayList<>();

    private boolean running;

    private Command defaultCommand = DEFAULT_COMMAND;

    /**
     * @return all commands
     */

    public ArrayList<Command> getAllCommands(){
        return commands;
    }

    /**
     * Sets the default command
     * @param defaultCommand to be called when command is not processed
     */

    public void setDefaultCommand(Command defaultCommand){
        this.defaultCommand = defaultCommand;
    }

    /**
     * Adds command to terminal
     * @param command to be added
     */

    public void addCommand(Command command){
        commands.add(command);
    }

    /**
     * Adds commands to terminal
     * @see Terminal#addCommand(Command) addCommand
     * @param commands commands to be added
     */

    public void addCommands(Command... commands){
        for(Command command : commands)
            addCommand(command);
    }

    /**
     * Call to start terminal
     * @param stream stream to process commands from
     */

    public void start(InputStream stream){
        running = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        while(running) {
            try {
                process(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Call to manually process command. If command is not processed calls default command
     * @see Terminal#setDefaultCommand(Command) setDefaultCommand
     * @param command to process
     * @return true if command is processed, else otherwise
     */

    public boolean process(String command){
        for(Command com : commands)
            if(com.check(command))
                return true;
        defaultCommand.call();
        return false;
    }

    /**
     * Called to stop command processing
     */

    public void stop(){
        running = false;
    }

}
