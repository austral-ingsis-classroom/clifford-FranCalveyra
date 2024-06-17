package edu.austral.ingsis;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner implements FileSystemRunner{
  private CLI cli;
  public TestRunner(CLI cli) {
    this.cli = cli;
  }

  @Override
  public List<String> executeCommands(List<String> commands) {

    return commands.stream().map(cli::executeCommand).collect(Collectors.toList());
  }
}
