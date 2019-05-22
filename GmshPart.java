import java.util.Iterator;
import java.util.HashMap;

public class GmshPart implements Part {
  private HashMap<Integer,Element> elements;
  
  public GmshPart() {
    elements = new HashMap<Integer,Element>();
  }

  @Override
  public Iterator<Element> iterator() {
    return elements.values().iterator();
  }

  @Override
  public Element get(int i) {
    return elements.get(i);
  }

  @Override
  public Element put(int i, Element e) {
    return elements.put(i,e);
  }
}