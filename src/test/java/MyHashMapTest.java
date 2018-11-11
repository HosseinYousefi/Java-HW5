import junit.framework.TestCase;
import org.junit.*;
import java.util.*;


public class MyHashMapTest extends TestCase {

    MyHashMap<String, Integer> myHashMap;

    public String shuffle(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray())
            characters.add(c);
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        myHashMap = new MyHashMap<>();
    }

    @After
    public void tearDown() throws Exception {
        myHashMap = null;
    }

    @Test
    public void testInit() {
        assertEquals(myHashMap.size(), 0);
    }

    @Test
    public void testAddingUnique() {
        myHashMap.put("Hossein", 0);
        myHashMap.put("Yousefi", 1);
        assertEquals(myHashMap.size(), 2);
    }

    @Test
    public void testAddingSame() {
        myHashMap.put("Hossein", 0);
        myHashMap.put("Hossein", 1);
        assertEquals(myHashMap.size(), 1);
    }

    @Test
    public void testGettingExistingKey() {
        myHashMap.put("Hossein", 188);
        int result = myHashMap.get("Hossein");
        assertEquals(result, 188);
    }

    @Test
    public void testReplacingValue() {
        myHashMap.put("Hossein", 123);
        myHashMap.put("Hossein", 456);
        int result = myHashMap.get("Hossein");
        assertEquals(result, 456);
    }

    @Test
    public void testAddingSameHash() {
        String str = "abcdefghijk";
        String sfl = str;
        myHashMap.put(str, 100);
        int strHash = myHashMap.myHash(str);
        do sfl = shuffle(sfl); while (myHashMap.myHash(sfl) != strHash);
        myHashMap.put(sfl, 200);
        int strResult = myHashMap.get(str);
        int sflResult = myHashMap.get(sfl);
        assertEquals(sflResult, 200);
        assertEquals(strResult, 100);
        assertEquals(myHashMap.size(), 2);
    }

    @Test
    public void testAddingNullValue() {
        myHashMap.put("Hossein", null);
        assertEquals(myHashMap.size(), 1);
    }

    @Test
    public void testGettingNullValue() {
        myHashMap.put("Hossein", null);
        assertEquals(myHashMap.get("Hossein"), null);
    }

    @Test
    public void testGettingNonExistingKey() {
        assertEquals(myHashMap.get("Hossein"), null);
    }

    @Test
    public void testGettingNullKey() {
        assertEquals(myHashMap.get(null), null);
    }

    @Test
    public void testRemovingExistingKey() {
        myHashMap.put("Hossein", 123);
        int result = myHashMap.remove("Hossein");
        assertEquals(myHashMap.size(), 0);
        assertEquals(result, 123);
    }

    @Test
    public void testRemovingNonExistingKey() {
        assertEquals(myHashMap.remove("Hossein"), null);
        assertEquals(myHashMap.size(), 0);
    }

    @Test
    public void testRemovingSameHash() {
        testAddingSameHash();
        int result = myHashMap.remove("abcdefghijk");
        assertEquals(myHashMap.size(), 1);
        assertEquals(result, 100);
    }

    @Test
    public void testRemovingNonExistingSameHash() {
        String str = "abcdefghijk";
        String sfl = str;
        myHashMap.put(str, 100);
        int strHash = myHashMap.myHash(str);
        do sfl = shuffle(sfl); while (myHashMap.myHash(sfl) != strHash);
        myHashMap.remove(sfl);
        assertEquals(myHashMap.size(), 1);
        int result = myHashMap.get(str);
        assertEquals(result, 100);
    }

    @Test
    public void testClearing() {
        myHashMap.put("Hossein", 100);
        myHashMap.put("Daniela", 200);
        myHashMap.clear();
        assertEquals(myHashMap.size(), 0);
    }

    @Test
    public void testClearingEmptyMap() {
        myHashMap.clear();
        assertEquals(myHashMap.size(), 0);
    }

    @Test
    public void testContainsKey() {
        myHashMap.put("Hossein", 100);
        assertEquals(myHashMap.containsKey("Hossein"), true);
        assertEquals(myHashMap.containsKey("Daniela"), false);
    }

    @Test
    public void testContainsValue() {
        myHashMap.put("Hossein", 100);
        myHashMap.put("Daniela", 200);
        assertEquals(myHashMap.containsValue(200), true);
        assertEquals(myHashMap.containsValue(100), true);
        assertEquals(myHashMap.containsValue(333), false);
        assertEquals(myHashMap.containsValue(555), false);
    }

    @Test
    public void testKeySet() {
        myHashMap.put("Hossein", 100);
        assertEquals(myHashMap.keySet().contains("Hossein"), true);
        assertEquals(myHashMap.keySet().contains("Daniela"), false);
        assertEquals(myHashMap.keySet().size(), 1);
    }

    @Test
    public void testValues() {
        myHashMap.put("Hossein", 100);
        assertEquals(myHashMap.values().contains(100), true);
        assertEquals(myHashMap.values().contains(200), false);
        assertEquals(myHashMap.values().size(), 1);
    }
}