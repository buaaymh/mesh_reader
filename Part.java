import java.util.Iterator;

public interface Part extends Iterable<Element> {
  public abstract Iterator<Element> iterator();
  public abstract Element get(int i);
  public abstract Element put(int i, Element e);
}

