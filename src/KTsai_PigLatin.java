/*
 * Katherine Tsai
 * This program implements Cryptable and uses string methods to convert English and Pig Latin back and forth.
 */

public class KTsai_PigLatin implements KTsai_Cryptable {
	
	public String encrypt(String text) {
		
		return phraseToPig(text);
	}
	
	public String decrypt(String text) {
		
		String[] words = text.split(" ");
		text = "";
		
		//add each word back after converting to English
		for(int i = 0; i < words.length; i++) {
			
			text += toEnglish(words[i]) + " ";
		}
		
		return text;
	}
	
	//converts Pig Latin to English
	private String toEnglish(String word) {
	
		//look around the dash to revert
		int dash = word.indexOf("-");
		if(word.substring(dash).equals("-ay"))
			return word.substring(0, dash);
		else {
			
			if(isCapitalized(word))
				word = word.substring(0, 1).toLowerCase() + word.substring(1, dash + 1) + word.substring(dash + 1, dash + 2).toUpperCase() + word.substring(dash + 2);
		
			return word.substring(dash + 1, word.length() - 2) + word.substring(0, dash);
		}
			
	}
	
	//returns true if word is Capitalized
	private boolean isCapitalized(String word) {

		return word.substring(0, 1).equals(word.substring(0, 1).toUpperCase());
	}
	
	//capitalizes the first letter
	private String capitalize(String word) {
		
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
	
	//converts English word to PL
	private String wordToPig(String english) {
		
		int vowelIndex = hasAVowel(english);
		
		//convert the word based on vowels
		if (vowelIndex <= 0)
			return english + "-ay";
		
		else {
			
			String firsthalf = english.substring(0, vowelIndex);
			String secondhalf = english.substring(vowelIndex);
			
			//alter word if it's capitalized
			if (isCapitalized(english)) {
				
				secondhalf = capitalize(secondhalf);
				firsthalf = firsthalf.toLowerCase();
			}
			
			return secondhalf + "-" + firsthalf + "ay";
		}
	}
	
	//converts sentence to PL
	private String phraseToPig(String phrase) {
		
		//make array to store each word individually
		String[] words = phrase.split(" ");
		phrase = "";
		
		//add each word back after converting to PL
		for(int i = 0; i < words.length; i++) {
			
			phrase += wordToPig(words[i]) + " ";
		}
		
		return phrase;
	}
	
	//returns index of first vowel, returns -1 if no vowels in word
	private int hasAVowel(String word) {
		
		//loop through the word to check if each letter is a vowel
		for(int i = 0; i < word.length(); i++) {
			
			//check if the letter at index i is a vowel
			if ("AaEeIiOoUu".indexOf(word.substring(i, i + 1)) > -1)
				return i;
		}
		
		return -1;
	}
}