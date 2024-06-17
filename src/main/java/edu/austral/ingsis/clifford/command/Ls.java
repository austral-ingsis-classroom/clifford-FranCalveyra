package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;

import java.util.List;

public class Ls implements Command{
  private final CLI cli;
  public Ls(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    //TODO
    Directory dir = cli.getDirectory();
    List<FileSystemNode> children = dir.getChildren();
    return getOutput(children, flags, args);
  }

  private String getOutput(List<FileSystemNode> children, List<String> flags, List<String> params) {
    //TODO
    if(children.isEmpty()){
      return "";
    }
    StringBuilder sb = new StringBuilder();
    children.forEach(child -> sb.append(child.getName()).append(" "));
    return sb.substring(0, sb.toString().length()-2);
  }
}
