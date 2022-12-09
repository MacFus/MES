package loadfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public abstract class ReadFile {
    static ArrayList<String> lines = new ArrayList<>();

    public static String[] readFileContent() {
        BufferedReader reader;
        String lineWithoutComma;
        String lineNoDuplicatedWhitespaces;
        try {
            reader = new BufferedReader(new FileReader(
                    "D:/Repositories/Java/mes_1/files/Test1_4_4.txt"));
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                // read next line

                if (line != null) {
                    lineNoDuplicatedWhitespaces = line.replaceAll("\\s+", " ");
                    if (!line.contains(",")) {
                        lines.add(lineNoDuplicatedWhitespaces);
                    } else {
                        lineWithoutComma = lineNoDuplicatedWhitespaces.replace(",", "");
                        lines.add(lineWithoutComma);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Printing and display the elements in ArrayList
        String[] content = lines.toArray(new String[0]);
        //for (String line : content) System.out.println(line);
        return content;
    }



    public static void main(String[] args) {
        ReadFile.readFileContent();
    }
}
