package java9s;

public interface EmployeeDAO 
{
	public void saveEmployee(Employee e); 
	//public void updateEmployee(Employee e);
	public Employee getEmployee(int empId);
}
