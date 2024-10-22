package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana_registro extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton btnregistrar,btncancelar;
	private JPanel pcentro,psur;
	private JLabel user,contrasenia,confirmar,nombre,apellido,dni,correo;
	private JTextField u,con,conf,nom,ape,dn,cor;
	private JFrame vIncial,vActual;
	
	public Ventana_registro(JFrame vI) {
		vIncial = vI;
		vActual = this;
		
		pcentro = new JPanel(new GridLayout(7, 1));
		psur = new JPanel();
		
		user = new JLabel("Introduce nombre de usuario: ");
		user.setHorizontalAlignment(SwingConstants.CENTER);
		contrasenia = new JLabel("Introduce contrasenia: ");
		contrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		confirmar = new JLabel("Confirma la contrasenia: ");
		confirmar.setHorizontalAlignment(SwingConstants.CENTER);
		nombre = new JLabel("Introduce tu nombre: ");
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		apellido = new JLabel("Introduce tu apellido: ");
		apellido.setHorizontalAlignment(SwingConstants.CENTER);
		dni = new JLabel("Introduce tu DNI: ");
		dni.setHorizontalAlignment(SwingConstants.CENTER);
		correo = new JLabel("Introduce tu correo: ");
		correo.setHorizontalAlignment(SwingConstants.CENTER);
		
		u = new JTextField();
		con = new JTextField();
		conf = new JTextField();
		nom = new JTextField();
		ape = new JTextField();
		dn = new JTextField();
		cor = new JTextField();
		nom.setPreferredSize(new Dimension(200, 30));
		btnregistrar = new JButton("Registrar");
		btncancelar = new JButton("Cancelar");
		btncancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vIncial.setVisible(true);
				vActual.setVisible(false);
			}
		});
		
		getContentPane().add(pcentro,BorderLayout.CENTER);
		getContentPane().add(psur,BorderLayout.SOUTH);
		
		psur.add(btncancelar);
		psur.add(btnregistrar);
		
		pcentro.add(nombre);
		pcentro.add(nom);
		pcentro.add(apellido);
		pcentro.add(ape);
		pcentro.add(correo);
		pcentro.add(cor);
		pcentro.add(dni);
		pcentro.add(dn);
		pcentro.add(user);
		pcentro.add(u);
		pcentro.add(contrasenia);
		pcentro.add(con);
		pcentro.add(confirmar);
		pcentro.add(conf);
		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setTitle("DeustoCine");
		setSize(900,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	

}
