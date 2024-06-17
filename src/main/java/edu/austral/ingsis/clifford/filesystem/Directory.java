package edu.austral.ingsis.clifford.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemNode {
  private FileSystemNode ownReference;
  private List<FileSystemNode> children;
  private String name;

  public Directory(String name) {
    this.name = name;
    children = new ArrayList<FileSystemNode>();
    ownReference = this;
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


  public List<FileSystemNode> getChildren() {
    return children;
  }

  public void addChild(FileSystemNode node) {

  }
  public void removeChild(FileSystemNode node) {

  }
}
