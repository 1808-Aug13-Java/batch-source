package dao;

public class ReimbursementRequest 
{
	private Integer employeeId;
	private Integer resolvingManager;
	private Integer targetManager;
	private String name;
	private String text;
	private Boolean isDenied;
	
	
	
	
	public ReimbursementRequest(int employeeId, int resolvingManager, int targetManager, String name, String text,
			boolean isDenied) {
		super();
		this.employeeId = employeeId;
		this.resolvingManager = resolvingManager;
		this.targetManager = targetManager;
		this.name = name;
		this.text = text;
		this.isDenied = isDenied;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	protected void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getResolvingManager() {
		return resolvingManager;
	}
	public void setResolvingManager(int resolvingManager) {
		this.resolvingManager = resolvingManager;
	}
	public int getTargetManager() {
		return targetManager;
	}
	protected void setTargetManager(int targetManager) {
		this.targetManager = targetManager;
	}
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	protected void setText(String text) {
		this.text = text;
	}
	public boolean isDenied() {
		return isDenied;
	}
	public void setDenied(boolean isDenied) {
		this.isDenied = isDenied;
	}
	
	
}
