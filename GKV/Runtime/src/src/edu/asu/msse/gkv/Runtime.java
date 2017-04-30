package runtime;
import javax.swing.text.html.parser.Parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Krishnakantha Jayashree Chandrashekhar on 4/21/2017.
 */
public class OSLRunTime {

    public static int lineTracker = 0;
    public static int[] parameters = {0,0};
    public static ArrayList <String>  instructionSet = new ArrayList < String >();
    public static ArrayList<String> loopconditionSet = new ArrayList<>();
    public static int loopconditionnumber = 0;
    public static Stack <OSLMap> SymbTable = new Stack <OSLMap>();
    public static Stack<String> regStack = new Stack<String>();
    public static HashMap<String,String> lookUp = new HashMap();
    public static String[] registers = new String[10];

    public static void main(String[] args) {
        readFile("OSLIntermediate.oslc");
        try {
            for(lineTracker = 0 ; lineTracker < instructionSet.size(); lineTracker++) {

            if(instructionSet.get(lineTracker).contains("CALL")){
                int lineTracker4 = lineTracker;
                ++lineTracker4;
                System.out.println("Values are" + instructionSet.get(lineTracker4)) ;
                char[] array = instructionSet.get(lineTracker4).toCharArray();


                break;

            }
             if(instructionSet.get(lineTracker).compareToIgnoreCase("LOOPCONDITIONEND") == 0 && regStack.peek().toString().equals("No")){
                 int lineTracker3 = lineTracker;
                 while(instructionSet.get(lineTracker3).compareToIgnoreCase("JUMP LOOPCONDITIONSTART")!= 0){
                     lineTracker3++;
                 }
                 lineTracker = ++lineTracker3;

             }
            if(instructionSet.get(lineTracker).compareToIgnoreCase("LOOPCONDITIONSTART") == 0){
                 loopconditionnumber = lineTracker;

             }
            if(instructionSet.get(lineTracker).compareToIgnoreCase("LOOPCONDITIONEND") == 0 && regStack.peek().toString().equals("Yes")){
                 int linetracker3 = lineTracker;
                 ++linetracker3;
                 while (instructionSet.get(linetracker3).compareToIgnoreCase("JUMP LOOPCONDITIONSTART")!= 0){
                     List<String> looptokens = tokenize(instructionSet.get(linetracker3));
                     OSLParserTokens.processTokens(looptokens);
                     linetracker3++;
                 }
                 lineTracker = loopconditionnumber;

             }


                if(instructionSet.get(lineTracker).compareToIgnoreCase("IFTRUE") == 0 &&  regStack.peek().toString().equals("No")){
                    int lineTracker1;
                    for(lineTracker1=lineTracker; lineTracker1 < instructionSet.size(); lineTracker1++) {
                        if(instructionSet.get(lineTracker1).compareToIgnoreCase("IFTRUEEND") == 0){
                            lineTracker1++;
                            break;
                        }
                    }
                    lineTracker = lineTracker1;
                }

                if(instructionSet.get(lineTracker).compareToIgnoreCase("ELSETRUE") == 0 &&  regStack.peek().toString().equals("Yes")){
                    int linetracker2;
                    for(linetracker2=lineTracker; linetracker2 < instructionSet.size(); linetracker2++) {
                        if(instructionSet.get(linetracker2).compareToIgnoreCase("ELSETRUEEND") == 0){

                            linetracker2++;
                            break;
                        }

                    }
                    lineTracker = linetracker2;
                }

                List<String> tokens = tokenize(instructionSet.get(lineTracker));
                if(instructionSet.get(lineTracker).compareToIgnoreCase("PROGRAMEND") == 0) {
                    OSLParserTokens.processTokens(tokens);
                    break;
                }
               OSLParserTokens.processTokens(tokens);
            }
        }
        catch(Exception e) {
            System.out.println("Error Processing the File: "+e);
        }
    }

    public static List<String> tokenize(String str) {
        List<String> tokens = new ArrayList<String>();
        String regex = "(\"[^\"]*\")|(\\S+)";
        Matcher m = Pattern.compile(regex).matcher(str);

        while (m.find()) {
            if (m.group(1) != null)
               tokens.add( m.group(1));
            else
               tokens.add( m.group(2));
        }
        return tokens;
    }

    private static void readFile(String intermediateFile) {
        try	{
            BufferedReader cursor = new BufferedReader(new FileReader(intermediateFile));

            String individualInstruction;
            while ((individualInstruction = cursor.readLine()) != null) {
                    instructionSet.add(individualInstruction);
                System.out.println("individualInstruction"+individualInstruction);
            }
            cursor.close ();
        }
        catch (Exception e) {
            System.err.format ("Error reading intermediate file '%s'.", intermediateFile);
            e.printStackTrace ();
        }
    }
}
