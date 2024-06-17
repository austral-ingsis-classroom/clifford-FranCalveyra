package edu.austral.ingsis.clifford.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemNode {
  public Directory parentDir;
  private final List<FileSystemNode> children;
  private final String name;

  public Directory(String name) {
    this.name = name;
    children = new ArrayList<>();
  }

  private Directory(String name, Directory parentDir, List<FileSystemNode> children) {
    this.name = name;
    this.parentDir = parentDir;
    this.children = children;
  }

  @Override
  public String getName() {
    return name;
  }

  public FileSystemNode getChild(String name) {
    return children.stream().filter(child -> child.getName().equals(name)).findFirst().orElse(null);
  }

  public List<FileSystemNode> getChildren() {
    return children;
  }

  public void addChild(FileSystemNode node) {
    FileSystemNode copy = node;
    if (node instanceof Directory dir) {
      copy = new Directory(dir.name, dir.parentDir, dir.children);
    } else if (node instanceof File file) {
      copy = new File(file.getName(), file.parentDir);
    }
    children.add(copy);
  }

  public void removeChild(String nodeName) {
    children.removeIf(child -> child.getName().equals(nodeName));
  }

  public boolean doesNotContainElement(String fileName, NodeType type) {
    return getChildren().stream()
        .noneMatch(child -> child.getName().equals(fileName) && sameType(type, child));
  }

  private boolean sameType(NodeType type, FileSystemNode child) {
    return type == NodeType.DIRECTORY ? child instanceof Directory : child instanceof File;
  }
}
