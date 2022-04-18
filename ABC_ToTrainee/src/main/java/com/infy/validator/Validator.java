package com.infy.validator;

import com.infy.dto.TeamMemberDTO;
import com.infy.exception.AbcException;

public class Validator {

	public static void validate(TeamMemberDTO teamMember) throws AbcException {
		if(!validateEmployeeId(teamMember.getEmployeeId())) {
			throw new AbcException("Validator.INVALID_EMPLOYEEID");
		}
	}

	public static Boolean validateEmployeeId(Integer employeeId) throws AbcException {
		if(employeeId.toString().matches("^[0-9]{1,6}$")) {
			return true;
		}
		return false;
	}
}
