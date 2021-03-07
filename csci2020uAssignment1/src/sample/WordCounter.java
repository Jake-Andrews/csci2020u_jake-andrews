package sample;
import java.io.*;
import java.util.*;


public class WordCounter{

    private File inputFileName;
    private Map<String, Float> wordCounts;
    //used to make sure a word is only counted once per file
    private Map<String, Float> wordCountPerFile;
    private float fileCounter = 0;

    public WordCounter(File input){
        this.wordCounts = new TreeMap<>();
        this.inputFileName = input;
    }

    public void setInputFileName(File inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void parseFile(File file) throws IOException{
        System.out.println("Starting parsing the file:" + file.getAbsolutePath());
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
            float previous = wordCounts.get(word);
            wordCountPerFile.put(word, 1.0f);
            wordCounts.put(word, previous+1);
        }else{
            wordCounts.put(word, 1.0f);
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
                float count = wordCounts.get(key);
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
            outputWordCount(0, outFile);
        }catch(FileNotFoundException e){
            System.err.println("Invalid input dir: " + dataDir.getAbsolutePath());
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Map<String, Float> getWordCounts() {
        return wordCounts;
    }
    public float getFileCounter(){return fileCounter;}

    //main method
}