import java.util.LinkedHashSet;

public class QA<T> {
	
	LinkedHashSet<T> QABank = new LinkedHashSet<T>();
	
	public String[] questions = {
			"What is the capital of Virginia?",
			"What is today's date?",
			"What day is today?",
			"What is the capital of Maryland?",
			"What is the capital of Mississippi?",
			"What is the capital of Massachussetts?",
			"What is the capital of New Jersey?",
			"What is the capital of Colorado",
			"What is the capital of New York",
			"What is the capital of Pennsylvania",
			"What is the capital of Conneticut?",
			"What color is the sky?",
			"In which city are the headquarters of Revature located in?",
			"What is the capital of Alabama?",
			"What is the capital of Alaska?",
			"What is the capital of Arkansas?",
			"What is the capital of California?",
			"What is the capital of Delaware?",
			"What is the capital of Florida?",
			"What is the capital of Georgia?",
			"What is the capital of Hawaii?",
			"What is the capital of Idaho?",
			"What is the capital of Illinois?",
			"What is the capital of Indiana?",
			"What is the capital of Iowa?",
			"What is the capital of Kansa?",
			"What is the capital of Kentucky?",
			"What is the capital of Louisiana?",
			"What is the capital of Maine?",
			"What is the capital of Michigan?"
	};
	
	public String[] answers = { "Richmond", "August 17, 2018", "Friday", "Annapolis", "Jackson",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty",
					"Empty"		
	};
	
	
	
	for (int i=0; i < questions.length(); i++) {
		String qapair = {questions[i], answers[i]};
		QABank.add({questions[i], answers[i]});		
	}
	
	
}
