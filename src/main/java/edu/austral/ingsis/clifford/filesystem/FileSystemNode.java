package edu.austral.ingsis.clifford.filesystem;

public interface FileSystemNode {
  void delete();
  String getName();
  Directory getParentDirectory();
  FileSystemNode getThis();
}
