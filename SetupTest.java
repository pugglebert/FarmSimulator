package farmSimulatorTests;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import farmSimulatorGUI.*;


class SetupTest {
	
	GameEnvironment game = new GameEnvironment();
	SetupScreen setupWindow = new SetupScreen(game);
	JLabel errorLabel = new JLabel();
	
	@Test
	void testValidName() {
		assertTrue(setupWindow.checkNameValid("Bob", errorLabel));
		assertTrue(setupWindow.checkNameValid("Bob Smith", errorLabel));
		assertTrue(setupWindow.checkNameValid("abcdefghijklmno", errorLabel));
	}
	
	@Test
	void testInvalidName() {
		assertFalse(setupWindow.checkNameValid("Bo", errorLabel));
		assertFalse(setupWindow.checkNameValid("123", errorLabel));
		assertFalse(setupWindow.checkNameValid("abcdefghijklmnop", errorLabel));
		assertFalse(setupWindow.checkNameValid("Bob1", errorLabel));
		assertFalse(setupWindow.checkNameValid("B$!b", errorLabel));
	}
	
	@Test
	void testValidAge() {
		assertTrue(setupWindow.checkAgeValid("1", errorLabel));
		assertTrue(setupWindow.checkAgeValid("100", errorLabel));
		assertTrue(setupWindow.checkAgeValid("50", errorLabel));
	}
	
	@Test
	void testInalidAge() {
		assertFalse(setupWindow.checkAgeValid("0", errorLabel));
		assertFalse(setupWindow.checkAgeValid("101", errorLabel));
		assertFalse(setupWindow.checkAgeValid("5a", errorLabel));
		assertFalse(setupWindow.checkAgeValid("@!1", errorLabel));
		assertFalse(setupWindow.checkAgeValid("abc", errorLabel));
	}

	@Test
	void isValidTest() {
		assertTrue(setupWindow.isValid("Bob", "25", "Bobs Farm"));
		assertFalse(setupWindow.isValid("Bob1", "25", "Bobs Farm"));
		assertFalse(setupWindow.isValid("Bob", "200", "Bobs Farm"));
		assertFalse(setupWindow.isValid("Bob", "25", "Bobs $$$ Farm"));
	}
}
