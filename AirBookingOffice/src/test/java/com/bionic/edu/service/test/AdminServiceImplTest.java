package com.bionic.edu.service.test;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.bionic.edu.entity.Flight;
import com.bionic.edu.service.AdminService;
import com.bionic.edu.service.AdminServiceImpl;

public class AdminServiceImplTest {
	
	@Inject
	private AdminServiceImpl adminService;
	
	@Before
	public void initAdminService() {
		adminService = new AdminServiceImpl();
	}

	@Test
	public void testFindById() {
		int id = 5;
		Flight flight = adminService.findById(id);
		Assert.assertTrue(flight == null);
	}
	
	@Ignore
	@Test
	public void testGetAuthorization() {
		fail("Not yet implemented");
	}

}
