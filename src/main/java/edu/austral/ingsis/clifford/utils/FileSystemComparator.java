package edu.austral.ingsis.clifford.utils;

import edu.austral.ingsis.clifford.cli.CLI;
import edu.austral.ingsis.clifford.filesystem.Directory;
import java.util.Comparator;

public class FileSystemComparator implements Comparator<String> {
  private final CLI cli;

  public FileSystemComparator(CLI cli) {
    this.cli = cli;
  }

  @Override
  public int compare(String o1, String o2) {
    return comparation(o1, o2);
  }

  private int comparation(String o1, String o2) {
    if (isDirectory(o1) && isDirectory(o2)) {
      return getIndex(o1) - getIndex(o2);
    } else if (isDirectory(o1) && !isDirectory(o2)) {
      return -1;
    } else if (isDirectory(o2) && !isDirectory(o1)) {
      return 1;
    }
    return o1.compareTo(o2);
  }

  private int getIndex(String o1) {
    return cli.currentDirectory.getChildren().indexOf(cli.currentDirectory.getChild(o1));
  }

  private boolean isDirectory(String name) {
    return cli.currentDirectory.getChild(name) instanceof Directory;
  }
}
