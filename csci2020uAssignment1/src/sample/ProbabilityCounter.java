package sample;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * ProbabilityCounter class associates a file and two maps.
 * Parses through the file, and uses each probability per word
 * to calculate probability total email is spam
 * <p>
 * This class parses through the given file,
 * updates spam probability and file array after each
 * valid word.
 * @param   wordSpamProbabilities  Treemap of words read from file and how many files appeared in
 * @param   filename              The file or directory to start from
 * @return  null
 */

public class ProbabilityCounter {
    public double[] probabilitiesPerFile;
    public String []fileNames;
    private String inputFileName;
    private Map<String, Double> wordSpamProbabilities;
    public int filesCounted = 0;
    private double spamProbability = 0.0;


    public ProbabilityCounter(Map<String, Double> wordSpamProbabilities, String fileName){
        this.wordSpamProbabilities = wordSpamProbabilities;
        this.inputFileName = fileName;

        //initilizing arrays based on number of files in directory
        //could of used arraylist, figured this was better
        //System.out.println(fileName);

        //number of files in the directory given.
        File temp = new File(fileName);
        File [] files = temp.listFiles();

        //int fileCount = (currentDirectory.list()).length;
        this.probabilitiesPerFile = new double[files.length];
        this.fileNames = new String[files.length];
    }

    public void parseFile(File file) throws IOException {
        if(file.isDirectory()){
            //parse each file inside the directory
            File[] content = file.listFiles();
            assert content != null;
            for(File current: content){
                spamProbability = 0.0; //reset counter after each file
                filesCounted++;  //total file count
                parseFile(current);
            }
        }else{
            Scanner scanner = new Scanner(file);
            // scanning token by token
            while (scanner.hasNext()){
                String  token = scanner.next();
                if (isValidWord(token)){
                    calculatePercent(token);
                }
            }
            //about to move onto next file, therefore update array's
            updateArrays(file);
            scanner.close();
        }
    }
    //Is called once a file has no more words.
    //Each spot in the array contains a files spam probability, and name.
    public void updateArrays(File currentFile){
        this.fileNames[filesCounted-1] = currentFile.getName();
        this.probabilitiesPerFile[filesCounted-1] = (double) (1.0 / (1.0 + Math.pow(Math.E, spamProbability)));;
    }

    private boolean isValidWord(String word){
        String allLetters = "^[a-zA-Z]+$";
        // returns true if the word is composed by only letters otherwise returns false;
        return word.matches(allLetters);
    }

    public void runWordCounter() {
        //directory given
        File dataDir = new File(this.inputFileName);

        try{
            parseFile(dataDir);
        }catch(FileNotFoundException e){
            System.err.println("Invalid input dir: " + dataDir.getAbsolutePath());
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void calculatePercent(String word){
        if (wordSpamProbabilities.containsKey(word)) {
            double prSW = wordSpamProbabilities.get(word);
            //avoids the log(0) and log(1-1)
            if (prSW == 1.0) {
                prSW = 0.9999999999;
            }
            else if (prSW == 0.0) {
                prSW = 0.0000000001;
            }
            double v = Math.log((1.0 - prSW)) - Math.log(prSW);
            spamProbability += v;


        }
    }
}
