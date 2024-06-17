package edu.austral.ingsis.clifford.cli;

import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.filesystem.Directory;

import java.util.List;
import java.util.Map;

public class CLI {
  private final Directory root;
  private Directory currentDirectory;
  private Map<String, Command> existingCommands;

  public CLI(Directory root){
    this.root = root;
    this.currentDirectory = new Directory("./");
    this.existingCommands = Map.of("ls", new Ls(this),
      "cd", new ChangeDirectory(this),
      "mkdir", new MakeDirectory(this),
      "pwd", new PrintWorkingDirectory(this),
      "touch", new Touch(this),
      "rm", new Remove(this));
  }

  public String executeCommand(String command){
    if(!existingCommands.containsKey(command)){
      return "Invalid Command";
    }
    String[] commandString = command.split(" ");
    Arguments arguments = buildArgs(commandString);
    return existingCommands.get(arguments.action).execute(arguments.flags, arguments.args);
  }

  private Arguments buildArgs(String[] args) {
    //TODO
    List<String> flags = args.length>2 ? List.of(args[1]) : List.of();
    return new Arguments(args[0],flags, List.of(args[args.length-1]));
  }

  public Directory getDirectory() {
    return currentDirectory;
  }

  private static class Arguments {
    public String action;
    public List<String> flags;
    public List<String> args;
    public Arguments(String action,List<String> flags, List<String> args){
      this.action = action;
      this.flags = flags;
      this.args = args;
    }
  }
}

