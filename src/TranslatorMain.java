/* Hoja de Trabajo 7
 * Kevin Josué Villagrán Mérida
 * 23584
 */
import java.util.Comparator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TranslatorMain {

    public static void main(String[] args) {
        // Crear los BST de los tres idiomas
        Comparator<String> stringComparator = Comparator.naturalOrder();
        BinarySearchTree<String, Association<String, String, String>> englishTree = new BinarySearchTree<>(stringComparator);
        BinarySearchTree<String, Association<String, String, String>> spanishTree = new BinarySearchTree<>(stringComparator);
        BinarySearchTree<String, Association<String, String, String>> frenchTree = new BinarySearchTree<>(stringComparator);

        // Se agregan las lineas del diccionario a los arboles
        populateTrees(englishTree, spanishTree, frenchTree);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escoja una opcion:");
            System.out.println("1. Traducir texto del archivo 'texto.txt' a Español");
            System.out.println("2. Traducir texto del archivo 'texto.txt' a Ingles");
            System.out.println("3. Traducir texto del archivo 'texto.txt' a Frances");
            System.out.println("4. Imprimir las palabras ordenadas por el idioma: Ingles");
            System.out.println("5. Imprimir las palabras ordenadas por el idioma: Español");
            System.out.println("6. Imprimir las palabras ordenadas por el idioma: Frances");
            System.out.println("7. Exit");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    translateText(englishTree, spanishTree, frenchTree, "Spanish");
                    break;
                case "2":
                    translateText(englishTree, spanishTree, frenchTree, "English");
                    break;
                case "3":
                    translateText(englishTree, spanishTree, frenchTree, "French");
                    break;
                case "4":
                    printOrderedWords(englishTree);
                    break;
                case "5":
                    printOrderedWords(spanishTree);
                    break;
                case "6":
                    printOrderedWords(frenchTree);
                    break;
                case "7":
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida. Ingrese un valor numerico de 1 a 5.");
            }
        }
    }

    private static void populateTrees(BinarySearchTree<String, Association<String, String, String>> englishTree, BinarySearchTree<String, Association<String, String, String>> spanishTree, BinarySearchTree<String, Association<String, String, String>> frenchTree) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/diccionario.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                Association<String, String, String> association = new Association<>(words[0], words[1], words[2]);
                englishTree.insert(words[0].toLowerCase(), association);
                spanishTree.insert(words[1].toLowerCase(), association);
                frenchTree.insert(words[2].toLowerCase(), association);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void translateText(BinarySearchTree<String, Association<String, String, String>> englishTree,
                                    BinarySearchTree<String, Association<String, String, String>> spanishTree,
                                    BinarySearchTree<String, Association<String, String, String>> frenchTree,
                                    String targetLanguage) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/texto.txt"))) {
            StringBuilder translatedText = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    // Consideramos que la palabra puede estar en cualquiera de los tres idiomas
                    String lowerCaseWord = token.toLowerCase();
                    String translatedWord = findAndTranslate(lowerCaseWord, englishTree, spanishTree, frenchTree, targetLanguage);
                    translatedText.append(translatedWord).append(" ");
                }
            }

            if (translatedText.length() > 0) {
                translatedText.deleteCharAt(translatedText.length() - 1); // Eliminar el último espacio
            }

            System.out.println(translatedText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String findAndTranslate(String word, 
        BinarySearchTree<String, Association<String, String, String>> englishTree,
        BinarySearchTree<String, Association<String, String, String>> spanishTree,
        BinarySearchTree<String, Association<String, String, String>> frenchTree,
        String targetLanguage) {
        Association<String, String, String> found = englishTree.find(word);
        if (found == null) {
            found = spanishTree.find(word);
        }
        if (found == null) {
            found = frenchTree.find(word);
        }
        if (found != null) {
            switch (targetLanguage) {
                case "Spanish":
                    return found.getValue1(); // Traducción a Español
                case "English":
                    return found.getKey(); // Traducción a Inglés
                case "French":
                    return found.getValue2(); // Traducción a Francés
            }
        }
        return "*" + word + "*"; // Si no se encuentra la palabra, se devuelve marcada
    }

    private static void printOrderedWords(BinarySearchTree<String, Association<String, String, String>> tree) {
        IWalk<Association<String, String, String>> printWalk = association -> {
            System.out.println("(" + association.getKey() + ", " + association.getValue1() + ", " + association.getValue2() + ")");
        };
        tree.InOrderWalk(printWalk);
    }
}
