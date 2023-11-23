package leo.yang.theifbooks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author leo.yang
 * @date 2021/11/19
 */
public class Book {

    public static List<String> lines;
    public static Integer line = 0;
    public static Properties properties;
    public static File propertiesFile;
    public static File bookFile;
    public static String bookPath;
    public static int lineLength = 50;
    public static String prefix = "//-";

    public static void config(String path, String line){
        try {
            properties.setProperty("path", path);
            properties.setProperty("line", line);
            properties.setProperty("length", String.valueOf(lineLength));
            properties.setProperty("prefix", prefix);
            FileOutputStream out = new FileOutputStream(propertiesFile);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            properties.store(writer, "Thief Book Properties");
            writer.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookFile = null;
        propertiesFile = null;
        lines = null;
        preProcess();
    }

    public static void recordLine(Integer line) {
        properties.setProperty("line", line + "");
        try {
            FileOutputStream out = new FileOutputStream(propertiesFile);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            properties.store(writer, "Thief Book Properties");
            writer.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String nextLine() {
        String check = preProcess();
        if (check != null) {
            return check;
        }
        line++;
        if (line < lines.size()) {
            recordLine(line);
            return lines.get(line);
        } else {
            line = lines.size();
            return "ThiefBook: There is no more behind!";
        }
    }

    public static String preLine() {
        String check = preProcess();
        if (check != null) {
            return check;
        }
        line--;
        if (line < 0) {
            line = -1;
            return "ThiefBook: No more in front";
        } else {
            recordLine(line);
            return lines.get(line);
        }
    }

    public static Map<String, String> info(){
        preProcess();
        if(bookFile != null && line != null){
            return Map.of("path", bookFile.getAbsolutePath(), "line", line.toString());
        }
        return new HashMap<>();
    }

    public static String preProcess() {
        try {
            if (propertiesFile == null) {
                propertiesFile = new File(System.getProperties().getProperty("user.home"), "book.properties");
            }
            if(!propertiesFile.exists()){
                propertiesFile.createNewFile();
                return "//please book path. example: path=D:\\tmhc.txt";
            }
            if (lines == null) {
                properties = new Properties();
                System.out.println(propertiesFile.getAbsolutePath());
                properties.load(new FileInputStream(propertiesFile));
                bookPath = properties.getProperty("path");
                if (bookPath == null) {
                    return "//please book path. example: path=D:\\tmhc.txt";
                }
                line = Integer.parseInt(properties.getProperty("line", "0"));
                lineLength = Integer.parseInt(properties.getProperty("length", "60"));
                prefix = properties.getProperty("prefix", "//- ");
                System.out.println(bookPath + line);
                bookFile = new File(bookPath);
                if (bookFile.exists()) {
                    FileInputStream bookFile = new FileInputStream(Book.bookFile);
                    InputStreamReader inputStreamReader = new InputStreamReader(bookFile, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    lines = bufferedReader.lines().collect(Collectors.toList());
                    bufferedReader.close();
                    inputStreamReader.close();
                    bookFile.close();
                } else {
                    return "//not found book. path=" + bookPath;
                }
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
            return "//error "+ ignore;
        }
        return null;
    }

    public static String loadBook(String bookPath) throws IOException {
        File book = new File(bookPath);
        if(!book.exists()){
            return "//please book path. example: path=" + bookPath;
        }
        FileInputStream bookFile = new FileInputStream(book);
        InputStreamReader inputStreamReader = new InputStreamReader(bookFile, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        lines = bufferedReader.lines().collect(Collectors.toList());
        bufferedReader.close();
        inputStreamReader.close();
        bookFile.close();
        return "//load success, please start with Alt+z ";
    }

}









































//-

































