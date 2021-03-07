package sample;

import java.text.DecimalFormat;

/**
 * Testfile was a provided class to structure our output list
 * Has appropriate getters and setters
 * <p>
 * This class instantiates the testfile concept, pairing a filename,
 * spamProbability, and actual class to one file object
 * @param   filename  The file or directory to start from
 * @param   spamProbability  double, calculated probability email is spam
 * @param   actualClass  The true ham or spam value of the file
 * @return  filename
 * @return spamProbability
 */

public class TestFile {
    private String filename;
    private double spamProbability;
    private String actualClass;
    public TestFile(String filename, double spamProbability, String actualClass) {
        this.filename = filename;
        this.spamProbability = spamProbability;
        this.actualClass = actualClass;
    }
    public String getFilename(){ return this.filename; }
    public double getSpamProbability(){ return this.spamProbability; }
    public String getSpamProbRounded(){
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(this.spamProbability);
    }
    public String getActualClass(){return this.actualClass;}
    public void setFilename(String value) { this.filename = value; }
    public void setSpamProbability(double val){this.spamProbability = val; }
    public void setActualClass(String value){this.actualClass=value; }
}