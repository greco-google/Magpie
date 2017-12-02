package Magpie;

public class Magpie4v2 {

	public String getGreeting() {
		return "Hello, let's talk.";
	}

   // String[] userPickFood = {
     //       "pizza", "carrot", "apple", "cookie cake", "chicken", "pie", "salad"
   // };
	public String getResponse(String statement) {
		String response = "";
		
		statement = statement.trim();
		statement = delete(statement, "?");
		statement = delete(statement, ".");
		
		if (statement.length() == 0) {
			response = "Say something, please.";
		}
		else if (statement.equalsIgnoreCase("asdfghjkl;") || statement.equalsIgnoreCase("zxcvbnm,./") || statement.equalsIgnoreCase("qwertyuiop[]") || statement.equalsIgnoreCase("1234567890")) {
		    response = "It looks like you just typed random keys.";
		}
		else if (isQuestion(statement)) {
			response = transformQuestion(statement);
		}
		else if (findKeyword(statement, "no") >= 0) {
			response = "Why so negative?";
		}
		else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "mom") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "dad") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0) {
			response = "Tell me more about your family.";
		}
		else if (findKeyword(statement, "Hi") >= 0
				|| findKeyword(statement, "hi") >= 0
				|| findKeyword(statement, "Hey") >= 0
				|| findKeyword(statement, "hey") >= 0
				|| findKeyword(statement, "Hello") >= 0
				|| findKeyword(statement, "hello") >= 0) {
			response = "How are you?";
		}
        //Was not working
		//else if (stringContainsItemFromList(statement, userPickFood)) {
        //    response = "Are you hungry? You know that I cannot eat. What do you like?";
       // }
		else {
			response = getRandomResponse();
		}
		
		return response;
	}
	
	private boolean stringContainsItemFromList(String statement, String[] userPickFood2) {
		// TODO Auto-generated method stub
		return false;
	}

	private String transformQuestion(String statement) {
		String questionWord = questionWord(statement);
		
		statement = delete(statement, questionWord);
		
		
		//Using the ~ to try to use bitwise functions
		
		if (questionWord.equals("will")) {
			statement = replace(statement, "be", "~will be~");
			statement = replace(statement, "will", "~if~");
		}
		else if (questionWord.equals("will")) {
			statement = replace(statement, "was", "");
			statement = replace(statement, "be", "~will be~");
		}
		
		//if the user uses I
		statement = replace(statement, "am i", "~you are~");
		statement = replace(statement, "will i", "~you will~");
		statement = replace(statement, "me", "~ you~");
		statement = replace(statement, "i", "~ you~");
		statement = replace(statement, "my", "~your~");
		
		//If the user asks the bot
		statement = replace(statement, "are you", "~I am~");
		statement = replace(statement, "will you", "~I will~");
		statement = replace(statement, "your", "~my~");
		statement = replace(statement, "do you", "~I~");
		statement = replace(statement, "you", "~ I~");
		statement = replace(statement, "can you", "~I can~");
		statement = replace(statement, "were you", "~I was~");


		
		if (findKeyword(statement, "is") >= 0) {
			statement = delete(statement, " is");
			statement = statement + " is";
		}
		if (findKeyword(statement, "are") >= 0) {
			statement = delete(statement, " are");
			statement = statement + " are";
		}
		
		statement = delete(statement, "~");
		statement = statement.trim();
		
		String intro = questionBeginning();
		
		if (questionWord == "am" || questionWord == "do" || questionWord == "does") {
			return intro + "if " + statement + ".";
		}
		return intro + questionWord + " " + statement + ".";
	}
	
	private String replace(String statement, String find, String replace) {
		int psn = findKeyword(statement, find, 0);
		
		while (psn >= 0) {
			String before = "", after = "";
			if (psn > 0) {
				before = statement.substring(0, psn);
			}
			if (psn + find.length() + 1 < statement.length()) {
				after = statement.substring(psn + find.length() + 1);
			}
			
			statement = before + replace + after;
			
			psn = findKeyword(statement, find, 0);
		}
		
		return statement;
	}
	
	private String delete(String statement, String delete) {
		for (int psn = 0; psn < statement.length() - delete.length() + 1; psn++) {
			if (statement.substring(psn, psn + delete.length()).equalsIgnoreCase(delete)) {
				statement = statement.substring(0, psn) + statement.substring(psn + delete.length());
			}
		}
		return statement;
	}

	private int findKeyword(String statement, String goal, int startPos) {
		statement = statement.trim().toLowerCase();
		int psn = statement.indexOf(goal, startPos);

		while (psn >= 0) {
			char before = ' ', after = ' ';
			if (psn > 0) {
				before = statement.charAt(psn-1);
			}
			if (psn + goal.length() < statement.length()) {
				after = statement.charAt(psn + goal.length());
			}
			
			if ((before < 'a' || before > 'z') && (after < 'a' || after > 'z') && before != '~' && after != '~') {
				return psn;
			}

			psn = statement.indexOf(goal, psn + 1);
		}

		return -1;
	}

	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}
	
	private String questionWord(String statement) {
		final String[] questionWords = { "who", "what", "when", "where", "why", "how", "do", 
					"could", "would", "will", "is", "did", "are"};
		
		for (int i = 0; i < questionWords.length; i++) {
			if (findKeyword(statement, questionWords[i]) == 0) {
				return questionWords[i];
			}
		}
		
		return "";
	}
	
	private boolean isQuestion(String statement) {
		final String questionWord = questionWord(statement);
		
		if (questionWord.equals("") || (statement.trim().length() == questionWord.length())) {
			return false;
		}
		
		return true;
	}
	
	private String questionBeginning() {
		final String[] responses = {
				"I seached the internet and I still don't know ",
				"I seached the internet and I still don't know ",
				"Who knows ",
				"You tell me ",
				"Why don't you ask someone else "
			};
		
		return responses[(int) (Math.random() * responses.length)];
	}

	private String getRandomResponse() {
		final String[] responses = {
				"Interesting, tell me more.",
				"Hmmm.",
				"Do you really think so?",
				"You don't say."
			};
		
		return responses[(int) (Math.random() * responses.length)];
	}

}