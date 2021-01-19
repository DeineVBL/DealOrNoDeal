package de.swausb.dealornodeal;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import net.dv8tion.jda.api.EmbedBuilder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;

public class Fenster {

	public JFrame frmSimonsTollesFenster;
	private JTextField msg;
	private JTextField txtHierTitelEingeben;
	private JTextField txtHierAuthorDer;

	/**
	 * Create the application.
	 */
	public Fenster() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimonsTollesFenster = new JFrame();
		frmSimonsTollesFenster.setTitle("Simons tolles Fenster");
		frmSimonsTollesFenster.setBounds(100, 100, 1024, 485);
		frmSimonsTollesFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimonsTollesFenster.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Informationssystem");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(478, 11, 224, 27);
		frmSimonsTollesFenster.getContentPane().add(lblNewLabel);
		
		msg = new JTextField();
		msg.setText("Hier Nachricht eingeben...");
		msg.setBounds(125, 177, 815, 20);
		frmSimonsTollesFenster.getContentPane().add(msg);
		msg.setColumns(10);
		
		txtHierTitelEingeben = new JTextField();
		txtHierTitelEingeben.setHorizontalAlignment(SwingConstants.LEFT);
		txtHierTitelEingeben.setText("Hier Titel eingeben...");
		txtHierTitelEingeben.setBounds(125, 146, 815, 20);
		frmSimonsTollesFenster.getContentPane().add(txtHierTitelEingeben);
		txtHierTitelEingeben.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Reaktion hinzuf\u00FCgen?");
		chckbxNewCheckBox.setBounds(125, 217, 290, 23);
		frmSimonsTollesFenster.getContentPane().add(chckbxNewCheckBox);
		
		txtHierAuthorDer = new JTextField();
		txtHierAuthorDer.setText("Hier Author der Nachricht eingeben...");
		txtHierAuthorDer.setBounds(125, 115, 815, 20);
		frmSimonsTollesFenster.getContentPane().add(txtHierAuthorDer);
		txtHierAuthorDer.setColumns(10);
		
		JButton submit = new JButton("Absenden");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (msg.getText().length() != 0 && txtHierTitelEingeben.getText().length() != 0 && txtHierAuthorDer.getText().length() != 0) {
					Start.jda.getGuilds().get(0).getTextChannelById("431944911713992707").sendMessage(new EmbedBuilder().setColor(Color.yellow).setAuthor(txtHierAuthorDer.getText()).setDescription(msg.getText()).setTitle(txtHierTitelEingeben.getText()).build()).queue(message -> {
						if (chckbxNewCheckBox.isSelected()) {
							message.addReaction("💩").queue();
						}
						JOptionPane.showConfirmDialog(null, "Die Neuigkeiten wurden gepostet!", "Newsbeitrag erfolgreich gepostet!", JOptionPane.OK_OPTION);
					});;
				} else {
					JOptionPane.showConfirmDialog(null, "Du hast nicht alle Felder ausgefüllt", "Fehler", JOptionPane.OK_OPTION);
				}
			}	
		});
		submit.setBounds(125, 245, 306, 23);
		frmSimonsTollesFenster.getContentPane().add(submit);
		
	}
}
