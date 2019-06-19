package Project.util;

import java.util.*;

public class MultiMap<k,v> {

    public MultiMap() {
        map = new HashMap<>();
    }

    HashMap<k, ArrayList<v>> map;



    public int size() {
        return map.size();
    }


    public boolean isEmpty() {
        return size()==0;
    }


    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }


    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }


    public ArrayList<v> get(Object key) {
        if (map.containsKey(key)){
            return map.get(key);
        }else{
            return new ArrayList<>();
        }
    }


    public v put(k key, v value) {
        if(map.containsKey(key)){
            map.get(key).add(value);
        }else {
            map.put(key,new ArrayList<v>(Arrays.asList(value)));
        }

        return value;
    }

    public ArrayList<v> put(k key, ArrayList<v> value) {
        if(map.containsKey(key)){
            map.get(key).addAll(value);
        }else {
            map.put(key,value);
        }

        return value;
    }


    public ArrayList<v> remove(Object key) {
        return map.remove(key);
    }


    public void putAll(MultiMap<k, v> m) {
        for (k key :
                map.keySet()) {
            put(key,m.get(key));
        }
    }


    public void clear() {
         map.clear();
    }


    public Set<k> keySet() {
        return map.keySet();
    }


}
