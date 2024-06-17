package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import java.util.List;

public class PrintWorkingDirectory implements Command {
  private final CLI cli;

  public PrintWorkingDirectory(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    return recursivePath(cli.currentDirectory);
  }

  private String recursivePath(Directory currentDirectory) {
    if (currentDirectory.parentDir == null) {
      return currentDirectory.getName();
    }
    return recursivePath(currentDirectory.parentDir) + "/" + currentDirectory.getName();
  }
}
