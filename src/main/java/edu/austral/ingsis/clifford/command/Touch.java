package edu.austral.ingsis.clifford.command;

import static edu.austral.ingsis.clifford.filesystem.NodeType.FILE;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.File;
import java.util.List;

public class Touch implements Command {
  private final CLI cli;

  public Touch(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    String fileName = args.getFirst();
    if (fileName.contains("/") || fileName.contains(" ")) {
      return "could not create file";
    }

    if (cli.currentDirectory.doesNotContainElement(fileName, FILE)) {
      File file = new File(fileName);
      file.parentDir = cli.currentDirectory;
      cli.currentDirectory.addChild(file);
    }
    return "'" + fileName + "'" + " file created";
  }
}
