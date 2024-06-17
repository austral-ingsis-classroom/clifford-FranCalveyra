package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;

import java.util.List;

public class Ls implements Command{
  @Override
  public String execute(FileSystemNode target, String flags, String params) {
    //TODO
    if(target instanceof Directory dir){
      List<FileSystemNode> children = dir.getChildren();
      return getOutput(children, flags, params);
    }
    return "";
  }

  private String getOutput(List<FileSystemNode> children, String flags, String params) {
    //TODO
    StringBuilder sb = new StringBuilder();
    children.forEach(child -> sb.append(child.getName()).append(" "));
    return sb.substring(0, sb.toString().length()-2);
  }
}
