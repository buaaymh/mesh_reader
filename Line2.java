import java.util.Iterator;
import java.util.Vector;

public class Line2 implements Line {
  private final Vector<Node> nodes;

  public Line2(Node[] itsNodes) { 
    nodes = new Vector<Node>();
    nodes.add(itsNodes[0]);
    nodes.add(itsNodes[1]);
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
