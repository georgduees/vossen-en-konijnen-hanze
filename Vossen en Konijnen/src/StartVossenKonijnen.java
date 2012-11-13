
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class with the main method, the only thing this does is be able to correctly start 
 * the vossen en konijnen project.
 * @author Mark Visscher, Cor Sloot, Tycho Marinus, Luuk Blom.
 *
 */
public class StartVossenKonijnen extends KeyAdapter
{
	static final JFrame frame = new JFrame("Field Size.");
	static final JDialog dialog = new JDialog(frame, "Give values for the size of the Field.");
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// Get default locale
		Locale locale = Locale.getDefault();

		// Set the default locale to pre-defined locale
		Locale.setDefault(Locale.ENGLISH);

		// Set the default locale to custom locale
		locale = new Locale("en");
		Locale.setDefault(locale);
		averageButton();
	}
	private static void averageButton()
	{
		
		JLabel firstText = new JLabel("Depth: ");
		final JTextField beginField = new JTextField();
		JLabel endText = new JLabel("Width: ");
		final JTextField endField = new JTextField();
		
KeyListener enter = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				//not used.				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 10)
				{
					try {
						int begin = 0;
						int end = 0;
						if(Integer.parseInt(beginField.getText()) <= 30 || Integer.parseInt(endField.getText()) <= 30)
							{
							 begin = 50;
							 end = 50;
							 JOptionPane.showMessageDialog(frame, "The Width and Depth may not be below 31. \n\nThe simulation is started with the default values of 50 by 50.");
							}
						else
						{
							begin = Integer.parseInt(beginField.getText());
							end = Integer.parseInt(endField.getText());
						}
						new model.Simulator(begin, end);
						dialog.dispose();

						} 
						catch (Exception ee) {
							JOptionPane.showMessageDialog(frame, "Program started with the default values.");
							dialog.dispose();
							new model.Simulator();
							
						}
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				//not used.
				
			}
		};
		
		JPanel popup = new JPanel();
		beginField.addKeyListener(enter);
		endField.addKeyListener(enter);
		popup.setLayout(new GridLayout(2, 2));
		popup.add(firstText);
		popup.add(beginField);
		popup.add(endText);
		popup.add(endField);
		JPanel pop = new JPanel(new BorderLayout());
		pop.add(popup, BorderLayout.NORTH);
		JButton ok = new JButton("Ok");
		pop.add(ok, BorderLayout.SOUTH);
        
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point center = ge.getCenterPoint();
		
		dialog.add(pop);
		dialog.setLocation(center);
		dialog.setSize(300, 110);
		dialog.setVisible(true);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int begin = 0;
					int end = 0;
					if(Integer.parseInt(beginField.getText()) <= 30 || Integer.parseInt(endField.getText()) <= 30)
						{
						 begin = 50;
						 end = 50;
						 JOptionPane.showMessageDialog(frame, "The Width and Depth must be greater then 30. \n\nThe simulation is started with the default values of 50 by 50.");
						}
					else
					{
						begin = Integer.parseInt(beginField.getText());
						end = Integer.parseInt(endField.getText());
					}
					new model.Simulator(begin, end);
					dialog.dispose();

					} 
					catch (Exception ee) {
						JOptionPane.showMessageDialog(frame, "The program started with the default values.");
						dialog.dispose();
						new model.Simulator();
						
					}
				
			}
		});

}
}
