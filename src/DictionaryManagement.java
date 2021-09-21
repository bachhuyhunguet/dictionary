import java.lang.reflect.GenericDeclaration;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DictionaryManagement {
    // truyen dictionary cu va return ra nham luu tru
    // ham nay toi de la class vi de la void thi khong biet luu lai kieu gi
    public Dictionary InserFromCommandLine(Dictionary dictionary )
    {
        Word word = new Word();
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap tu can them: ");
        System.out.println("Tieng anh: ");
        word.setWord_target(scanner.nextLine());
        System.out.println("Tieng viet: ");
        word.setWord_explain(scanner.nextLine());
        dictionary.add(word);
        return dictionary;
    }

    public Dictionary InsertFromFile(Dictionary dictionary) throws FileNotFoundException {
        File input = new File("C:\\Users\\nguye\\OneDrive\\Desktop\\dictionary\\src\\input\\dictionaries.txt");
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String m = scanner.nextLine();
            Word new_word = new Word();
            int vt = 0; // cai này lưu vị trí dấu cách trước nghĩa tiếng việt
            for (int i = 0; i < m.length(); i++) {
                if (m.charAt(i) == '_') {
                    new_word.setWord_target(m.substring(0,i)); // gán từ tiếng anh cho Word_target
                    vt = i + 1;
                    break;
                }
            }

            int m_size = m.length();
            new_word.setWord_explain(m.substring(vt, m_size ));// gán nghĩa tiếng việt

            dictionary.add(new_word);
         }

        return dictionary;
    }

    void dictionaryLookup( Dictionary dictionary ) throws FileNotFoundException {
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

}