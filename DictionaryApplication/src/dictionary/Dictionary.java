package dictionary;

/**
 *
 * @author Nhóm không tên
 * @version Commandline
 */
import java.util.ArrayList;

/**
 *
 * @author Nhóm không tên
 * @version Commandline
 */
public class Dictionary {

    private ArrayList<Word> words = new ArrayList<>();

    public ArrayList<Word> getWords() {
        return this.words;
    }

    public int getSizeWords() {
        return words.size();
    }
}
