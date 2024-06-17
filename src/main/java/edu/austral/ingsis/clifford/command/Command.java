package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.FileSystemNode;

public interface Command {
  String execute(FileSystemNode target,String flags ,String params);
}
