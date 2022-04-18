package com.infy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.dto.CustomerLoginDTO;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerLoginRepository;
import com.infy.service.CustomerLoginService;
import com.infy.service.CustomerLoginServiceImpl;

@SpringBootTest
public class DemoSpringBootCoreApplicationTests {
	
	@Mock
	CustomerLoginRepository customerLoginRepository;

	@InjectMocks
	CustomerLoginService customerLoginService =  new CustomerLoginServiceImpl();

	@Test
	public void authenticateCustomerTestValidCredentials() throws InfyBankException {
		CustomerLoginDTO customer = new CustomerLoginDTO();
		customer.setLoginName("monica");
		customer.setPassword("monica123");
		Mockito.when(customerLoginRepository.getCustomerLoginByLoginName("monica")).thenReturn(customer);
		String actual = customerLoginService.authenticateCustomer(customer);
		Assertions.assertEquals("SUCCESS", actual);
	} 

	@Test
	public void authenticateCustomerTestInValidCredentials() throws InfyBankException {
		CustomerLoginDTO customer = new CustomerLoginDTO();
		customer.setLoginName("monica");
		customer.setPassword("monica12");
		
		CustomerLoginDTO fromRepository = new CustomerLoginDTO();
		fromRepository.setLoginName("monica");
		fromRepository.setPassword("monica123");
		
		Mockito.when(customerLoginRepository.getCustomerLoginByLoginName("monica")).thenReturn(fromRepository);
		InfyBankException exception = Assertions.assertThrows(InfyBankException.class, () -> customerLoginService.authenticateCustomer(customer));
		Assertions.assertEquals("Service.WRONG_CREDENTIALS", exception.getMessage());
	}
}