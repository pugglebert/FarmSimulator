package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JLabel;

import farmSimulatorGUI.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SetupTest {
	
	GameEnvironment game = new GameEnvironment();
	SetupScreen setupWindow = new SetupScreen(game);
	@ParameterizedTest
	@ValueSource(strings = {"Bob", "Bob Smith", "abcdefghijklmno"})
	void testValidName(String name) {
		JLabel errorLabel = new JLabel();
		assertTrue(setupWindow.checkName(name, errorLabel));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Bo", "123", "abcdefghijklmnop", "Bob1", "B$!b"})
	void testInvalidName(String name) {
		JLabel errorLabel = new JLabel();
		assertFalse(setupWindow.checkName(name, errorLabel));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"1", "100", "50"})
	void testValidAge(String age) {
		JLabel errorLabel = new JLabel();
		assertTrue(setupWindow.checkAge(age, errorLabel));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"0", "101", "5a", "@!1", "abc"})
	void testInalidAge(String age) {
		JLabel errorLabel = new JLabel();
		assertFalse(setupWindow.checkAge(age, errorLabel));
	}
	

}
