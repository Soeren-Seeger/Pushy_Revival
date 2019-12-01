package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Succsess extends Thread  implements ActionListener{

	JFrame succsessInfo = new JFrame ();	
	JButton hauptmen� = new JButton("zum Hauptmen�");    //Button im "Level geschafft" Fenster (Global, da mit ActionListener)
	JButton next = new JButton("n�chstes Level");
	int thisLevel = 0;
	JFrame Playground;									//JFrame in dem die Referenz des aktuellen Level-Playgrunds espeichert wird

	
	
	 public void run(int thisLevel, JFrame Playground) {
		this.Playground = Playground;						//Da �ber das Succsess-Fenster der Playground geschlossen wird muss die Referenz �bergeben werden
		
		this.thisLevel = thisLevel;
		PlayerStatistic playerStat = new PlayerStatistic();  //Objekt des Schreiben/Laden des Statistik in Textdokument
		try {
			playerStat.save((thisLevel+1));						//Schreibe in Textdokument, dass n�chstes Level gespielt werden darf!
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		succsessInfo.setSize(500, 500);						// Erfolg-Fenster Einstellungen
		succsessInfo.setLocation(500, 500);
		succsessInfo.setLayout(new GridLayout(4,1));
		ImageIcon topIcon = new ImageIcon("resources/images/Pushy_w.png");   //JFrame Icon und �berschrift
		succsessInfo.setIconImage(topIcon.getImage());
		succsessInfo.setTitle("Geschafft !!!");
		
		
		JLabel info1 = new JLabel("Herzlichen Gl�ckwunsch !");
		JLabel info2 = new JLabel ("Du hast Level " + thisLevel + " bestanden ;)");
		next.setText("Weiter mit Level "+ (thisLevel +1));
		succsessInfo.add(info1);
		succsessInfo.add(info2);
		succsessInfo.add(hauptmen�);
		hauptmen�.addActionListener(this);
		succsessInfo.add(next);
		next.addActionListener(this);			//ActionListener F�r Hauptmen� + n�. Level
		
		succsessInfo.setVisible(true);	
	
	}
	
	 
	 
	public void actionPerformed(ActionEvent e) {				//ActionListener im Erfolgfenster
		if (e.getSource() == hauptmen� ) {
			Playground.setVisible(false);
			succsessInfo.setVisible(false);
		}
		if (e.getSource() == next) {
			Playground.setVisible(false);
			succsessInfo.setVisible(false);
			Playground play = new Playground((thisLevel+1));		//N�chstes Level
			
		}
	}
	

}
