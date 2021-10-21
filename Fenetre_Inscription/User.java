package Fenetre_Inscription;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;


public class User extends JFrame implements ActionListener{
	/*JLabel*/
	private JLabel inscription = new JLabel("Inscription");
	private JLabel nomComplet = new JLabel("Nom Complet* ");//nomUtilisateur
	private JLabel passWord = new JLabel("Mots de passe* ");//mots de passe
	private JLabel confPassWord = new JLabel("Confirmation Mots de passe* ");//confirme mots de passe
	private JLabel contact = new JLabel("Tel/Gmail* ");
	private JLabel type = new JLabel("Type d'inscription");
	private JLabel niveauEtude = new JLabel("Niveau Etude");
	private JLabel ecole = new JLabel("Ecole");
	private JLabel specialite = new JLabel("Specialite");
	
	/*JTextField*/
	private JTextField nomCompletField = new JTextField();//champ NomComplet
	private JTextField passWordField = new JTextField();//champ mots de passe
	private JTextField confPassWordField = new JTextField();//champ confirm Pass
	private JTextField contactField = new JTextField();// champ contact
	private JComboBox typeCombo = new JComboBox();// champ type Inscription
	private JTextField niveauEtudeField =new JTextField();
	private JTextField ecoleField =new JTextField();
	private JTextField specialiteField =new JTextField();
	/*Jpanel*/
	 JPanel panNom = new JPanel();
	 JPanel panPassWord = new JPanel();
	 JPanel panConfPassWord = new JPanel();
	 JPanel panContact = new JPanel();
	 JPanel panType = new JPanel();
	 JPanel panAll = new JPanel();
	 JPanel panComplet = new JPanel();
	 JPanel panNiveau = new JPanel();
	 JPanel panEcole = new JPanel();
	 JPanel panSpecialite = new JPanel();
	private Panneau Container = new Panneau();
	
