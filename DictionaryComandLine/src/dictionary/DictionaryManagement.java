package dictionary;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
/**
 * 
 * @author Nhóm không tên
 * @version Commandline
 */
public class DictionaryManagement {

    private Dictionary dictionary = new Dictionary();
    
    public Dictionary getDictionary() {
        return this.dictionary;
    }
    
    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int check = binarySearch(str);
        if (check == -1)
            System.out.println("Không tìm thấy từ");
        else {
            System.out.print(dictionary.getWords().get(check).getWord_target() + "\t");
            System.out.println(dictionary.getWords().get(check).getWord_explain());
        }
    }
    
    public int binarySearch(String str) {
        int left = 0;
        int right = dictionary.getSizeWords() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (str.compareToIgnoreCase(dictionary.getWords().get(mid).getWord_target()) == 0) {
                return mid;
            }
            else if (str.compareToIgnoreCase(dictionary.getWords().get(mid).getWord_target()) > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public int binarySearchFirstStartWith(String str) {
        int left = 0;
        int right = dictionary.getSizeWords() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dictionary.getWords().get(mid).getWord_target().startsWith(str)) {
                while (mid > 0 && dictionary.getWords().get(mid).getWord_target().startsWith(str)
                        && dictionary.getWords().get(mid - 1).getWord_target().startsWith(str))
                    mid--;
                return mid;
            }
            else if (str.compareToIgnoreCase(dictionary.getWords().get(mid).getWord_target()) > 0){
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public int binarySearchEndStartWith(String str) {
        int left = 0;
        int right = dictionary.getSizeWords() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dictionary.getWords().get(mid).getWord_target().startsWith(str)) {
                int size = dictionary.getSizeWords();
                while (mid < size - 1 && dictionary.getWords().get(mid).getWord_target().startsWith(str)
                        && dictionary.getWords().get(mid + 1).getWord_target().startsWith(str))
                    mid++;
                return mid;
            }
            else if (str.compareToIgnoreCase(dictionary.getWords().get(mid).getWord_target()) > 0){
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public void sortWords () {
        Comparator cmt = (Comparator<Word>) (Word w1, Word w2) -> w1.getWord_target().compareToIgnoreCase(w2.getWord_target());
        Collections.sort(dictionary.getWords(), cmt);
    }
    
    public void insertFromFile() {
        try {
            File inFile = new File("dictionaries.txt");
            FileReader fileReader = new FileReader(inFile);
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String str[] = line.split("\t");
                    Word new_word = new Word();
                    new_word.setWord_target(str[0]);
                    new_word.setWord_explain(str[1]);
                    dictionary.getWords().add(new_word);
                }
                sortWords();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWord() {
        Scanner sc = new Scanner(System.in);
        Word new_word = new Word(sc.nextLine(), sc.nextLine());
        dictionary.getWords().add(new_word);
        //dictionaryExportToFile();
    }

    public void deleteWord() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int check = binarySearch(str);
        if (check != -1)
            dictionary.getWords().remove(check);
        else
            System.out.println("Không tìm thấy từ");
        //dictionaryExportToFile();
    }

    public void repairWord() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int check = binarySearch(str);
        if (check != -1) {
            System.out.println("Nhập vào từ tiếng anh");
            dictionary.getWords().get(check).setWord_target(sc.nextLine());
            System.out.println("Nhập vào nghĩa");
            dictionary.getWords().get(check).setWord_explain(sc.nextLine());
        }
        else
            System.out.println("Không tìm thấy từ");
        //dictionaryExportToFile();
    }

    public void dictionaryExportToFile()  {
        try {
            FileWriter writer = new FileWriter("dictionaries.txt");
            for (int i = 0; i < dictionary.getSizeWords(); i++)
                writer.write(dictionary.getWords().get(i).getWord_target() + "\t" + dictionary.getWords().get(i).getWord_explain() + "\n");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int number_word = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < number_word; i++) {
            Word new_word = new Word(sc.nextLine(), sc.nextLine());
            dictionary.getWords().add(new_word);
        }
        sc.close();
    }
}
