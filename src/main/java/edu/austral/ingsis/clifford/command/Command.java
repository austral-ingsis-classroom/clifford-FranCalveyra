package edu.austral.ingsis.clifford.command;

import java.util.List;

public interface Command {
  String execute(List<String> flags, List<String> args);
}
