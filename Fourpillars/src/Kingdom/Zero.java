package Kingdom;
public class Zero {
	public static void main(String[] args) {
		//Place your code here
		Sleeping sleeping = new Sleeping();
		
		try {
			sleeping.sleep();
		} catch (CustomException w) {
			w.printStackTrace();
		}
	}

}

public class CustomException extends Exception {
	private String memo;
	private static final long serialVersionUID = 1L;
	
	public CustomException(String memo) {
		super(memo);
		this.memo = memo;
	}
}
