/*
 * Vigenere Crypt
 */

public class KTsai_VigenereCrypt implements KTsai_Cryptable{

	private String[] table;
	private String keyword = "tacoma";


	public KTsai_VigenereCrypt(){

		table = new String[26];

		int val = 0;
		int start = 0;

		for(int row = 0; row < table.length; row++){
			String toBuild ="";
			for(int col = 0; col < table.length; col++){
				toBuild += (char)(val%26+97);
				val++;
			}
			table[row] = toBuild;
			start++;
			val = start;
		}
	}

	public String encrypt(String plain){

		String key = buildKey(plain);
		
		String toReturn = "";

		for(int i = 0; i < plain.length(); i++){
			String plainLetter = plain.substring(i,i+1);
			if(plainLetter.equals(" ")){
				toReturn+=" ";
			}
			else{
				boolean isCap = isCap(plainLetter);
				
				if(isCap)
					plainLetter = plainLetter.toLowerCase();
				int row = key.charAt(i)-97;
				int col = plainLetter.charAt(0)-97;
				String cipher = table[row].substring(col,col+1);
				if(isCap)
					cipher = cipher.toUpperCase();
				toReturn+=cipher;
			}
		}
		return toReturn;
	}

	public String decrypt(String cipher){
		
		String key = buildKey(cipher);
		String toReturn ="";
		
		for(int i = 0; i < cipher.length(); i++){
			String cipherLetter = cipher.substring(i,i+1);
			if(cipherLetter.equals(" ")){
				toReturn+=" ";
			}
			else{
				boolean isCap = isCap(cipherLetter);
				if(isCap)
					cipherLetter = cipherLetter.toLowerCase();
				int row = key.charAt(i)-97;
				int col = table[row].indexOf(cipherLetter.charAt(0));
				String result = (char)(col+97)+"";
				if(isCap)
					result = result.toUpperCase();
				toReturn+=result;
			}
		}
		
		return toReturn;
		
	}

	private String buildKey(String from){
		
		String key="";
		int loc = 0;

		for(int i = 0; i < from.length(); i++){
			key+= keyword.substring(loc, loc+1);
			loc = (loc+1)%keyword.length();
		}
		return key;
	}
	
	private boolean isCap(String letter) {
		return letter.equals(letter.toUpperCase());
	}
	
	public static void main(String[] args) {
		String phrase = "Any Dog Under twenty pounds is worthless";
		KTsai_VigenereCrypt v = new KTsai_VigenereCrypt();
		String res = v.encrypt(phrase);
		System.out.println(v.decrypt(res));
	}
}
