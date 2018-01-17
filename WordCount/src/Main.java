import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);


        // sonsuz döngü ile her en baştan çalışması için.
        while (true) {

            System.out.println("Press 1 for print");
            System.out.println("Press 2 for search");


            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    printCounter();
                    break;
                case "2":
                    System.out.println("Write what do you want to search");
                    findWord();
                    break;
            }

        }
    }

    private static void printCounter() throws IOException {
        List<String> text = Files.readAllLines(Paths.get("sample.txt"), StandardCharsets.UTF_8);
        HashMap<String, List<Integer>> hashMap = new HashMap<>();

        for (String aText : text) {
            String[] line = aText.split(" ");

            for (int i = 0; i < line.length; i++) {
                hashMap.computeIfAbsent(line[i].toLowerCase().replace('.', ' '), c -> new ArrayList<>()).add(i);
            }

        }

        for (Object word : hashMap.keySet()) {
            System.out.println(word +  " - Repeat: " + hashMap.get(word).size());
        }
    }


    /**
     *text dosyasındaki her şeyi önce lower case yaptım. Böylece ararken sorun olmayacak.
     *
     */
    public static void replace(List<String> strings)
    {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext())
        {
            iterator.set(iterator.next().toLowerCase(Locale.ENGLISH));
        }
    }



    private static void findWord() throws IOException {
        int lineCount = -1;
        int count = 0;

        List<Integer> lines = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        HashMap<String, List<Integer>> hashMapScan = new HashMap<>();

        String input = scanner.nextLine();

        List<String> textScan = Files.readAllLines(Paths.get("sample.txt"), StandardCharsets.UTF_8);
        replace(textScan);


        for (int j = 0; j < textScan.size(); j++) {
            lineCount++;

            String[] line = textScan.get(j).split(" ");


            /**
             * noktaları değiştiriyor.
             */

            for (int i = 0; i < line.length; i++) {
                hashMapScan.computeIfAbsent(line[i].toLowerCase(Locale.ENGLISH).replace('.', ' '), c -> new ArrayList<>()).add(i);
            }


            /**
             * eğer bulursa line count u arttırıyor ve line count listesine ekliyor.
             */
            for (int i = 0; i <line.length ; i++) {

                if (input.toLowerCase(Locale.ENGLISH).equals(line[i])) {
                    lines.add(lineCount);
                    System.out.println(line[i] + " - Repeat: " + hashMapScan.get(line[i]).size() + " at line: " + lineCount);
                    count++;
                    break;
                }
            }

        }
        if (count == 0) {
            System.out.println("Word doesn't exist");
        }
     }
 }

