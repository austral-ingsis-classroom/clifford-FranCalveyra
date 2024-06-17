package edu.austral.ingsis.clifford.cli;

import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.filesystem.Directory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CLI {
  public final Directory root;
  public Directory currentDirectory;
  private final Map<String, Command> existingCommands;

  public CLI(Directory root){
    this.root = root;
    this.currentDirectory = root;
    this.existingCommands = Map.of("ls", new Ls(this),
      "cd", new ChangeDirectory(this),
      "mkdir", new MakeDirectory(this),
      "pwd", new PrintWorkingDirectory(this),
      "touch", new Touch(this),
      "rm", new Remove(this));
  }

  public String executeCommand(String command){
    String[] commandString = command.split(" ");
    CommandLine commandLine = buildArgs(commandString);
    if(!existingCommands.containsKey(commandLine.action)){
      return "Invalid Command";
    }
    return existingCommands.get(commandLine.action).execute(commandLine.flags, commandLine.args);
  }

  //Private stuff
  private static class CommandLine {
    public String action;
    public List<String> flags;
    public List<String> args;
    public CommandLine(String action, List<String> flags, List<String> args){
      this.action = action;
      this.flags = flags;
      this.args = args;
    }
  }
  private CommandLine buildArgs(String[] args) {
    List<String> flags = args.length>2 ? new ArrayList<>(List.of(args[1])) : List.of(args[args.length-1]);
    return new CommandLine(args[0],flags, new ArrayList<>(List.of(args[args.length-1])));
  }

}

