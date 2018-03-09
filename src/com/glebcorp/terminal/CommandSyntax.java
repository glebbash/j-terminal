package com.glebcorp.terminal;

/**
 * Command syntax
 * @see NameSyntax
 * @see RegexSyntax
 * @author Glebbash
 */

public interface CommandSyntax{

    /**
     * @param input command to check syntax
     * @return array of arguments or null if syntax do not match
     */

    String[] checkArgs(String input);

}