package message;

public class Message 
{
	String theMessage;
	int code;
	
	public Message(String message, int code)
	{
		this.theMessage = message;
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}
	
	public String getMessage()
	{
		return theMessage;
	}
}
