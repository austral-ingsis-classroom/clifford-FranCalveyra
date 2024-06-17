package edu.austral.ingsis.clifford.filesystem;

public class File implements FileSystemNode{
  private final String name;
  private FileSystemNode ownReference;

  public File(String name) {
    this.name = name;
    this.ownReference = this;
  }

  @Override
  public void delete() {
    ownReference = null;
  }


  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getParentDirectory() {
    return null;
  }

  @Override
  public FileSystemNode getThis() {
    return ownReference;
  }
}
