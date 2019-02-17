package secretmessages;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.awt.Color;

public class SecretMessages extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtKey;
	private JTextArea txtInput;
	private JTextArea txtOutput;
	private JSlider sldKey;
	private JTextArea txtInstructions;
	
	public String encode(String message, int k)
	{
		String out = "";
		char key = (char)k;
		
		for(int x = 0; x < message.length(); x++)
		{
			char in = message.charAt(x);
			if(in >= 'A' && in <= 'Z')
			{
				in += key;
				if(in > 'Z')
					in -= 26;
				if(in < 'A')
					in += 26;
			}
			if(in >= 'a' && in <= 'z')
			{
				in += key;
				if(in > 'z')
					in -= 26;
				if(in < 'a')
					in += 26;
			}
			if(in >= '0' && in <= '9')
			{
				in += (k % 10);
				if(in > '9')
					in -= 10;
				if( in < '0')
					in += 10;
			}
			out+=in;
		}
		return out;
	}
	
	public SecretMessages() 
	{
		setBackground(new Color(95, 158, 160));
		setLayout(null);
		
		txtInput = new JTextArea();
		txtInput.setLineWrap(true);
		txtInput.setWrapStyleWord(true);
		txtInput.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtInput.setBounds(10, 83, 500, 110);
		add(txtInput);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setForeground(new Color(240, 255, 240));
		lblKey.setFont(new Font("Dialog", Font.BOLD, 16));
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKey.setBounds(220, 213, 38, 31);
		add(lblKey);
		
		txtKey = new JTextField();
		txtKey.setForeground(new Color(95, 158, 160));
		txtKey.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setBounds(260, 214, 46, 28);
		add(txtKey);
		txtKey.setColumns(3);
		
		JButton btnEncodeDecode = new JButton("Encode/Decode");
		btnEncodeDecode.setForeground(new Color(95, 158, 160));
		btnEncodeDecode.setBackground(new Color(255, 255, 255));
		btnEncodeDecode.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEncodeDecode.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				sldKey.setValue(Integer.parseInt(txtKey.getText()));
				// Get the message from txtInput
				String message = txtInput.getText();
				
				// Get the key from txtKey
				int key = Integer.parseInt(txtKey.getText());
				
				// Encode the message with that key
				String output = encode(message, key);
				
				// Show encoded message in txtOutput
				txtOutput.setText(output);
			}
		});
		btnEncodeDecode.setBounds(334, 213, 176, 31);
		add(btnEncodeDecode);
		
		txtOutput = new JTextArea();
		txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtOutput.setBounds(10, 264, 500, 110);
		add(txtOutput);
		setPreferredSize(new Dimension(520, 385));
		
		sldKey = new JSlider();
		sldKey.setForeground(new Color(240, 255, 240));
		sldKey.setBackground(new Color(95, 158, 160));
		sldKey.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				// Change txtKey field to match slider value
				txtKey.setText("" + sldKey.getValue());
				
				// Get the message from txtInput
				String message = txtInput.getText();
				
				// Get the key from txtKey
				int key = Integer.parseInt(txtKey.getText());
				
				// Encode the message with that key
				String output = encode(message, key);
				
				// Show encoded message in txtOutput
				txtOutput.setText(output);
			}
		});
		sldKey.setSnapToTicks(true);
		sldKey.setPaintTicks(true);
		sldKey.setPaintLabels(true);
		sldKey.setMinorTickSpacing(1);
		sldKey.setMajorTickSpacing(13);
		sldKey.setValue(0);
		sldKey.setMinimum(-13);
		sldKey.setMaximum(13);
		sldKey.setBounds(10, 206, 200, 45);
		add(sldKey);
		
		txtInstructions = new JTextArea();
		txtInstructions.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtInstructions.setForeground(new Color(255, 255, 255));
		txtInstructions.setBackground(new Color(95, 158, 160));
		txtInstructions.setEditable(false);
		txtInstructions.setLineWrap(true);
		txtInstructions.setWrapStyleWord(true);
		txtInstructions.setText("Enter a message in the text field below. Use the slider or enter a key between -13 and 13 to encode or decode the message. ");
		txtInstructions.setBounds(91, 11, 339, 61);
		add(txtInstructions);
	}

	public static void main(String[] args) 
	{
		// Set up a window JFrame for the app
		JFrame frame = new JFrame("Laura's Secret Message App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Add the encoder panel to the frame
		frame.getContentPane().add(new SecretMessages());
		
		// Prepare and show the frame
		frame.pack();
		frame.setVisible(true);

	}
}
