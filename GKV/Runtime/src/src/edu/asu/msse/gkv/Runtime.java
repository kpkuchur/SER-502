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
			e.printStackTrace();
		}
		return res;
	}
	public static boolean checkBool(String s){
		boolean res = false;
		try{
			if(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false"))
			res =true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	static int loopVariable = 0;
	public static void loopRT(int loopVarInit, String fileName){
    	Stack<Integer> loopstack = new Stack<Integer>();
    	LinkedList<Map<String, String>> looplist = new LinkedList<Map<String, String>>();
    	Map<String, Integer> loopmap = new HashMap<String, Integer>();
		try{
		FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
            	if(line.startsWith("DECLINT")){
                	loopmap.put(line.split(" ")[1], 0);
                }else if(line.startsWith("PUSH")){
                	boolean result = checkNum(line.split(" ")[1]);
                	boolean testResult = checkBool(line.split(" ")[1]);
                	if(result == false && testResult == false){
                		loopstack.push(loopmap.get(line.split(" ")[1]));
                	}else if(result == false && testResult == true){
                		if(line.split(" ")[1].equalsIgnoreCase("true")){
                			loopstack.push(1);
                		}else{
                			loopstack.push(0);
                		}
                	}
                	else{
                		loopstack.push(Integer.parseInt(line.split(" ")[1]));
                	}
                }else if(line.startsWith("SET")){
                	loopmap.put(line.split(" ")[1], loopstack.pop()); 
                }else if(line.startsWith("LOOPHEAD")){
            		loopstack.push(loopVarInit);
            		loopmap.put(line.split(" ")[3], loopVarInit);
            	}else if(line.equals("LT")){
                	int secondValue = loopstack.pop();
                	int firstValue = loopstack.pop();
                	int result = 0;
                	if(firstValue < secondValue){
                		result = 1;
                	}
                	loopstack.push(result);
                }
            	else if(line.equals("LTEQL")){
                	int secondValue = loopstack.pop();
                	int firstValue = loopstack.pop();
                	int result = 0;
                	if(firstValue <= secondValue){
                		result = 1;
                	}
                	loopstack.push(result);
                }
            	else if(line.startsWith("JMPIFFALSE")){
		        	int r1 = loopstack.pop();
		        	if(r1 == 0){
		        	String label = line.split(" ")[1];
		        	FileReader ifFileReader = 
		                    new FileReader(fileName);
		        	BufferedReader bufferedReaderforif = 
		                    new BufferedReader(ifFileReader);
		        	String ifCallingLine = null; 
		        	while((ifCallingLine = bufferedReaderforif.readLine()) != null){
		        		if(ifCallingLine.startsWith("LABEL")){
		        			
		        					String s = ifCallingLine.replace("LABEL : PRINT ", "");
		        					String s1 = s.replace("\"", "");
		        					if(loopmap.get(s1) == null){
			                    		System.out.println(s1);
			                    	}else{
			                    		if(loopmap.get("Logic") == null){
			                    			System.out.println(loopmap.get(s1));
			                    		}else if(loopmap.get("Logic") == 1){
			                    			if(loopmap.get(s1) == 0){
			                    				System.out.println("False");
			                    			}else if(loopmap.get(s1) == 1){
			                    				System.out.println("True");
			                    			}
			                    		}
			                    	}
		        			
		        				
		        			
		        		}
		
		        	}           
		        	}else{
		            	while((line = bufferedReader.readLine()) != null){
		            		if(line.startsWith("PRINT")){
		                    	String s = line.replace("PRINT ", "");
		    					String s1 = s.replace("\"", "");
		                    	if(loopmap.get(s1) == null){
		                    		System.out.println(s1);
		                    	}else{
		                    		if(loopmap.get("Logic") == null){
		                    			System.out.println(loopmap.get(s1));
		                    		}else if(loopmap.get("Logic") == 1){
		                    			if(loopmap.get(s1) == 0){
		                    				System.out.println("False");
		                    			}else if(loopmap.get(s1) == 1){
		                    				System.out.println("True");
		                    			}
		                    		}
		                    	}
		                    	
		                    }
		            		else if(line.startsWith("PUSH")){
		            			boolean result = checkNum(line.split(" ")[1]);
		                    	boolean testResult = checkBool(line.split(" ")[1]);
		                    	if(result == false && testResult == false){
		                    		loopstack.push(loopmap.get(line.split(" ")[1]));
		                    	}else if(result == false && testResult == true){
		                    		if(line.split(" ")[1].equalsIgnoreCase("true")){
		                    			loopstack.push(1);
		                    		}else{
		                    			loopstack.push(0);
		                    		}
		                    	}
		                    	else{
		                    		loopstack.push(Integer.parseInt(line.split(" ")[1]));
		                    	}
		            		}
		            		else if(line.startsWith("ADD")){
		            			int firstValue = loopstack.pop();
		                    	int secondValue = loopstack.pop();
		                    	int res = firstValue + secondValue;
		                    	loopstack.push(res);
		    				}
		            		else if(line.startsWith("SUB")){
		            			int firstValue = loopstack.pop();
		                    	int secondValue = loopstack.pop();
		                    	int res = firstValue - secondValue;
		                    	loopstack.push(res);
		    				}
		            		else if(line.startsWith("MULT")){
		            			int firstValue = loopstack.pop();
		                    	int secondValue = loopstack.pop();
		                    	int res = firstValue * secondValue;
		                    	loopstack.push(res);
		    				}
		            		else if(line.startsWith("DIV")){
		            			int firstValue = loopstack.pop();
		                    	int secondValue = loopstack.pop();
		                    	int res = firstValue / secondValue;
		                    	loopstack.push(res);
		    				}
		            		else if(line.startsWith("SET")){
		                    	loopmap.put(line.split(" ")[1], loopstack.pop()); 
		                    }
		            		else if(line.startsWith("JMP")){
		            			loopVarInit++;
		            			loopRT(loopVarInit, fileName);
		            		}
		            	}
        	}
        	
        }
            }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    public static void main(String [] args) {
    	Stack<Integer> stack = new Stack<Integer>();
    	LinkedList<Map<String, String>> list = new LinkedList<Map<String, String>>();
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	Map<String, Stack<Integer>> stackmap = new HashMap<String, Stack<Integer>>();
    	int res =0;
    	String filePath = "C:\\Users\\Gowtham\\Git\\SER-502\\GKV\\Sample-Programs\\Intermediate-Code\\";
    	String fileName = filePath + args[0];

        String line = null;

        try {
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	if(line.startsWith("CALL")){
                	String funcName = line.split(" ")[1];
                	int count = Integer.parseInt(line.split(" ")[2]);
                	for(int i=count; i>0; i--){
                		map.put("parameter"+i, stack.pop());
                	}
                	if(funcName == "FACT"){
                		int value = map.get("parameter1");
                		int result =1;
                		  for ( int i=1; i<=value; i++ ){
                		    result *= i;
                		  }
                		  System.out.println(result);
                		  System.exit(0);
                	}else if(funcName == "FIBO"){
                		int value = map.get("parameter1");
                		int x = 0, y = 1, result = 1;
                		System.out.println(x);
                		System.out.println(y);
                        for (int i = 0; i < value; i++) {
                            x = y;
                            y = result;
                            result = x + y;
                            System.out.println(result);
                        }
                        
              		  	System.exit(0);
                	}else if(funcName == "GCD"){
                		int value1 = map.get("parameter1");
                		int value2 = map.get("parameter1");
                		int divisor = value2;
                	    while(divisor >= 1){
                	        if(value1 % divisor == 0 && value2 % divisor == 0){
                	            System.out.println(divisor);
                	        }
                	        divisor--;
                	     
                	    }
                	 
                        System.out.println("1");
              		  	System.exit(0);
                	}
                	
                	FileReader functionFileReader = 
                            new FileReader(fileName);
                	BufferedReader bufferedReaderforFunctionCall = 
                            new BufferedReader(functionFileReader);
                	String functionCallingLine = null; 
                	while((functionCallingLine = bufferedReaderforFunctionCall.readLine()) != null){
                		if(functionCallingLine.startsWith("FUNCSTART")){
                        	Map<String, String> funcMap = new HashMap<String, String>();
                        	Stack<String> functionvariableStack = new Stack<String>();
                        	if(funcName.equals(functionCallingLine.split(" ")[1])){
	                        	funcMap.put("functionName", functionCallingLine.split(" ")[1]);
	                        	funcMap.put("functionReturnType", functionCallingLine.split(" ")[2]);
	                        	String nextfunctionCallingLine = null;
	                        	nextfunctionCallingLine = bufferedReaderforFunctionCall.readLine();
	                        	while((nextfunctionCallingLine = bufferedReaderforFunctionCall.readLine()) != null){
	                        		if(nextfunctionCallingLine.startsWith("DECLINT")){
	                        			funcMap.put(nextfunctionCallingLine.split(" ")[1], "0");
	                                }else if(nextfunctionCallingLine.startsWith("PUSH")){
	                                	if(funcMap.get(nextfunctionCallingLine.split(" ")[1]).toString() == "0"){
	                                		functionvariableStack.push(nextfunctionCallingLine.split(" ")[1]);
	                                	}else{
	                                		functionvariableStack.push(funcMap.get(nextfunctionCallingLine.split(" ")[1]));
	                                	}
	                                }else if(nextfunctionCallingLine.startsWith("SET")){
	                                	funcMap.put(nextfunctionCallingLine.split(" ")[1],functionvariableStack.pop());
	                                }else if(nextfunctionCallingLine.startsWith("RET")){
	                                	res = Integer.parseInt(functionvariableStack.pop());
	                                	stack.push(res);
	                                }else if(nextfunctionCallingLine.startsWith("ADD")){
	                                	String p2 = functionvariableStack.pop();
	    
	                                	String p1 = functionvariableStack.pop();
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
	                                	int r = v1 + v2;
	                                	functionvariableStack.push(Integer.toString(r));
	                                } else if(nextfunctionCallingLine.startsWith("SUB")){
	                                	String p2 = functionvariableStack.pop();
	                                	String p1 = functionvariableStack.pop();
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
	                                	int r = v1 - v2;
	                                	functionvariableStack.push(Integer.toString(r));
	                                }else if(nextfunctionCallingLine.startsWith("MULT")){
	                                	String p2 = functionvariableStack.pop();
	                                	String p1 = functionvariableStack.pop();
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
	                                	int r = v2 * v1;
	                                	functionvariableStack.push(Integer.toString(r));
	                         
	                                }else if(nextfunctionCallingLine.startsWith("DIV")){
	                                	String p2 = functionvariableStack.pop();
	                                	String p1 = functionvariableStack.pop();
	                                	funcMap.put(p2, map.get("parameter2").toString());
	                                	funcMap.put(p1, map.get("parameter1").toString());
	                                	int v1 = Integer.parseInt(funcMap.get(p1));
	                                	int v2 = Integer.parseInt(funcMap.get(p2));
	                                	int r = v1/v2;
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
                }else if(line.startsWith("DECLINT")){
                	map.put(line.split(" ")[1], 0);
                }else if(line.startsWith("DECLSTACK")){
                	stackmap.put(line.split(" ")[1], new Stack<Integer>());
                }
                else if(line.contains(".PUSH")){
                	Stack<Integer> s = stackmap.get(line.split("\\.")[0]);
                	s.push(Integer.parseInt(line.split(" ")[1]));
                	stackmap.put(line.split(" ")[1], s);
                }
                else if(line.contains(".POP")){
                	Stack<Integer> s = stackmap.get(line.split("\\.")[0]);
                	int res1 = s.pop();
                	stack.push(res1);
                }
                else if(line.startsWith("PUSH")){
                	boolean result = checkNum(line.split(" ")[1]);
                	boolean testResult = checkBool(line.split(" ")[1]);
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
                }else if(line.startsWith("LOOPHEAD")){
                	loopVariable = map.get(line.split(" ")[3]);
                	loopRT(loopVariable,fileName);
                	break;
                }
                else if(line.startsWith("LOGAND")){
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
                	int result = 1;
                	if(firstValue > secondValue){
                		result = 0;
                	}
                	stack.push(result);
                }
                else if(line.equals("GTEQL")){
                	int secondValue = stack.pop();
                	int firstValue = stack.pop();
                	int result = 1;
                	if(firstValue < secondValue){
                		result = 0;
                	}
                	stack.push(result);
                }
                else if(line.startsWith("PRINT")){
                	String s = line.replace("PRINT ", "");
					String s1 = s.replace("\"", "");
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
                	if(r1 == 0){
                	String label = line.split(" ")[1];
                	FileReader ifFileReader = 
                            new FileReader(fileName);
                	BufferedReader bufferedReaderforif = 
                            new BufferedReader(ifFileReader);
                	String ifCallingLine = null; 
                	while((ifCallingLine = bufferedReaderforif.readLine()) != null){
                		if(ifCallingLine.startsWith("LABEL")){
                			while((ifCallingLine = bufferedReaderforif.readLine()) != null){
                				if(ifCallingLine.startsWith("PRINT")){
                					String s = ifCallingLine.replace("PRINT ", "");
                					String s1 = s.replace("\"", "");
                					System.out.println(s1);
                					
                				}
                				else if(ifCallingLine.startsWith("ELSEEND")){
                        			System.exit(0);
                        			
                        		}
                				
                			}
                		}
                			
                	}           
                	}else{
                    	while((line = bufferedReader.readLine()) != null){
                    		if(line.startsWith("PRINT")){
            					String s = line.replace("PRINT ", "");
            					String s1 = s.replace("\"", "");
            					System.out.println(s1);
            				}
                    		else if(line.startsWith("PUSH")){
                    			boolean result = checkNum(line.split(" ")[1]);
                            	boolean testResult = checkBool(line.split(" ")[1]);
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
                    		}
                    		else if(line.startsWith("ADD")){
                    			int firstValue = stack.pop();
                            	int secondValue = stack.pop();
                            	res = firstValue + secondValue;
                            	stack.push(res);
            				}
                    		else if(line.startsWith("SET")){
                            	map.put(line.split(" ")[1], stack.pop()); 
                            }
                    		else if(line.startsWith("END")){
                    			System.exit(0);
                    		}
                    	}
                	}
                }
                else {
                	
                }
            }   
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
        }
    }
}
