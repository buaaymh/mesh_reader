import java.util.Iterator;
import java.util.Vector;

public class Textrahedron4 implements Body {
  private final Vector<Node> nodes;

  public Textrahedron4(Node[] itsNodes) { 
    nodes = new Vector<Node>();
    nodes.add(itsNodes[0]);
    nodes.add(itsNodes[1]);
    nodes.add(itsNodes[2]);
    nodes.add(itsNodes[3]);
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