package com.glebcorp.terminal;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexSyntax implements CommandSyntax {

    private final ArrayList<Integer> pos = new ArrayList<>();

    private final Pattern pattern;

    private final String[] args;

    private String syntax;

    public RegexSyntax(String ... params){
        int i = -1, count = 0;
        StringBuilder regexBuilder = new StringBuilder(),
                syntaxBuilder = new StringBuilder();
        regexBuilder.append("^");
        for(String s : params){
            if(i++ >= 0){
                syntaxBuilder.append(" ");
                regexBuilder.append("(\\s+)");
            }
            if(s == null) continue;

            if(s.equals("\\w")){
                regexBuilder.append("((?:[a-z][a-z]+))");
                syntaxBuilder.append("<word>");
                pos.add(count++, i);
            }else if(s.equals("\\d")){
                regexBuilder.append("(\\d+)");
                syntaxBuilder.append("<int>");
                pos.add(count++, i);
            }else {
                regexBuilder.append("(");
                regexBuilder.append(s);
                regexBuilder.append(")");
                syntaxBuilder.append(s);
            }
            i++;
        }
        regexBuilder.append("$");
        syntax = syntaxBuilder.toString();
        pattern = Pattern.compile(
                regexBuilder.toString(), Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        args = new String[count];
    }

    @Override
    public String[] checkArgs(String input){
        Matcher m = pattern.matcher(input);
        if(!m.find())
            return null;
        for(int i = 0; i < args.length; i++)
            args[i] = m.group(pos.get(i) + 1);
        return args;
    }

    @Override
    public String toString(){
        return syntax;
    }

}