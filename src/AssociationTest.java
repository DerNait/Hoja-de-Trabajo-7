import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AssociationTest {
    @Test
    public void testGetters() {
        Association<String, String, String> association = new Association<>("key", "value1", "value2");

        assertEquals("key", association.getKey());
        assertEquals("value1", association.getValue1());
        assertEquals("value2", association.getValue2());
    }

    @Test
    public void testSetters() {
        Association<String, String, String> association = new Association<>("key", "value1", "value2");

        association.setKey("newKey");
        association.setValue1("newValue1");
        association.setValue2("newValue2");
        
        assertEquals("newKey", association.getKey());
        assertEquals("newValue1", association.getValue1());
        assertEquals("newValue2", association.getValue2());
    }
}
