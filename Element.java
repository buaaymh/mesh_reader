import java.util.HashMap;
import java.util.Iterator;

public interface Element extends Iterable<Node> {
  public abstract Iterator<Node> iterator();
  public abstract Node getNode(int i);
}
