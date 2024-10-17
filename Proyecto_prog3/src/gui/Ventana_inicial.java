package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import domain.Cartelera;
import domain.Cliente;
import domain.Pelicula;
import domain.Sala;

public class Ventana_inicial extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel pcentro,psur,pnorte;
	private JLabel titulo;
	private JButton carrito, cerrarsesion;
	private JScrollPane scroll;
	
	
	public Ventana_inicial() {
		pcentro = new JPanel();
		psur = new JPanel();
		pnorte = new JPanel();
		
		carrito = new JButton("Ver carrito");
		cerrarsesion = new JButton("Cerrar Sesion");
		
		titulo = new JLabel("Cartelera");
		cargarPelis();
		
		Font fuente = new Font(getName(),Font.BOLD , 30);
		titulo.setFont(fuente);
		
		getContentPane().add(psur,BorderLayout.SOUTH);
		getContentPane().add(pnorte,BorderLayout.NORTH);

		pnorte.add(titulo);
		pnorte.setBackground(Color.LIGHT_GRAY);
		
		Font fuentebtn = new Font(getName(),Font.BOLD , 16);
		psur.add(carrito);
		carrito.setFont(fuentebtn);
		carrito.addActionListener(e -> {
			Cliente c = new Cliente("nombre", "contrasenia", "Markel", "Urquiza", "791258886k", "markel.urquiza@opendeusto.es", null);
			c.setCarrito_de_compra(c.cargarEntradas());
			new Ventana_carrito(c);
			dispose();
		});
		psur.add(cerrarsesion);
		cerrarsesion.setFont(fuentebtn);
		cerrarsesion.addActionListener(e -> {
			System.exit(0);
		});

		pcentro.setLayout(new GridLayout(0,4));
		scroll = new JScrollPane(pcentro);
		getContentPane().add(scroll,BorderLayout.CENTER);

		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setTitle("DeustoCine");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
	}
	public void cargarPelis() {
		
		ArrayList<Pelicula> pelis = new ArrayList<>();
		Cartelera cartelera = new Cartelera(null);
		cartelera.setCartelera(cartelera.cargarCartelera());
		for (Sala sala : cartelera.getCartelera()) {
			for(String date : sala.getHorarios().keySet()) {
				if (!pelis.contains(sala.getHorarios().get(date))) {
					JPanel peli = new JPanel();
					ImageIcon img = new ImageIcon(sala.getHorarios().get(date).getRutafoto());
					peli.add(new JLabel(img),BorderLayout.CENTER);
					peli.add(new JLabel(sala.getHorarios().get(date).getTitulo()),BorderLayout.SOUTH);
					pcentro.add(peli);
					pelis.add(sala.getHorarios().get(date));
				}
			}
		}
		
	}
	

}
