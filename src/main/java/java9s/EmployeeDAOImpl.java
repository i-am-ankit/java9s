package java9s;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDAOImpl implements EmployeeDAO
{
	private JdbcTemplate jdbcTemplate;
	private final String employee_Select = 	"SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
	private final String employee_insert = "INSERT INTO EMPLOYEE(EMP_NAME)VALUES(?)";
	//private final String employee_update = "UPDATE EMPLOYEE SET EMP_NAME=? WHERE ";
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void saveEmployee(Employee e) 
	{
		this.jdbcTemplate.update(this.employee_insert, new Object[]{e.getEmpName()});
	}

	/*public void updateEmployee(Employee e) 
	{
		this.jdbcTemplate.update(this.employee_update, new Object[]{e.getEmpName(), new Integer(e.getEmpId())});
	}*/

	public Employee getEmployee(int empId) 
	{
		Employee e = (Employee)jdbcTemplate.queryForObject(this.employee_Select,
				new Object[] {new Integer(empId)},
				new RowMapper() 
		{
			public Object mapRow(ResultSet rs, int arg1) throws SQLException			
			{
				Employee e = new Employee();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setEmpName(rs.getString("EMP_NAME"));
				return e;
			}
		});
		return e;
	}


}