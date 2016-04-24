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
	public static boolean checkBool(String s){
		boolean res = false;
		try{
			if(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false"))
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
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/HelloWorld.igkv";
//      String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/add.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/division.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/multiplication.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/substraction.igkv";
    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/function.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/equalTo.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/greaterThan.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/greaterThanOrEqualTo.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/lessThan.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/lessThanOrEqualTo.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/notEqualTo.igkv";
//    	String fileName = "D:/SER-502/GKV/Sample-Programs/Intermediate-Code/expressions.igkv";
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
                	int count = Integer.parseInt(line.split(" ")[2]);
                	for(int i=count; i>0; i--){
                		map.put("parameter"+i, stack.pop());
                	}
//                	String parameter1 = line.split(" ")[2].split(",")[0];
//                	String parameter2 = line.split(" ")[2].split(",")[1];
//                	System.out.println(funcName);
//                	System.out.println("value of : parameter1 is : "+map.get("parameter1"));
//                	System.out.println("value of : parameter2 is : "+map.get("parameter2"));
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
	                        			funcMap.put(nextfunctionCallingLine.split(" ")[1], "0");
	                                }else if(nextfunctionCallingLine.startsWith("PUSH")){
//	                                	System.out.println("******************value of : "+ nextfunctionCallingLine.split(" ")[1]+" is : "+funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                	if(funcMap.get(nextfunctionCallingLine.split(" ")[1]).toString() == "0"){
	                                		functionvariableStack.push(nextfunctionCallingLine.split(" ")[1]);
	                                	}else{
//	                                		System.out.println("Value put in stack "+funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                		functionvariableStack.push(funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                	}
	                                }else if(nextfunctionCallingLine.startsWith("SET")){
	                                	funcMap.put(nextfunctionCallingLine.split(" ")[1],functionvariableStack.pop());
//	                                	System.out.println(nextfunctionCallingLine.split(" ")[1]+" value is : "+funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                }else if(nextfunctionCallingLine.startsWith("RET")){
	                                	res = Integer.parseInt(functionvariableStack.pop());
	                                	stack.push(res);
	                                }else if(nextfunctionCallingLine.startsWith("ADD")){
	                                	String p2 = functionvariableStack.pop();
//	                                	System.out.println("p2 : "+p2);
	    
	                                	String p1 = functionvariableStack.pop();
//	                                	System.out.println("p1 : "+p1);
//	                                	System.out.println("p2 value :"+map.get(parameter2).toString());
//	                                	System.out.println("p1 value :"+map.get(parameter1).toString());
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
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
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
//	                                	System.out.println("value of : "+p1+" is : "+v1);
//	                                	System.out.println("value of : "+p2+" is : "+v2);
	                                	int r = v1 - v2;
	                                	functionvariableStack.push(Integer.toString(r));
	                                }else if(nextfunctionCallingLine.startsWith("MULT")){
	                                	String p2 = functionvariableStack.pop();
//	                                	System.out.println("p2 : "+p2);
	    
	                                	String p1 = functionvariableStack.pop();
//	                                	System.out.println("p1 : "+p1);
//	                                	System.out.println("p2 value :"+map.get(parameter2).toString());
//	                                	System.out.println("p1 value :"+map.get(parameter1).toString());
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
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
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
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
                	boolean testResult = checkBool(line.split(" ")[1]);
//                	System.out.println("Parameter : "+line.split(" ")[1]+" is Integer? "+result);
                	if(result == false && testResult == false){
                		stack.push(map.get(line.split(" ")[1]));
                	}else if(result == false && testResult == true){
                		if(line.split(" ")[1].equalsIgnoreCase("true")){
                			stack.push(1);
                		}else{
                			stack.push(0);
                		}
                	}
                	else{
                		stack.push(Integer.parseInt(line.split(" ")[1]));
                	}
                }else if(line.startsWith("SET")){
                	map.put(line.split(" ")[1], stack.pop()); 
//                	System.out.println("Key : "+ line.split(" ")[1]+" Value : "+map.get(line.split(" ")[1]));
                }else if(line.startsWith("LOGAND")){
                	int firstValue = stack.pop();
                	int secondValue = stack.pop();
                	if(firstValue == 0  && secondValue == 0){
                		res = 0;
                	}else if(firstValue == 0  && secondValue == 1){
                		res = 0;
                	}else if(firstValue == 1  && secondValue == 0){
                		res = 0;
                	}else if(firstValue == 1  && secondValue ==1){
                		res = 1;
                	}
                	map.put("Logic", 1);
                	stack.push(res);
                }
                else if(line.startsWith("LOGOR")){
                	int firstValue = stack.pop();
                	int secondValue = stack.pop();
                	if(firstValue == 0  && secondValue == 0){
                		res = 0;
                	}else if(firstValue == 0  && secondValue == 1){
                		res = 1;
                	}else if(firstValue == 1  && secondValue == 0){
                		res = 1;
                	}else if(firstValue == 1  && secondValue ==1){
                		res = 1;
                	}
                	map.put("Logic", 1);
                	stack.push(res);
                }
                else if(line.startsWith("ADD")){
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
                }else if(line.equals("EQL")){
                	int firstValue = stack.pop();
                	int secondValue = stack.pop();
                	int result = 0;
                	if(firstValue == secondValue){
                		result = 1;
                	}
                	stack.push(result);
                }else if(line.equals("GT")){
                	int secondValue = stack.pop();
                	int firstValue = stack.pop();
                	int result = 0;
                	if(firstValue > secondValue){
                		result = 1;
                	}
                	stack.push(result);
                }
                else if(line.equals("LT")){
                	int secondValue = stack.pop();
                	int firstValue = stack.pop();
                	int result = 0;
                	if(firstValue < secondValue){
                		result = 1;
                	}
                	stack.push(result);
                }
                else if(line.equals("NTEQL")){
                	int secondValue = stack.pop();
                	int firstValue = stack.pop();
                	int result = 0;
                	if(firstValue != secondValue){
                		result = 1;
                	}
                	stack.push(result);
                }
                else if(line.equals("LTEQL")){
                	int secondValue = stack.pop();
                	int firstValue = stack.pop();
//                	System.out.println(firstValue);
//                	System.out.println(secondValue);
                	int result = 1;
                	if(firstValue > secondValue){
                		result = 0;
                	}
                	stack.push(result);
                }
                else if(line.equals("GTEQL")){
//                	System.out.println("vgajasjbjh");
                	int secondValue = stack.pop();
                	int firstValue = stack.pop();
                	int result = 1;
//                	System.out.println(firstValue);
//                	System.out.println(secondValue);
                	if(firstValue < secondValue){
//                		System.out.println("inside");
                		result = 0;
                	}
                	stack.push(result);
                }
                else if(line.startsWith("PRINT")){
//                	System.out.println("vgajasjbjh");
                	String s = line.replace("PRINT ", "");
					String s1 = s.replace("\"", "");
//					System.out.println(s1);
                	if(map.get(s1) == null){
                		System.out.println(s1);
                	}else{
                		if(map.get("Logic") == null){
                			System.out.println(map.get(s1));
                		}else if(map.get("Logic") == 1){
                			if(map.get(s1) == 0){
                				System.out.println("False");
                			}else if(map.get(s1) == 1){
                				System.out.println("True");
                			}
                		}
                	}
                	
                }
                else if(line.startsWith("JMPIFFALSE")){
                	int r1 = stack.pop();
//                	System.out.println("r1 is : "+r1);
                	if(r1 == 0){
                	String label = line.split(" ")[1];
                	FileReader ifFileReader = 
                            new FileReader(fileName);
                	BufferedReader bufferedReaderforif = 
                            new BufferedReader(ifFileReader);
                	String ifCallingLine = null; 
                	while((ifCallingLine = bufferedReaderforif.readLine()) != null){
//                		System.out.println("Calling function "+functionCallingLine);
                		if(ifCallingLine.startsWith("LABEL")){
                			while((ifCallingLine = bufferedReaderforif.readLine()) != null){
//                				System.out.println(ifCallingLine);
                				if(ifCallingLine.startsWith("PRINT")){
                					String s = ifCallingLine.replace("PRINT ", "");
                					String s1 = s.replace("\"", "");
                					System.out.println(s1);
                				}
                				
                			}
                		}

                	}           
                	}else{
//                		FileReader ifFileReader = 
//                                new FileReader(fileName);
//                    	BufferedReader bufferedReaderforif = 
//                                new BufferedReader(ifFileReader);
//                    	String ifCallingLine = null; 
                    	while((line = bufferedReader.readLine()) != null){
//                    		System.out.println(line);
                    		if(line.startsWith("PRINT")){
            					String s = line.replace("PRINT ", "");
            					String s1 = s.replace("\"", "");
            					System.out.println(s1);
            				}
                    		if(line.startsWith("END")){
                    			break;
                    		}
                    	}
                	}
                	
//                	int firstValue = stack.pop();
//                	int secondValue = stack.pop();
//                	int result = 0;
//                	if(firstValue == secondValue){
//                		result = 1;
//                	}
//                	stack.push(result);
                }
                else{
                	
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
//        System.out.println("Final Result : "+res);
    }
}
