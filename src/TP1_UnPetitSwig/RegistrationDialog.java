package TP1_UnPetitSwig;

import java.awt.Font;
import javax.swing.*;

public class RegistrationDialog 
{
	public static void main(String[] args) 
	{
		/* Create text area */
		JTextArea textArea = new JTextArea(
				"",10,10
				);
				textArea.setFont(new Font("Serif", Font.ITALIC, 16));
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
	
		/* Registration Loop */
		boolean registrationOpen = true;
		while(registrationOpen)
		{	
			/* Input user Name --------*/
			String name = JOptionPane.showInputDialog(null,
				"Prénom Nom",
				"Registration",
				JOptionPane.WARNING_MESSAGE);
			
			/* Input user age--------*/
			// Generate ages tableau
			String[] ageTable = new String[120];
			for(int i = 0; i < 120; i++)
			{
				/*Add ages to tableau*/
				int auxAge = i + 1;
				ageTable[i] = "" + auxAge;
			}
			/* Generate age dialog --------*/
			String ageStr = (String) JOptionPane.showInputDialog(null,
				"Choisir votre age",
				"Registration",
				JOptionPane.QUESTION_MESSAGE,
				null,
				ageTable,
				ageTable[17]);
			// Convert String to Integer
			int age = Integer.parseInt(ageStr);
			
			/* Add another entry? --------*/
			Object[] options = { "Nouvelle Saisie", "Terminer"};
			// choice = 0 pour continuer, = 1 pour arreter
			int choice = JOptionPane.showOptionDialog(null,
				"Votre option :",
				"Choisir une option",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
			
			/* Add new data to text dialog */
			textArea.append("Nom :" + name +"\n");
			textArea.append("Age :" + age +"\n");
			
			if(choice == 1)
				registrationOpen = false;
		}
		JOptionPane.showMessageDialog(null, textArea);

		System.exit(0);
	}
}
