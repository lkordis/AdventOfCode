package day5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) {
		String input = "ffykfhsq";
		String message = "";
		String password[] = new String[8];
		
		int characters = 0;
		
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			message = input + Integer.toString(i);
			String hashedInput = hashValue(message);
			
			if(hasFiveZeroes(hashedInput) && characters < 8){
				if(validPosition(hashedInput.split("")[5]) && 
						password[Integer.parseInt(hashedInput.split("")[5])] == null){
					password[Integer.parseInt(hashedInput.split("")[5])] = hashedInput.split("")[6];
					System.out.println(toString(password));
					characters++;
				}
			}
			
			if(characters == 8){
				System.out.println("Password: ");
				System.out.println(toString(password));
				break;
			}
		}
	}
	
	private static String toString(String[] password) {
		String res = "";
		
		for(String letter : password){
			res += letter;
		}
		
		return res;
	}
	
	private static boolean validPosition(String string) {
		switch (string) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
			return true;

		default:
			return false;
		}
	}

	private static boolean hasFiveZeroes(String hashedInput) {
		String[] parts = hashedInput.split("");
		
		for(int i=0; i < 5; i++){
			if(!parts[i].equals("0")){
				return false;
			}
		}
		
		return true;
	}

	private static String hashValue(String message){
		try {
			byte[] bytesOfMessage = message.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			StringBuffer result = new StringBuffer();
			
			for (byte b : md.digest(bytesOfMessage)) {
			    result.append(String.format("%02X", b));
			}
			return result.toString();
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
