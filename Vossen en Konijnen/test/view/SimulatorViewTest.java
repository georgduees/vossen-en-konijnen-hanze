package view;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import de.vogella.junit.first.MyClass;

public class SimulatorViewTest {
    SimulatorView tester = new SimulatorView(100,100);
    @ignore
	@Test
	public final void testSimulatorView() {
		fail("Not yet implemented");
	}
	@ignore
	@Test
	public final void testSetColor() {
		fail("Not yet implemented");
	}
	@ignore
	@Test
	public final void testShowStatus() {
		fail("Not yet implemented");
	}
	@ignore
	@Test
	public final void testIsViable() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSetViewPanel() {
	JPanel foo = new JPanel();
	tester.setViewPanel(foo);
	assertSame("TESTSetViewPanel",foo,tester.getViewPanel());}
	@Test
	public final void testGetViewPanel() {
		assertTrue("InstanceOfViewPanel",tester.getViewPanel() instanceof JPanel);
		
	}
	}


