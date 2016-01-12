import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Customer.Gender;
import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Ordering.StatusOrder;
import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Staff.Role;
import com.bionic.edu.entity.Staff.StatusStaff;
import com.bionic.edu.entity.Ticket;
import com.bionic.edu.service.AccountantService;
import com.bionic.edu.service.AdminService;
import com.bionic.edu.service.AnalyticService;
import com.bionic.edu.service.CustomerService;
import com.bionic.edu.service.SecurityOffService;


public class Main {

	@SuppressWarnings("recourse")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
		
//		Administrator
//		AdminService adminService = context.getBean(AdminService.class); 
		
			//авторизація в системі
//			Staff staff = adminService.getAuthorization("igats", "1234");
//			System.out.println(staff);		
		
			//знайти рейс по заданому id
//			Flight flight = adminService.findById(3);
//			System.out.println(flight);
		
			//вивести всі рейси з БД
//			adminService.getAllFlights()
//				.forEach(a -> System.out.println(a));
		
			//вивести рейси з БД за вказаний період
//			adminService.getFlightByPeriod(LocalDate.of(2015, 12, 01), LocalDate.of(2015, 12, 15))
//				.forEach(a -> System.out.println(a));
			
			//добавити новий рейс
//			Flight flight = new Flight();
//			flight.setNumber("LLL3");
//			flight.setCompany("AirLines");
//			flight.setFrom_("Kiev");
//			flight.setTo_("Ivano-Frankivsk");
//			flight.setDate_departure(java.sql.Timestamp.valueOf(LocalDateTime.of(2015, 11, 21, 10, 01, 0)));
//			flight.setDate_arrival(java.sql.Timestamp.valueOf(LocalDateTime.of(2015, 11, 21, 13, 15, 0)));
//			flight.setFree_ticket(120);
//			flight.setPrice(190d);
//			adminService.addFlight(flight);
			
			//редагувати діючий рейс
//			Flight flight = new Flight();
//			flight = adminService.findById(7);
//			flight.setTo_("Varchava");
//			flight.setPrice(127d);
//			adminService.editFlight(flight);
			
			//видалення діючого рейсу
//			adminService.deleteFlight(9);
			
			//видалення рейсу по якому немає проданих/заброньованих квитків
//			System.out.println(adminService.deleteFlight(7));
			
			//добавляння до діючого рейсу квитків
//			Flight flight = new Flight();
//			flight = adminService.findById(6);
//			adminService.addTicket(flight, 105);
						
			//зміна статусу неоплачених замовлень
//			adminService.deleteNotPayOrdering();
			
			//test: пошук квитків по заданому замовленню
//			Ordering ordering = adminService.findOrderingById(10);
//			List<Ticket> ticket = adminService.findByOrdering(ordering);
//			for (Ticket t : ticket) {
//				System.out.println(t);
//			}
			
			//test: пошук квитків по заданому рейсі
//			Flight flight = adminService.findById(6);
//			List<Ticket> ticket = adminService.findBFlightById(flight);
//			for (Ticket t : ticket) {
//				System.out.println(t);
//			}
			
			//test: пошук квитків по заданому рейсі
//			List<Ordering> list = adminService.findByPeriod(LocalDate.of(2015, 10, 23), LocalDate.of(2015, 10, 25), StatusOrder.NOT_PAID);
//			list.forEach(a -> System.out.println(a));
			
		
		//Customer
		CustomerService customerService = context.getBean(CustomerService.class); 
			
			//знайти рейс за місцем призначення та датою відправки
//			customerService.findFlight(LocalDate.of(2015, 12, 13), "Milan")
//				.forEach(a -> System.out.println(a));
			
			//знайти всі рейси від поточної дати і дальше
//			customerService.getFlightByPeriod(LocalDate.now(), LocalDate.now().plusYears(3))
//				.forEach(a -> System.out.println(a));
		
			//добавити нове замовлення (бронювання квитків)
//			Customer customer = new Customer();
//				customer.setFirst_name("Іван");
//				customer.setSecond_name("Іванов");
//				customer.setBirthday(java.sql.Date.valueOf(LocalDate.of(1984, 9, 26)));
//				customer.setGender(Gender.MALE);
//				customer.setPassport_data("CC002879");
//				
//			Flight flight = new Flight();
//			flight = customerService.findById(7);
//			
//			Ticket ticket = new Ticket();
//				ticket.setCustomer(customer);
//				ticket.setFlight(flight);
//				ticket.setPrice(flight.getPrice());
//			
//			List<Ticket> tickets = new ArrayList<>();
//				tickets.add(ticket);
//			
//			System.out.println(customerService.addOrdering(tickets));
				

		
		//Accountant
//		AccountantService accountService = context.getBean(AccountantService.class); 
		
			//вивести всі замовлення в статусі booked
//			accountService.showBookedOrdering()
//				.forEach(a -> System.out.println(a));
			
			//зміна статусу оплачених замовлень
//			accountService.changeStatusOrder(6);
		
		//Business analytic
//		AnalyticService analyticService = context.getBean(AnalyticService.class); 
//		
//			//звіт за період згрупований по даті
//			analyticService.getReportByDate(LocalDate.of(2015, 10, 2), LocalDate.of(2015, 10, 10))
//				.forEach(a -> System.out.println(a));
		
			//звіт за період згупований по місцю призначення 
//			analyticService.getReportByDestinPlace(LocalDate.of(2015, 10, 2), LocalDate.of(2015, 10, 2))
//				.forEach(a -> System.out.println(a));
		
		//Security officer
//		SecurityOffService securityService = context.getBean(SecurityOffService.class);
		
			//авторизація в системі користувачів
	//		Staff staff = staffService.getAutorisation("igats", "1234");
	//		System.out.println(staff);
		
			//вивести список всіх працівників
//			securityService.getAllStaff()
//				.forEach(a -> System.out.println(a));
					
			//добавити користувача
//			Staff staff = new Staff();
//			staff.setRole(Role.ACCOUNTANT);
//			staff.setStatus(StatusStaff.ACTIVE);
//			staff.setFirst_name("Павло");
//			staff.setSecond_name("Васинда");
//			staff.setMiddle_name("Миколайович");
//			staff.setLogin("agaid");
//			staff.setPassword("qazx");
//			securityService.addStaff(staff);
			
			//знайти працівника за вказаним id
//			Staff staff = securityService.findById(6);
//			System.out.printf("%d %s %s %s %s",staff.getId(), staff.getFirst_name(), staff.getSecond_name(), staff.getRole(), 
//								staff.getStatus());
		
			//перевірка унікальності нового логіна
//			System.out.println(securityService.checkLogin("agaid1").toString());
			
			//редагувати користувача
//			Staff staff = securityService.findById(2);
//				staff.setFirst_name("Гайдичук");
//			securityService.editStaff(staff);
			
			//зміна статусу коритувача
//			Staff staff = securityService.findById(2);
//			securityService.changeStatus(staff);
	}			

}
