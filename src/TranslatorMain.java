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
            System.out.println("1. Traducir texto del archivo 'texto.txt'");
            System.out.println("2. Imprimir las palabras ordenadas por el idioma: Ingles");
            System.out.println("3. Imprimir las palabras ordenadas por el idioma: Español");
            System.out.println("4. Imprimir las palabras ordenadas por el idioma: Frances");
            System.out.println("5. Exit");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    translateText(englishTree);
                    break;
                case "2":
                    printOrderedWords(englishTree);
                    break;
                case "3":
                    printOrderedWords(spanishTree);
                    break;
                case "4":
                    printOrderedWords(frenchTree);
                    break;
                case "5":
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

    private static void translateText(BinarySearchTree<String, Association<String, String, String>> englishTree) {
        String languageTo = "Spanish";
    
        try (BufferedReader br = new BufferedReader(new FileReader("data/texto.txt"))) {
            StringBuilder translatedText = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" "); //Separa la linea en tokens separados por un espacio.
                for (String token : tokens) {
                    String word = token.replaceAll("[^a-zA-Z]", ""); //Quita los signos de puntuacion.
                    String lowerCaseWord = word.toLowerCase();
                    String punctuation = token.replace(word, "");
    
                    Association<String, String, String> found = englishTree.find(lowerCaseWord);
                    if (found != null) {
                         // Se agrega la palabra traducida a mi variable translatedText
                        String translatedWord = languageTo.equals("Spanish") ? found.getValue1() : found.getValue2();
                        translatedText.append(translatedWord);
                    } else {
                         // Si no encuentra la palabra, mantiene la original
                        translatedText.append(word);
                    }
                    translatedText.append(punctuation).append(" "); //Agrega la puntuacion y el espacio al final de la palabra
                }
            }
            if (translatedText.length() > 0) {
                translatedText.deleteCharAt(translatedText.length() - 1); //Quita el ultimo espacio
            }
            System.out.println(translatedText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       

    private static void printOrderedWords(BinarySearchTree<String, Association<String, String, String>> tree) {
        IWalk<Association<String, String, String>> printWalk = association -> {
            System.out.println("(" + association.getKey() + ", " + association.getValue1() + ", " + association.getValue2() + ")");
        };
        tree.InOrderWalk(printWalk);
    }
}
