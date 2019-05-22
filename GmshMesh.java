import java.util.Iterator;
import java.util.HashMap;

public class GmshMesh implements Mesh {
  private HashMap<String,Part> parts;

  public GmshMesh() {
    parts = new HashMap<String,Part>();
  }

  @Override
  public  Iterator<Part> iterator() {
    return parts.values().iterator();
  }
  @Override
  public  Part get(String name) {
    return parts.get(name);
  }

  @Override
  public Part put(String name, Part part) {
    return parts.put(name, part);
  }
}