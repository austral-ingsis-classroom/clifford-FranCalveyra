package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.FileSystemNode;

public class PrintWorkingDirectory implements Command{
  @Override
  public String execute(FileSystemNode target, String flags, String params) {
    return "";
  }
}
