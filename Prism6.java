import java.util.Iterator;
import java.util.Vector;

public class Prism6 implements Body {
  private final Vector<Node> nodes;

  public Prism6(Node[] itsNodes) { 
    nodes = new Vector<Node>();
    for (int i = 0; i < itsNodes.length; i++) {
      nodes.add(itsNodes[i]);
    }
  }

  @Override
  public Iterator<Node> iterator() { 
    return nodes.iterator();
  }
  @Override
  public Node getNode(int i) {
    return nodes.get(i);
  }
}