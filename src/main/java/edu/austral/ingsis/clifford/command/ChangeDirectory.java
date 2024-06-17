package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;
import edu.austral.ingsis.clifford.filesystem.NodeType;

import java.util.List;

public class ChangeDirectory implements Command{
  private final CLI cli;
  public ChangeDirectory(CLI cli) {
    this.cli = cli;
  }
  @Override
  public String execute(List<String> flags, List<String> args) {
    String directoryName = args.getFirst();
    if(directoryName.equals("./")){
      directoryName = cli.currentDirectory.getName();
    }
    else if(directoryName.equals("../")){
      directoryName = cli.currentDirectory.parentDir.getName();
    }

    if(!cli.currentDirectory.containsElement(directoryName, NodeType.DIRECTORY)){
      return "directory does not exist";
    }
    moveToDirectory(directoryName);
    return "moved to directory " + "'" + directoryName + "'"; 
  }


  //Private stuff
  private void moveToDirectory(String directoryName) {
    cli.currentDirectory = (Directory) getFilteredNodes(directoryName).getFirst();
  }

  private List<FileSystemNode> getFilteredNodes(String name) {
    List<FileSystemNode> children = cli.currentDirectory.getChildren();
    return children.stream().filter(child-> child.getName().equals(name)).toList();
  }
}
