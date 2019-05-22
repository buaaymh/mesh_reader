import java.util.Iterator;

public interface Mesh extends Iterable<Part> {
  public abstract Iterator<Part> iterator();
  public abstract Part get(String name);
  public abstract Part put(String name, Part part);
}