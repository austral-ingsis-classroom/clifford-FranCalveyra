package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;

import java.util.List;

public class Touch implements Command{
  private final CLI cli;
  public Touch(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    return "";
  }
}
