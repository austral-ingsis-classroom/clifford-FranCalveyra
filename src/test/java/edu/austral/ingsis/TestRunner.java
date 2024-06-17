package edu.austral.ingsis;

import edu.austral.ingsis.clifford.cli.CLI;
import java.util.List;

public class TestRunner implements FileSystemRunner {
  private final CLI cli;

  public TestRunner(CLI cli) {
    this.cli = cli;
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    return commands.stream().map(cli::executeCommand).toList();
  }
}
