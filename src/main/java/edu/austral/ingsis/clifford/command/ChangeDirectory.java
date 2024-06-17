package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.NodeType;
import java.util.List;

public class ChangeDirectory implements Command {
  private final CLI cli;

  public ChangeDirectory(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    String directoryName = args.getFirst();
    if (directoryName.equals("/")
        || (directoryName.equals("..") && cli.currentDirectory.parentDir == null)) {
      directoryName = "/";
      return "moved to directory " + "'" + directoryName + "'";
    }
    if (directoryName.equals("..")) {
      cli.currentDirectory = cli.currentDirectory.parentDir;
      return "moved to directory " + "'/'";
    }
    return parsePath(directoryName);
  }

  // Private stuff

  private String parsePath(String directoryName) {
    String[] directories = directoryName.split("/");
    for (String path : directories) {
      if (cli.currentDirectory.doesNotContainElement(path, NodeType.DIRECTORY)) {
        return "'" + directoryName + "' directory does not exist";
      }
      cli.currentDirectory = (Directory) cli.currentDirectory.getChild(path);
    }
    return "moved to directory " + "'" + cli.currentDirectory.getName() + "'";
  }
}
