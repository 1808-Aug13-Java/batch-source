package service;

public class ReturnPack <T>
{
	private int errorCode;
	private String errorMessage;
	private T object;
	
	public ReturnPack(int ec, String em)
	{
		errorMessage = em;
		errorCode = ec;
		object = null;
	}
	
	public ReturnPack(int ec, T o)
	{
		errorCode = ec;
		object = o;
		errorMessage = "Okay";
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public T getObject() {
		return object;
	}
	
	
}
