package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;
import edu.austral.ingsis.clifford.filesystem.NodeType;

import java.util.List;

public class Remove implements Command{
  private final CLI cli;
  public Remove(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    String nodeName = args.getFirst();
    NodeType type = isFile(nodeName) ? NodeType.FILE: NodeType.DIRECTORY;
    String message = "";
    if(type.equals(NodeType.DIRECTORY)){
      message = removeDirectory(nodeName, flags);
    }
    if (type.equals(NodeType.FILE)){
      message = removeFile(nodeName);
    }
    return message;
  }

  private String removeFile(String nodeName) {
    cli.currentDirectory.removeChild(nodeName);
    return "'" + nodeName + "' removed";
  }

  private String removeDirectory(String name, List<String> flags) {
    if(!cli.currentDirectory.getChildren().isEmpty()){
      if(flags.isEmpty() || notValidFlags(flags)){
        return "cannot remove " + "'" + name + "', is a directory";
      }
      cli.currentDirectory.removeChild(name);
    }
    else{
      cli.currentDirectory.removeChild(name);
    }
    return "'" + name + "' removed";
  }

  private boolean notValidFlags(List<String> flags) {
    return !flags.contains("--recursive");
  }

  private boolean isFile(String nodeName) {
    return cli.currentDirectory.getChild(nodeName) instanceof File;
  }
}
