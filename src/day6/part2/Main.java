package day6.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		List<String> input;
		Map<Integer, List<String>> columns = new HashMap<>();

		try {
			input = Files.readAllLines(Paths.get(".\\src\\day6\\part2\\input.in"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}

		columns.putAll(initialize(input));
		System.out.println(processInput(columns));
		

	}

	private static String processInput(Map<Integer, List<String>> map) {
		String result = "";
		
		for(int i : map.keySet()){
			result += processColumn(map.get(i));
		}
		
		return result;
	}

	private static String processColumn(List<String> list) {
		Map<String, Integer> occurence = new HashMap<>();

		for (String s : list) {
			if (!occurence.containsKey(s)) {
				occurence.put(s, 1);
			} else {
				int value = occurence.get(s);
				occurence.replace(s, ++value);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i : occurence.values()){
			if(i <= min){
				min = i;
			}
		}
		
		for(String s : occurence.keySet()){
			if(occurence.get(s) <= min){
				return s;
			}
		}
		
		return "";
	}

	private static Map<Integer, List<String>> initialize(List<String> input) {
		Map<Integer, List<String>> map = new HashMap<>();

		for (int i = 0; i < input.get(0).length(); i++) {
			List<String> column = new ArrayList<>();
			map.put(i, column);
		}

		for (String s : input) {
			String[] parts = s.split("");
			for (int i = 0; i < parts.length; i++) {
				List<String> column = map.get(i);
				column.add(parts[i]);
				map.replace(i, column);
			}
		}

		return map;
	}

}

