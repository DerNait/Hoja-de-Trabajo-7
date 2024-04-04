import org.junit.Test;
import static org.junit.Assert.*;

public class TreeNodeTest {
    
    @Test
    public void testGetKey() {
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        assertEquals("key", node.getKey());
    }

    @Test
    public void testGetLeft() {
        TreeNode<String, String> leftNode = new TreeNode<>("left", "leftValue");
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        node.setLeft(leftNode);
        assertEquals(leftNode, node.getLeft());
    }

    @Test
    public void testGetParent() {
        TreeNode<String, String> parentNode = new TreeNode<>("parent", "parentValue");
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        node.setParent(parentNode);
        assertEquals(parentNode, node.getParent());
    }

    @Test
    public void testGetRight() {
        TreeNode<String, String> rightNode = new TreeNode<>("right", "rightValue");
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        node.setRight(rightNode);
        assertEquals(rightNode, node.getRight());
    }

    @Test
    public void testGetValue() {
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        assertEquals("value", node.getValue());
    }

    @Test
    public void testSetKey() {
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        node.setKey("newKey");
        assertEquals("newKey", node.getKey());
    }

    @Test
    public void testSetLeft() {
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        TreeNode<String, String> leftNode = new TreeNode<>("left", "leftValue");
        node.setLeft(leftNode);
        assertEquals(leftNode, node.getLeft());
    }

    @Test
    public void testSetParent() {
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        TreeNode<String, String> parentNode = new TreeNode<>("parent", "parentValue");
        node.setParent(parentNode);
        assertEquals(parentNode, node.getParent());
    }

    @Test
    public void testSetRight() {
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        TreeNode<String, String> rightNode = new TreeNode<>("right", "rightValue");
        node.setRight(rightNode);
        assertEquals(rightNode, node.getRight());
    }

    @Test
    public void testSetValue() {
        TreeNode<String, String> node = new TreeNode<>("key", "value");
        node.setValue("newValue");
        assertEquals("newValue", node.getValue());
    }
}
