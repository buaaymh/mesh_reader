import java.util.Iterator;
import java.util.Vector;

public class Point1 implements Face {
  private final Vector<Node> nodes;

  public Point1(Node[] itsNodes) { 
    nodes = new Vector<Node>();
    nodes.add(itsNodes[0]);
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