	/*JButton */
	JButton valider = new JButton("Enregistre");
	/*Boite de Dialog */
	JOptionPane boitedialog = new JOptionPane();
	public User() {
		this.setTitle("QuizGame");
		this.setSize(1000,800);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		  miseEnFormeLabel();
		  miseEnFormeTextField();
		  miseEnFormeJPanel();
		  
		 
		  
		
		this.setContentPane(Container);
		this.setVisible(true);
	}
	// Mise En forme des champ de text
	public void miseEnFormeTextField() {
		Font police  = new Font("Ariel",Font.BOLD,18);
		Dimension dimension = new Dimension(300,40);
		nomCompletField.setPreferredSize(dimension); //Dimension du  champ de texte
		nomCompletField.setFont(police);//Style d'ecriture du champ de texte
		passWordField.setPreferredSize(dimension);
		passWordField.setFont(police);
		confPassWordField.setPreferredSize(dimension);
		confPassWordField.setFont(police);
		contactField.setPreferredSize(dimension);
		contactField.setFont(police);
		typeCombo.setPreferredSize(dimension);
		niveauEtudeField.setFont(police);
		niveauEtudeField.setPreferredSize(dimension);
		ecoleField.setFont(police);
		ecoleField.setPreferredSize(dimension);
		specialiteField.setFont(police);
		specialiteField.setPreferredSize(dimension);
		valider.setPreferredSize(dimension);
		valider.setBackground(Color.blue);
	}
	//Mise En Forme des Label
	public void miseEnFormeLabel() {
		Font police  = new Font("Ariel",Font.BOLD,15);
		
		nomComplet.setFont(police);
		nomComplet.setForeground(Color.black);
		passWord.setFont(police);
		passWord.setForeground(Color.black);
		confPassWord.setFont(police);
		confPassWord.setForeground(Color.black);
		contact.setFont(police);
		contact.setForeground(Color.black);
		type.setFont(police);
		type.setForeground(Color.black);
		niveauEtude.setFont(police);
		niveauEtude.setForeground(Color.black);
		specialite.setFont(police);
		specialite.setForeground(Color.black);
		 typeCombo.setFont(police);
		 valider.setFont(police);
		 valider.setForeground(Color.white);
		police  = new Font("Ariel",Font.BOLD,25);
		inscription.setFont(police);
		inscription.setForeground(Color.black);
		inscription.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		
	}
	public void miseEnFormeJPanel() {
		GridLayout layout = new GridLayout(2,1);
		layout.setVgap(1);
		 panNom.setLayout(layout);
		 panNom.setBackground(Color.white);
		 panNom.add(nomComplet);
		 panNom.add(nomCompletField);
		 
		 panPassWord.setLayout(layout);
		 panPassWord.setBackground(Color.white);
		 panPassWord.add(passWord);
		 panPassWord.add(passWordField);
		 
		 panConfPassWord.setLayout(layout);
		 panConfPassWord.setBackground(Color.white);
		 panConfPassWord.add(confPassWord);
		 panConfPassWord.add(confPassWordField);
		 
		 panContact.setLayout(layout);
		 panContact.setBackground(Color.white);
		 panContact.add(contact);
		 panContact.add(contactField);
		 
		 panNiveau.setLayout(layout);
		 panNiveau.setBackground(Color.white);
		 panNiveau.add(niveauEtude);
		 panNiveau.add(niveauEtudeField);
		 
		 panEcole.setLayout(layout);
		 panEcole.setBackground(Color.white);
		 panEcole.add(ecole);
		 panEcole.add(ecoleField);
		 
		 panSpecialite.setLayout(layout);
		 panSpecialite.setBackground(Color.white);
		 panSpecialite.add(specialite);
		 panSpecialite.add(specialiteField);
		 
		 panType.setLayout(layout);
		 panType.setBackground(Color.white);
		 panType.add(type);
		 panType.add(typeCombo);
		 typeCombo.addItemListener(new ItemState());
		 typeCombo.addItem("");
		 typeCombo.addItem("Etudiant");
		 typeCombo.addItem("Professeur");
		 typeCombo.setBackground(Color.white);
		/*  Tous les Jpanel dans un Jpanel  */ 
		  GridLayout gAll = new GridLayout(3,2);
		   gAll.setHgap(12);
		   panAll.setLayout(gAll);
		   panAll.add(panNom);
		   panAll.add(panPassWord);
		   panAll.add(panConfPassWord);
		   panAll.add(panContact);
		  
		   /* Action Button    */
		    valider.addActionListener(this);  
		   
		   panAll.setBackground(Color.white);
		   
		    panComplet.setBackground(Color.white);
		    
		    panComplet.setLayout(new BorderLayout(0,12));
		    panComplet.add(inscription,BorderLayout.NORTH);
		     panComplet.add(panAll,BorderLayout.CENTER);
		     panComplet.add(panType,BorderLayout.SOUTH);
		     Container.add(panComplet);
	}
	public class ItemState implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getItem().toString()=="Etudiant") {
				panAll.add(panNiveau);
				panComplet.remove(panType);
				panComplet.add(valider,BorderLayout.SOUTH);
				panComplet.revalidate();
			    panAll.revalidate();
			    
			}else if(e.getItem().toString()=="Professeur") {
				panAll.add(panEcole);
				panAll.add(panSpecialite);
				panComplet.remove(panType);
				panComplet.add(valider,BorderLayout.SOUTH);
				panComplet.revalidate();
			    panAll.revalidate();
			}
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/*Compare les deux mots de pass*/
		if(passWordField.getText().equals(confPassWordField.getText())) {
			/*les champs pour etudiant*/
			if(!niveauEtudeField.getText().isEmpty()) {
				if(!nomCompletField.getText().isEmpty()&&!passWordField.getText().isEmpty()&&!contactField.getText().isEmpty()
						 && !niveauEtudeField.getText().isEmpty()) {
				 EnregistrementUser etudiant = new EnregistrementUser(nomCompletField.getText(),passWordField.getText(),
						contactField.getText(), niveauEtudeField.getText());
			
				}else {
					boitedialog.showMessageDialog(null, "vous devez remplir tous les champs", "Information",JOptionPane.ERROR_MESSAGE);
				}
				 
			}else {/*les champs pour le prof*/
				 if(!nomCompletField.getText().isEmpty()&&!passWordField.getText().isEmpty()&&!contactField.getText().isEmpty()
						 && !ecoleField.getText().isEmpty()&&!specialiteField.getText().isEmpty()) {
					 EnregistrementUser professseur = new EnregistrementUser(nomCompletField.getText(),passWordField.getText(),
								contactField.getText(), ecoleField.getText(),specialiteField.getText());
						
				 }else {
					
					 boitedialog.showMessageDialog(null, "vous devez remplir tous les champs", "Information",JOptionPane.ERROR_MESSAGE);
				 }
			}
		}else {
			 boitedialog.showMessageDialog(null, "Vos deux mots de passe sont different", "Information",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}