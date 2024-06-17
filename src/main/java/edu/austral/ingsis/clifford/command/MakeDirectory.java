package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import java.util.List;

public class MakeDirectory implements Command {
  private final CLI cli;

  public MakeDirectory(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    String name = args.getFirst();
    Directory directory = new Directory(name);
    directory.parentDir = cli.currentDirectory;
    cli.currentDirectory.addChild(directory);
    return "'" + name + "' directory created";
  }
}
