package application;

import org.junit.*;

import controller.Controller;
import model.person;

import static org.junit.Assert.*;

public class testcases{
	Controller control = new Controller();
	
	@Test
	public void testComparePeople() {
		person person1 = new person("John", "Smith", "Baker", "0872283918", "green@gmail.com");
		person person2 = new person("Brendan", "Parker", "Tomson", "0863920129", "red@gmail.com");
		assertEquals(false, control.comparePeople(person1, person2));
		assertEquals(true, control.comparePeople(person1, person1));
	}
	
	@Test
	public void testgetFirstName() {
		person person1 = new person("John", "Smith", "Baker", "0872283918", "green@gmail.com");
		assertEquals("John", person1.getName().getFirstName());
	}
}

