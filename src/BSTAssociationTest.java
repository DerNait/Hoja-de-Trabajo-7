import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class BSTAssociationTest {

    private BinarySearchTree<String, Association<String, String, String>> tree;

    @Before
    public void setUp() {
        tree = new BinarySearchTree<>(Comparator.<String>naturalOrder());
    }

    @Test
    public void testInsertAndFind() {
        // Insertamos una asociación en el árbol
        String key = "house";
        Association<String, String, String> association = new Association<>(key, "casa", "maison");
        tree.insert(key, association);

        // Verificamos que el árbol no esté vacío
        assertFalse(tree.isEmpty());

        // Intentamos encontrar la asociación por la clave
        Association<String, String, String> foundAssociation = tree.find(key);
        assertNotNull(foundAssociation);

        // Verificamos los valores de la asociación encontrada
        assertEquals("house", foundAssociation.getKey());
        assertEquals("casa", foundAssociation.getValue1());
        assertEquals("maison", foundAssociation.getValue2());
    }

    @Test
    public void testFindNonExistent() {
        // Buscamos una clave que no existe
        String nonExistentKey = "tree";
        Association<String, String, String> foundAssociation = tree.find(nonExistentKey);
        assertNull(foundAssociation);
    }
}
