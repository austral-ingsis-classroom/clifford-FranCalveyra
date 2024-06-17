package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystemNode;
import edu.austral.ingsis.clifford.utils.FileSystemComparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ls implements Command {
  private final CLI cli;

  public Ls(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> flags, List<String> args) {
    Directory dir = cli.currentDirectory;
    List<FileSystemNode> children = dir.getChildren();
    List<String> childrenNames =
        new ArrayList<>(children.stream().map(FileSystemNode::getName).toList());
    return getOutput(childrenNames, flags);
  }

  private String getOutput(List<String> children, List<String> flags) {
    if (children.isEmpty()) {
      return "";
    }
    if (flags.isEmpty() || notValidFlags(flags)) {
      Comparator<String> fsComparator = new FileSystemComparator(cli);
      children.sort(fsComparator);
      return getString(children);
    }
    Comparator<String> sortingOrder = getComparator(flags);
    children.sort(sortingOrder);
    return getString(children);
  }

  private boolean notValidFlags(List<String> flags) {
    return !(flags.contains("--ord=asc") || flags.contains("--ord=desc"));
  }

  private Comparator<String> getComparator(List<String> flags) {
    return flags.getFirst().equals("--ord=asc")
        ? Comparator.naturalOrder()
        : Comparator.reverseOrder();
  }

  private String getString(List<String> childrenNames) {

    StringBuilder sb = new StringBuilder();
    childrenNames.forEach(child -> sb.append(child).append(" "));
    if (sb.toString().charAt(sb.length() - 1) == ' ') {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }
}
