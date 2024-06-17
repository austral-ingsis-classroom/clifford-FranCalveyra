package edu.austral.ingsis.clifford.filesystem;

public class File implements FileSystemNode {
  private final String name;
  public Directory parentDir;

  public File(String name) {
    this.name = name;
  }

  protected File(String name, Directory parentDir) {
    this.name = name;
    this.parentDir = parentDir;
  }

  @Override
  public String getName() {
    return name;
  }
}
