package com.chandrika.model;

import java.math.BigDecimal;

public class Request {
	private int id;
	private String status;
	private BigDecimal amount;
	private Employee employee;
	private Manager manager;
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Request(int id, String status, BigDecimal amount, Employee employee, Manager manager) {
		super();
		this.id = id;
		this.status = status;
		this.amount = amount;
		this.employee = employee;
		this.manager = manager;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee e) {
		employee = e;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager m) {
		manager = m;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		// result = prime * result + employee;
		// result = prime * result + manager;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (employee != other.employee)
			return false;
		if (manager != other.manager)
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Request [id=" + id + ", status=" + status + ", amount=" + amount + ", Employee=" + employee
				+ ", Manager=" + manager + "]";
	}
	
}
