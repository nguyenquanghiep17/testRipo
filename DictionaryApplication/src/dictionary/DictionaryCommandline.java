package dictionary;

import java.io.IOException;
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
        Scanner sc = new Scanner(System.in);
        int command = 0;
        while (command != 7) {
            System.out.println("1: Thêm từ\n2: Xóa từ\n3: Sửa từ\n"
                    + "4: Tìm 1 từ\n5: Tìm các từ\n6: Hiển thị tất cả\n"
                    + "7: Thoát");
            command = sc.nextInt();
            while (command < 1 || command > 7) {
                System.out.println("Nhập lại");
                command = sc.nextInt();
            }
            switch (command) {
                case 1:
                    dictionaryManagement.addWord();
                    break;
                case 2:
                    dictionaryManagement.deleteWord();
                    break;
                case 3:
                    dictionaryManagement.repairWord();
                    break;
                case 4:
                    dictionaryManagement.dictionaryLookup();
                    break;
                case 5:
                    dictionarySearcher();
                    break;
                case 6:
                    showAllWords();
                    break;
                case 7:
                    System.exit(0);
            }
        }
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
        if (fisrt == -1) {
            System.out.println("Không tìm thấy dữ liệu phù hợp");
        } else {
            for (int i = fisrt; i <= end; i++) {
                System.out.print(dictionaryManagement.getDictionary().getWords().get(i).getWord_target());
                if (i != end) {
                    System.out.print(", ");
                } else {
                    System.out.print(".");
                }
            }
        }
        System.out.println();
    }

    public void showAllWords() {
        System.out.printf("%-10s%-30s%s", "No", "English", "Vietnamese\n");
        for (int i = 0; i < dictionaryManagement.getDictionary().getSizeWords(); i++) {
            System.out.printf("%-10s%-30s%s\n", i + 1,
                    dictionaryManagement.getDictionary().getWords().get(i).getWord_target(),
                    dictionaryManagement.getDictionary().getWords().get(i).getWord_explain());
        }
    }

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập 1: Phiên bản Basic\nNhập 2: Phiên bản Advanced");
        int command = sc.nextInt();
        while (command < 1 || command > 2) {
            System.out.println("Nhập lại");
            command = sc.nextInt();
        }
        if (command == 1) {
            dictionaryCommandline.dictionaryBasic();
        } else {
            dictionaryCommandline.dictionaryAdvanced();
        }
    }
}
