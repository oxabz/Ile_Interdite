package Project.util;

import java.util.HashMap;

public class IdentifiedElement {
    private static HashMap<Integer,IdentifiedElement> elements = new HashMap<Integer, IdentifiedElement>();
    private static int lastId = 0;

    private int id;

    public IdentifiedElement() {
        id = nextId();
        elements.put(id,this);
    }

    public int getId() {
        return id;
    }

    private static int nextId(){
        return ++lastId;
    }

    public static IdentifiedElement getIdentifiedElement(int id){
        return elements.get(id);
    }

}
