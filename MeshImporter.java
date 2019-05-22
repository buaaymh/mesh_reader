

public interface MeshImporter {
  public abstract void open(String filename);
  public abstract void parse();
  public abstract Mesh getMesh();
}