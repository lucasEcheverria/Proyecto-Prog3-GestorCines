package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Cartelera;
import domain.Pelicula;
import domain.Sala;

public class Aniadir_carrito extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel pcentro,pnorte,poeste,psur,pderecha;
	private JLabel titulo,foto,info;
	private JButton btncancelar,btnaniadir; 
	private JList<String> listapelis;
	private JComboBox<String> horarios;
	private JSlider precios;

	public Aniadir_carrito(){
		pcentro = new JPanel(new GridLayout(1,2));
		poeste = new JPanel();
		pnorte = new JPanel();
		psur = new JPanel();
		pderecha = new JPanel(new GridLayout(4,1));
		
		horarios = new JComboBox<String>();
		
		foto = new JLabel();
		info = new JLabel();
		titulo = new JLabel("Comprar entradas");
		
		listapelis = new JList<String>(cargarLista());
		listapelis.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String valor = listapelis.getSelectedValue();
				if (valor!=null) {
					cargarPanelCentro(valor);
				}
			}
		});
		
		btnaniadir = new JButton("Aniadir al carrito");
		btnaniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres aniadir?");
				
			}
		});
		btncancelar = new JButton("Cancelar");
		btncancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Ventana_inicial();
				dispose();
			}
		});
		
		getContentPane().add(pcentro,BorderLayout.CENTER);
		getContentPane().add(poeste,BorderLayout.WEST);
		getContentPane().add(pnorte,BorderLayout.NORTH);
		getContentPane().add(psur,BorderLayout.SOUTH);
		
		pnorte.add(titulo);
		psur.add(btncancelar);
		poeste.add(listapelis);
		
		pcentro.add(foto);
		pcentro.add(pderecha);
		
		pderecha.add(info);
		pderecha.add(horarios);
		pderecha.add(btnaniadir);
		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setTitle("DeustoCine");
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	public String[] cargarLista() {
		String palabra = "";
		ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
		Cartelera cartelera = new Cartelera(null);
		cartelera.setCartelera(cartelera.cargarCartelera());;
		for (Sala sala : cartelera.getCartelera()) {
			for (String string : sala.getHorarios().keySet()) {
				if (!pelis.contains(sala.getHorarios().get(string))) {
					pelis.add(sala.getHorarios().get(string));
					palabra = palabra+sala.getHorarios().get(string).getTitulo()+";";
				}
				
			}
		}
		String[] l = palabra.split(";");
		return l;
	}
	public void cargarPanelCentro(String valor) {
		ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
		Cartelera cartelera = new Cartelera(null);
		cartelera.setCartelera(cartelera.cargarCartelera());;
		for (Sala sala : cartelera.getCartelera()) {
			for (String string : sala.getHorarios().keySet()) {
				if (!pelis.contains(sala.getHorarios().get(string))) {
					pelis.add(sala.getHorarios().get(string));
				}
				
			}
		}
		int pos=0;
		boolean enc=false;
		System.out.println("SI");
		while (!enc&&pos>pelis.size()) {
			if (pelis.get(pos).getTitulo().equals(valor)) {		
				
				Pelicula peli = pelis.get(pos);
				ImageIcon img = new ImageIcon(peli.getRutafoto());
				foto.setIcon(img);
				
				info.setText("Titulo: "+peli.getTitulo()+"\tDirigida por: "+peli.getDirector()+"\tGenero: "+
						peli.getTipo().toString()+"\tDuracion: "+peli.getDuracion()+"h");
				
				String horas = "";
				Cartelera car = new Cartelera(null);
				car.setCartelera(car.cargarCartelera());;
				
				for (Sala sala : car.getCartelera()) {
					for (String string : sala.getHorarios().keySet()) {
						if (sala.getHorarios().containsValue(peli)) {
							horas += string+";";
						}
					}
				}
				System.out.println("SI");
				String[] h = horas.split(";");
				for (int i = 0; i < h.length; i++) {
					horarios.addItem(h[i]);
					System.out.println("SI");
				}
				System.out.println("SI");

				enc = true;
			}else pos++;
		}
		
	}
}
