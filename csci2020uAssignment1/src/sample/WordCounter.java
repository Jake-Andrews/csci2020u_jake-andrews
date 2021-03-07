package sample;
import java.io.*;
import java.util.*;
import java.lang.Math.*;

/**
 * WordCounter class associates a file and two maps.
 * Parses through the file, counting all files contained
 * Has functions to put all valid words in the file into a map
 * and count how many files the word appears in
 * Has appropriate getters
 * <p>
 * This class parses through the entire selected directory,
 * regardless of the file type.
 * that draw the image will incrementally paint on the screen.
 * @param   file  The file or directory to start from
 * @return  null
 */

public class WordCounter{

    private File inputFileName;
    private Map<String, Double> wordCounts;
    //used to make sure a word is only counted once per file
    private Map<String, Double> wordCountPerFile;
    private double fileCounter = 0.0;

    public WordCounter(File input){
        this.wordCounts = new TreeMap<>();
        this.inputFileName = input;
    }

    public void setInputFileName(File inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void parseFile(File file) throws IOException{
        //System.out.println("Starting parsing the file:" + file.getAbsolutePath());
        if(file.isDirectory()){
            //parse each file inside the directory
            File[] content = file.listFiles();
            assert content != null;
            for(File current: content){
                //create a new TreeMap for every new file, so a word is only counted once per file
                //probably an easier way to do this
                fileCounter++;  //total file count
                wordCountPerFile = new TreeMap<>();
                parseFile(current);
            }
        }else{
            Scanner scanner = new Scanner(file);
            // scanning token by token
            //
            while (scanner.hasNext()){
                String  token = scanner.next();
                if (isValidWord(token)){
                    countWord(token, file);
                }
            }
            scanner.close();
        }

    }

    private boolean isValidWord(String word){
        String allLetters = "^[a-zA-Z]+$";
        // returns true if the word is composed by only letters otherwise returns false;
        return word.matches(allLetters);

    }

    private void countWord(String word, File file){
        if(wordCounts.containsKey(word) && !(wordCountPerFile.containsKey(word))){
            double previous = wordCounts.get(word);
            wordCountPerFile.put(word, 1.0);
            double obj = wordCounts.remove(word);
            obj = obj + 1.0;
            //System.out.println(obj);
            wordCounts.put(word, obj);

        }else{
            wordCounts.put(word, 1.0);
        }
    }

    public void runWordCounter() {
        File dataDir = this.inputFileName;
        //File outFile = new File("output.txt");


        try{
            parseFile(dataDir);
            //outputWordCount(0, outFile);
        }catch(FileNotFoundException e){
            System.err.println("Invalid input dir: " + dataDir.getAbsolutePath());
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public Map<String, Double> getWordCounts() {
        return wordCounts;
    }
    public double getFileCounter(){return fileCounter;}

    //main method
}