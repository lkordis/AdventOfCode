package day5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) {
		String input = "ffykfhsq";
		String message = "";
		String password = "";
		
		int characters = 0;
		
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			message = input + Integer.toString(i);
			String hashedInput = hashValue(message);
			
			if(hasFiveZeroes(hashedInput) && characters < 8){
				password += hashedInput.split("")[5];
				System.out.println(password);
				characters++;
			}
			
			if(characters == 8){
				System.out.println(password);
				break;
			}
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
