package dictionary;
import java.util.Scanner;
/**
 * 
 * @author Nhóm không tên
 * @version Commandline
 */
public class DictionaryCommandline {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    
    public DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }
    
    public void dictionaryAdvanced() {
        dictionaryManagement.insertFromFile();
        System.out.println("Thêm : Nhập vào từ cần thêm");
        dictionaryManagement.addWord();
        System.out.println("Sửa:");
        dictionaryManagement.repairWord();
        System.out.println("Xóa: Nhập vào từ cần xóa");
        dictionaryManagement.deleteWord();
        dictionaryManagement.sortWords();
        dictionaryManagement.dictionaryExportToFile();
        showAllWords();
        System.out.println("Tìm kiếm: Nhập vào từ cần tìm");
        dictionaryManagement.dictionaryLookup();
        System.out.println("Searcher:");
        dictionarySearcher();
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }
    
    public void dictionarySearcher() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int fisrt = dictionaryManagement.binarySearchFirstStartWith(str);
        int end = dictionaryManagement.binarySearchEndStartWith(str);
        if (fisrt == -1)
            System.out.println("Không tìm thấy dữ liệu phù hợp");
        else {
            for (int i  = fisrt; i <= end; i++) {
                System.out.print(dictionaryManagement.getDictionary().getWords().get(i).getWord_target());
                if ( i != end)
                    System.out.print(", ");
                else
                    System.out.print(".");
            }
        }
        System.out.println();
    }

    public void showAllWords() {
        System.out.printf("%-10s%-30s%s", "No", "English", "Vietnamese\n");
        for (int i = 0; i < dictionaryManagement.getDictionary().getSizeWords(); i++) {
            System.out.printf("%-10s%-30s%s\n", i + 1, dictionaryManagement.getDictionary().getWords().get(i).getWord_target(),
                    dictionaryManagement.getDictionary().getWords().get(i).getWord_explain());
        }
    }

    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        //dictionaryCommandline.dictionaryBasic();
        dictionaryCommandline.dictionaryAdvanced();
        dictionaryCommandline.dictionarySearcher();
    }
}
