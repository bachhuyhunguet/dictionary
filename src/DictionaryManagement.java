import java.io.*;
import java.lang.reflect.GenericDeclaration;
import java.util.Scanner;


public class DictionaryManagement {
    public static String url = "E:\\dictionary\\dictionary\\src\\input\\dictionaries.txt";
    public static char wall = '_';

    private Scanner scanner = new Scanner(System.in);

    public void InserFromCommandLine(Dictionary dictionary )
    {
        Word word = new Word();
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap tu can them: ");
        System.out.println("Tieng anh: ");
        word.setWord_target(scanner.nextLine());
        System.out.println("Tieng viet: ");
        word.setWord_explain(scanner.nextLine());
        dictionary.add(word);
    }

    public void InsertFromFile(Dictionary dictionary) throws FileNotFoundException {
        File input = new File(this.url);
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String m = scanner.nextLine();
            Word new_word = new Word();
            int vt = 0; // cai này lưu vị trí dấu cách trước nghĩa tiếng việt
            for (int i = 0; i < m.length(); i++) {
                if (m.charAt(i) == this.wall) {
                    new_word.setWord_target(m.substring(0,i)); // gán từ tiếng anh cho Word_target
                    vt = i + 1;
                    break;
                }
            }

            int m_size = m.length();
            new_word.setWord_explain(m.substring(vt, m_size ));// gán nghĩa tiếng việt

            dictionary.add(new_word);
         }

    }

    public void dictionaryLookup( Dictionary dictionary ) throws FileNotFoundException {
        Scanner cin = new Scanner(System.in);
        String input;
        input = cin.nextLine();
        System.out.println(input);
        Dictionary temp = new Dictionary();
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

        for(int i = 0; i < dictionary.list_word.size(); i++) {
            String english = dictionary.get(i).getWord_target();
            String vietnam  = dictionary.get(i).getWord_explain();
            if(input.equals(english) || input.equals(vietnam)) {
                temp.add(dictionary.get(i));
                dictionaryCommandline.showAllWords(temp);
                return ;
            }
        }
        System.out.println("Word not found");
    }

    public void dictionaryExportToFile(Dictionary dictionary) throws IOException {
        FileWriter writer = new FileWriter(this.url, false);
        for(int i = 0; i < dictionary.list_word.size(); i++) {
            String temp = dictionary.get(i).getWord_target() + this.wall + dictionary.get(i).getWord_explain() + '\n';
            writer.write(temp);
        }

        writer.close();
    }

    public void FixOrremove(Dictionary dictionary, int i) {
        // sau này khi có đồ họa thì hiên chữ nào đó thì return ra giá trị của nó trong list

        int a = scanner.nextInt();
        if (a == 1) { // a = 1 thì xóa đại diện cho button xóa sau này
            dictionary.remove(i);
        }
        if (a == 2) {
            Word new_word = new Word();
            System.out.println("Nhap tu moi theo anh, viet");
            // khởi tạo từ mới
            Scanner scanner = new Scanner(System.in);
            String english = scanner.nextLine();
            new_word.setWord_target(english); // gan tieng anh cho new_word
            String vietnamese = scanner.nextLine();
            new_word.setWord_explain(vietnamese); // gan tieng viet cho new_word
            scanner.close();
            dictionary.set(new_word,i); // thay doi gia tri cua dictionary[i]
        }
        //dictionaryExportToFile(dictionary);
    }

}