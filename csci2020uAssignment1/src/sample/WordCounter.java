package sample;
import java.io.*;
import java.util.*;


public class WordCounter{

    private File inputFileName;
    private Map<String, Integer> wordCounts;
    //used to make sure a word is only counted once per file
    private Map<String, Integer> wordCountPerFile;

    public WordCounter(File input){
        wordCounts = new TreeMap<>();
        this.inputFileName = input;
    }
    /*
    public void setInputFileName(File input) {
        inputFileName = input;
    }
    */
    public void parseFile(File file) throws IOException{
        System.out.println("Starting parsing the file:" + file.getAbsolutePath());

        if(file.isDirectory()){
            //parse each file inside the directory
            File[] content = file.listFiles();
            assert content != null;
            for(File current: content){
                //create a new TreeMap for every new file, so a word is only counted once per file
                //probably an easier way to do this
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
            int previous = wordCounts.get(word);
            wordCountPerFile.put(word, 1);
            wordCounts.put(word, previous+1);
        }else{
            wordCounts.put(word, 1);
        }
    }

    public void outputWordCount(int minCount, File output) throws IOException{
        System.out.println("Saving word counts to file:" + output.getAbsolutePath());
        System.out.println("Total words:" + wordCounts.keySet().size());

        //if (!output.exists()){
        output.createNewFile();
        if (output.canWrite()){
            PrintWriter fileOutput = new PrintWriter(output);

            Set<String> keys = wordCounts.keySet();
            Iterator<String> keyIterator = keys.iterator();

            while(keyIterator.hasNext()){
                String key = keyIterator.next();
                int count = wordCounts.get(key);
                // testing minimum number of occurances
                if(count>=minCount){
                    fileOutput.println(key + ": " + count);
                }
            }

            fileOutput.close();
        }
        //}else{
        //		System.out.println("Error: the output file already exists: " + output.getAbsolutePath());
        //	}

    }

    public void runWordCounter() {
        File dataDir = this.inputFileName;
        File outFile = new File("output.txt");

        System.out.println("Hello");
        try{
            parseFile(dataDir);
            outputWordCount(2, outFile);
        }catch(FileNotFoundException e){
            System.err.println("Invalid input dir: " + dataDir.getAbsolutePath());
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getWordCounts() {return wordCounts;}
    //main method
}