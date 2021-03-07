README.md
Project Information
    This project utilizes TreeMaps in java to detect if an email is spam or not. The program reads files from a selected directory and analyizes each word in each file 
as part of the training phase. The program maps each unique word, paired with how many files contain that word into two maps, one for ham files and one for spam files. 
With these maps the program uses probability laws to calculate the probability if a file is spam, or ham, given a word, for each word. From this calculation, the 
probability of the file being spam given the file is calculated, done so by logarthimic and exponential equations to  normalize the data. The process is tested with test
data, which is read through, and spam probability is calculated. Output is a table containing all the files tested, aswell as the true class and the spam probability.
Accuracy and percision of the calculations were included aswell.

                    [INSERT SCREENSHOT OF RUNNING APPLICATION]


Improvements
    Improvements were made to this process ...


How-to-Run:
    Clone from https://github.com/Jake-Andrews/csci2020u_jake-andrews.git, and navigate to the csci2020uAssignment1 folder. 
    Open the project in IntelliJ and navigate to Main.java, from here press run and the program will ask you to select a directory.
    Select 'Data' from csci2020uAssignment1/src, then the program will run and output a small window

References/Resources/Links
    