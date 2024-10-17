package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Inicio_sesion  extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel nombre,contrasenia;
	private JPanel pcentro,psur,poeste,peste,pcontra,pnombre;
	private JTextField nom;
	private JPasswordField con;
	private JButton btninicio,btncierre;
	
	public Inicio_sesion() {
		setTitle("Inicio de Sesion");
		setSize(600, 400);
		setLocationRelativeTo(null);

		pcentro = new JPanel();
		psur = new JPanel();
		poeste = new JPanel();
		peste = new JPanel();
		pnombre = new JPanel(new GridLayout(2,1));
		pcontra = new JPanel(new GridLayout(2,1));
		
		nombre = new JLabel("Introduce tu nombre:");
		contrasenia = new JLabel("Introduce tu contrasenia:");

		nom = new JTextField("nombre", 20);
		con = new JPasswordField("contrasenia", 20);
		
		btncierre = new JButton("Cerrar sesion");
		btncierre.addActionListener((e)->{
			System.exit(0);
		});
		
		btninicio = new JButton("Iniciar sesion");
		btninicio.addActionListener((e)->{
			if (con.getText().isEmpty() || nom.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,"Rellena todas las partes","CUIDADO!!!",JOptionPane.WARNING_MESSAGE);
				nom.setText("");
				con.setText("");
			}
			else {
				if (nom.getText().equals("nombre") && con.getText().equals("contrasenia")) {
					new Ventana_inicial();
					dispose();
				}
			}
		});
		
		Font fuente = new Font(getName(),Font.BOLD , 16);
		nombre.setFont(fuente);
		contrasenia.setFont(fuente);
		btncierre.setFont(fuente);
		btninicio.setFont(fuente);
		
		Font otra = new Font(getName(),Font.ITALIC , 13);
		nom.setFont(otra);
		con.setFont(otra);
		
		getContentPane().add(pcentro,BorderLayout.CENTER);
		getContentPane().add(psur,BorderLayout.SOUTH);
		getContentPane().add(poeste,BorderLayout.WEST);
		getContentPane().add(peste,BorderLayout.EAST);
		
		pcentro.setLayout(new GridLayout(4,1));
		pcentro.add(nombre);
		pcentro.add(pnombre);
		pcentro.add(contrasenia);
		pcentro.add(pcontra);
		
		pnombre.add(nom);
		pcontra.add(con);
		
		
		psur.add(btninicio);
		psur.add(btncierre);		
		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);	
		
	}
	
}
