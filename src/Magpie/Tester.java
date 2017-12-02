package Magpie;

public class Tester {

	public static void main(String[] args) {
		
		/*String keyword = "aaaab";
		String keyword = "Succeeding";
		
		System.out.println(keyword.indexOf("b"));
		System.out.println(keyword.indexOf("in"));
		System.out.println(keyword.indexOf("zoo"));*/

		
		/**String phrase = "I like spaghetti smothered in sauce";
		String goal = "mother";
		
		String before;
		before = phrase.substring(phrase.indexOf(goal)-1, phrase.indexOf(goal));
		System.out.println(before);
		
		String after;
		after = phrase.substring(phrase.indexOf(goal) + goal.length(),  phrase.indexOf(goal) + goal.length() + 1);
		System.out.println(after);*/
		
		String phrase = "I have a mother.";
		String goal = "mother";
		
		String before;
		before = phrase.substring(phrase.indexOf(goal)-1, phrase.indexOf(goal));
		System.out.println(before);
		
		String after;
		after = phrase.substring(phrase.indexOf(goal) + goal.length(),  phrase.indexOf(goal) + goal.length() + 1);
		System.out.println(after);
	}

}
