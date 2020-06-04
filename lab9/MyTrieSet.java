import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B{

    private class Node{
        private Map<Character, Node> map;
        private boolean isKey;
        private Character ch;
        private String key;
        public Node(Character ch, boolean isKey){
            map = new HashMap<>();
            this.isKey = isKey;
            this.ch = ch;
        }
        public Node(){
            map = new HashMap<>();
        }

    }
    private Node root;
    public MyTrieSet(){
        root = new Node();
    }
    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (char c: key.toCharArray()){
            if (!curr.map.containsKey(c)){
                return false;
            }
            curr = curr.map.get(c);
        }
        if (curr.isKey){
            return true;
        }
        return false;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
        curr.key = key;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        Node curr = root;
        for (char c: prefix.toCharArray()){
            if (!curr.map.containsKey(c)){
                return res;
            }
            curr = curr.map.get(c);
        }
        if (curr.isKey){
            res.add(curr.key);
        }
        find(res, curr);
        return res;
    }

    private void find(List<String> res, Node start){
        if (start.map.size() == 0){
            return;
        }
        for (Node i: start.map.values()){
            if (i.isKey){
                res.add(i.key);
            }
            find(res, i);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("unsupported");

    }
}