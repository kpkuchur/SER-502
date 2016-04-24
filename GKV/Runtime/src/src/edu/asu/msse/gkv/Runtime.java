package src.edu.asu.msse.gkv;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Runtime {
	public static boolean checkNum(String s){
		boolean res = false;
		try{
			int i = Integer.parseInt(s);
			res =true;
		}catch(Exception e){
//			e.printStackTrace();
		}
		return res;
	}
    public static void main(String [] args) {
    	Stack<Integer> stack = new Stack<Integer>();
    	LinkedList<Map<String, String>> list = new LinkedList<Map<String, String>>();
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	int res =0;
        // The name of the file to open.
//        String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/add.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/division.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/multiplication.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/substraction.igkv";
    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/function.igkv";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
            	if(line.startsWith("CALL")){
                	String funcName = line.split(" ")[1];
                	String parameter1 = line.split(" ")[2].split(",")[0];
                	String parameter2 = line.split(" ")[2].split(",")[1];
//                	System.out.println(funcName);
//                	System.out.println("value of : "+parameter1+" is : "+map.get(parameter1));
//                	System.out.println("value of : "+parameter2+" is : "+map.get(parameter2));
                	FileReader functionFileReader = 
                            new FileReader(fileName);
                	BufferedReader bufferedReaderforFunctionCall = 
                            new BufferedReader(functionFileReader);
                	String functionCallingLine = null; 
                	while((functionCallingLine = bufferedReaderforFunctionCall.readLine()) != null){
//                		System.out.println("Calling function "+functionCallingLine);
                		if(functionCallingLine.startsWith("FUNCSTART")){
                        	Map<String, String> funcMap = new HashMap<String, String>();
                        	Stack<String> functionvariableStack = new Stack<String>();
                        	if(funcName.equals(functionCallingLine.split(" ")[1])){
	                        	funcMap.put("functionName", functionCallingLine.split(" ")[1]);
	                        	funcMap.put("functionReturnType", functionCallingLine.split(" ")[2]);
	                        	String nextfunctionCallingLine = null;
	                        	nextfunctionCallingLine = bufferedReaderforFunctionCall.readLine();
	                        	while((nextfunctionCallingLine = bufferedReaderforFunctionCall.readLine()) != null){
//	                        		System.out.println("Inside function :"+nextfunctionCallingLine);
	                        		if(nextfunctionCallingLine.startsWith("DECLINT")){
	                                	map.put(nextfunctionCallingLine.split(" ")[1], 0);
	                                }else if(nextfunctionCallingLine.startsWith("PUSH")){
//	                                	System.out.println("value of : "+ nextfunctionCallingLine.split(" ")[1]+" is : "+funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                	if(funcMap.get(nextfunctionCallingLine.split(" ")[1]) == null){
	                                		functionvariableStack.push(nextfunctionCallingLine.split(" ")[1]);
	                                	}else{
//	                                		System.out.println("Value put in stack "+funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                		functionvariableStack.push(funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                	}
	                                }else if(nextfunctionCallingLine.startsWith("SET")){
	                                	funcMap.put(nextfunctionCallingLine.split(" ")[1],functionvariableStack.pop());
	                                }else if(nextfunctionCallingLine.startsWith("RET")){
	                                	res = Integer.parseInt(functionvariableStack.pop());
	                                }else if(nextfunctionCallingLine.startsWith("ADD")){
	                                	String p2 = functionvariableStack.pop();
//	                                	System.out.println("p2 : "+p2);
	    
	                                	String p1 = functionvariableStack.pop();
//	                                	System.out.println("p1 : "+p1);
//	                                	System.out.println("p2 value :"+map.get(parameter2).toString());
//	                                	System.out.println("p1 value :"+map.get(parameter1).toString());
	                                	funcMap.put(p2, map.get(parameter2).toString());
	                                	funcMap.put(p1, map.get(parameter1).toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
//	                                	System.out.println("value of : "+p1+" is : "+v1);
//	                                	System.out.println("value of : "+p2+" is : "+v2);
	                                	int r = v1 + v2;
	                                	functionvariableStack.push(Integer.toString(r));
	                                } else if(nextfunctionCallingLine.startsWith("SUB")){
	                                	String p2 = functionvariableStack.pop();
//	                                	System.out.println("p2 : "+p2);
	    
	                                	String p1 = functionvariableStack.pop();
//	                                	System.out.println("p1 : "+p1);
//	                                	System.out.println("p2 value :"+map.get(parameter2).toString());
//	                                	System.out.println("p1 value :"+map.get(parameter1).toString());
	                                	funcMap.put(p2, map.get(parameter2).toString());
	                                	funcMap.put(p1, map.get(parameter1).toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
//	                                	System.out.println("value of : "+p1+" is : "+v1);
//	                                	System.out.println("value of : "+p2+" is : "+v2);
	                                	int r = v2 - v1;
	                                	functionvariableStack.push(Integer.toString(r));
	                                }else if(nextfunctionCallingLine.startsWith("MULT")){
	                                	String p2 = functionvariableStack.pop();
//	                                	System.out.println("p2 : "+p2);
	    
	                                	String p1 = functionvariableStack.pop();
//	                                	System.out.println("p1 : "+p1);
//	                                	System.out.println("p2 value :"+map.get(parameter2).toString());
//	                                	System.out.println("p1 value :"+map.get(parameter1).toString());
	                                	funcMap.put(p2, map.get(parameter2).toString());
	                                	funcMap.put(p1, map.get(parameter1).toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
//	                                	System.out.println("value of : "+p1+" is : "+v1);
//	                                	System.out.println("value of : "+p2+" is : "+v2);
	                                	int r = v2 * v1;
	                                	functionvariableStack.push(Integer.toString(r));
	                                }else if(nextfunctionCallingLine.startsWith("DIV")){
	                                	String p2 = functionvariableStack.pop();
//	                                	System.out.println("p2 : "+p2);
	    
	                                	String p1 = functionvariableStack.pop();
//	                                	System.out.println("p1 : "+p1);
//	                                	System.out.println("p2 value :"+map.get(parameter2).toString());
//	                                	System.out.println("p1 value :"+map.get(parameter1).toString());
	                                	funcMap.put(p2, map.get(parameter2).toString());
	                                	funcMap.put(p1, map.get(parameter1).toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
//	                                	System.out.println("value of : "+p1+" is : "+v1);
//	                                	System.out.println("value of : "+p2+" is : "+v2);
	                                	int r = v1/v2;
//	                                	int r = 6/3;
//	                                	System.out.println("Result : "+r);
	                                	functionvariableStack.push(Integer.toString(r));
	                                }  
	                        		else if(nextfunctionCallingLine.equals("FUNCEND")){
	                        			break;
	                        		}			
	                        	}
                        	}else{
                        		continue;
                        	}
                        }
                	}
                	
                	
//                	int firstValue = stack.pop();
//                	int secondValue = stack.pop();
//                	res = secondValue - firstValue;
//                	stack.push(res);
                }else if(line.startsWith("DECLINT")){
                	map.put(line.split(" ")[1], 0);
                }else if(line.startsWith("PUSH")){
                	boolean result = checkNum(line.split(" ")[1]);
//                	System.out.println("Parameter : "+line.split(" ")[1]+" is Integer? "+result);
                	if(result == false){
                		stack.push(map.get(line.split(" ")[1]));
                	}else{
                		stack.push(Integer.parseInt(line.split(" ")[1]));
                	}
                }else if(line.startsWith("SET")){
                	map.put(line.split(" ")[1], stack.pop()); 
//                	System.out.println("Key : "+ line.split(" ")[1]+" Value : "+map.get(line.split(" ")[1]));
                }else if(line.startsWith("ADD")){
                	int firstValue = stack.pop();
                	int secondValue = stack.pop();
                	res = firstValue + secondValue;
                	stack.push(res);
                }else if(line.startsWith("DIV")){
                	int firstValue = stack.pop();
                	int secondValue = stack.pop();
                	if(firstValue == 0){
                		res = Integer.MAX_VALUE;
                	}else{
                		res = secondValue/firstValue;
                	}
                	stack.push(res);
                }
                else if(line.startsWith("MULT")){
                	int firstValue = stack.pop();
                	int secondValue = stack.pop();
                	res = secondValue * firstValue;
                	stack.push(res);
                }else if(line.startsWith("SUB")){
                	int firstValue = stack.pop();
                	int secondValue = stack.pop();
                	res = secondValue - firstValue;
                	stack.push(res);
                }else{
                	
                }
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        System.out.println("Final Result : "+res);
    }
}
