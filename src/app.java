import java.io.FileNotFoundException;
import java.io.IOException;

public class app {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.InsertFromFile(dictionary);
//        dictionaryCommandline.showAllWords(dictionary);
//        dictionaryCommandline.dictionarySearcher(dictionary);

        // khi nào muốn lưu giữ liệu của dictionary vào file thì dùng hàm này
        dictionaryManagement.FixOrremove(dictionary,0);// khi dùng hàm này thì khi chạy cần ấn 1 là xóa, 2 là sửa
        dictionaryCommandline.showAllWords(dictionary);

    }
}