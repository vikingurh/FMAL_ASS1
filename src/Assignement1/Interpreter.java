package Assignement1;

import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Interpreter {
	static private Stack<Object> myStack = new Stack<Object>();
	static private TreeMap<String, Integer> myMap = new TreeMap<String, Integer>();
	public static void main(String[] args) {
		run();
	}
	
	private static void run(){
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			String instruction = sc.next();
			if (instruction.equals("PUSH")){
				myStack.push(sc.next());
			}
			else if (instruction.equals("ASSIGN")){
				if (myStack.isEmpty() || !myStack.peek().toString().matches(".*\\d+.*") ){
					error(instruction);
				}
				Integer value = Integer.parseInt(myStack.pop().toString());
				if (myStack.isEmpty()){
					error(instruction);
				}
				String name = myStack.pop().toString();
				myMap.put(name, value);
			}
			else if (instruction.equals("SUB")){
				if (myStack.isEmpty()){
					error(instruction);
				}
				Object value1 = myStack.pop();
				if (myStack.isEmpty()){
					error(instruction);
				}
				Object value2 = myStack.pop();
				Integer firstValue = convert(value1, instruction);
				Integer secondValue = convert(value2, instruction);
				myStack.push(secondValue - firstValue);
			}
			else if (instruction.equals("ADD")){
				if (myStack.isEmpty()){
					error(instruction);
				}
				Object value1 = myStack.pop();
				if (myStack.isEmpty()){
					error(instruction);
				}
				Object value2 = myStack.pop();
				Integer firstValue = convert(value1, instruction);
				Integer secondValue = convert(value2, instruction);
				myStack.push(secondValue + firstValue);
			}
			else if (instruction.equals("MULT")){
				if (myStack.isEmpty()){
					error(instruction);
				}
				Object value1 = myStack.pop();
				if (myStack.isEmpty()){
					error(instruction);
				}
				Object value2 = myStack.pop();
				Integer firstValue = convert(value1, instruction);
				Integer secondValue = convert(value2, instruction);
				myStack.push(secondValue * firstValue);
			}
			else if (instruction.equals("PRINT")){
				if (myStack.isEmpty()){
					error(instruction);
				}
				Object value = myStack.pop();
				System.out.println(convert(value, instruction));
			}
			else{
				error(instruction);
			}
		}
	}
	
	private static Integer convert(Object value, String s){
		if (value.toString().matches(".*\\d+.*")){
			if (valid(value.toString())){
				return Integer.parseInt(value.toString());
			}
			error(s);
		}
		if (myMap.get(value.toString()) != null){
			return myMap.get(value.toString());
		}
		else{
			error(s);	
		}
		return 0;	
	}
	
	private static void error(String s){
		System.out.print("Error for operator: ");
		System.out.println(s);
		System.exit(1);
	}
	
	private static boolean valid(String s){
		for (int i = 0; i < s.length(); i++){
			if (!Character.isDigit(s.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
