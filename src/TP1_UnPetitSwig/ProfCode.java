package TP1_UnPetitSwig;

import java.util.Calendar;
import java.util.StringTokenizer;
 
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ProfCode 
{
	public static void main(String[] args) {
		 
        String[] tab = new String[121];
        Object[] options = { "Nouvelle saisie", "Terminer"}; 
        JTextArea jt = new JTextArea(3,50);
        boolean repeter=true;
 
        for(int i=0;i<tab.length;i++)
            tab[i]=i+"";
        while(repeter){
            String s = JOptionPane.showInputDialog(null, "Préonom Nom");
 
            StringTokenizer st = new StringTokenizer(s);
 
            String prenom = st.nextToken();
            String nom = st.nextToken();
 
            String sage = (String)JOptionPane.showInputDialog(null, "Votre age", "Age", JOptionPane.QUESTION_MESSAGE, null, tab, tab[20]);
 
 
            int age = Integer.parseInt(sage);
 
 
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int adn = year-age;
 
            int choice = JOptionPane.showOptionDialog(null, "Votre option :", "Choisir une option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
 
            if (choice == 1) repeter = false;
 
            jt.append("Nom : "+nom+"\n");
            jt.append("Prénom : "+prenom+"\n");
            jt.append("Initiales : "+prenom.charAt(0)+"."+nom.charAt(0)+".\n");
            jt.append("Année de naissance :"+adn+"\n");
        }
 
        JOptionPane.showMessageDialog(null, jt);
	}
}
