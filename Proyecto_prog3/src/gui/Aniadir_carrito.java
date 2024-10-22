package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import domain.Cartelera;
import domain.Pelicula;
import domain.Sala;

public class Aniadir_carrito extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel pcentro,pnorte,poeste,psur,pderecha,pcombo;
	private JLabel titulo,foto;
	private JButton btncancelar,btnaniadir,btnasientos; 
	private JList<String> listapelis;
	private JComboBox<String> horarios;
	private JTextArea info;
	private JFrame vActual,vInicial;

	public Aniadir_carrito(JFrame vI,Cartelera cartelera){
		vActual = this;
		vInicial = vI;
		
		pcentro = new JPanel(new GridLayout(1,2));
		poeste = new JPanel();
		pnorte = new JPanel();
		psur = new JPanel();
		pderecha = new JPanel(new GridLayout(4,1));
		pcombo = new JPanel(new GridLayout(2,1));
		
		horarios = new JComboBox<String>();
		
		foto = new JLabel();
		titulo = new JLabel("Comprar entradas");
		
		info = new JTextArea();
		info.setEditable(false);
		
		listapelis = new JList<String>(cargarLista(cartelera));
		listapelis.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String valor = listapelis.getSelectedValue();
				if (valor!=null) {
					cargarPanelCentro(valor,cartelera);
				}
			}
		});
		
		
		btnasientos = new JButton("Elegir asiento");
		btnasientos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Ventana_elegirbutaca();
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
				vInicial.setVisible(true);
				vActual.setVisible(false);
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
		pderecha.add(pcombo);
		pderecha.add(btnaniadir);
		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setTitle("DeustoCine");
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	public String[] cargarLista(Cartelera cartelera) {
		String palabra = "";
		ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
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
	public void cargarPanelCentro(String valor,Cartelera cartelera) {
		ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
		for (Sala sala : cartelera.getCartelera()) {
			for (String string : sala.getHorarios().keySet()) {
				if (!pelis.contains(sala.getHorarios().get(string))) {
					pelis.add(sala.getHorarios().get(string));
				}
				
			}
		}
		int pos=0;
		boolean enc=false;
		while (!enc&&pos<pelis.size()) {
			if (pelis.get(pos).getTitulo().equals(valor)) {		
				
				Pelicula peli = pelis.get(pos);
				ImageIcon img = new ImageIcon(peli.getRutafoto());
				Image imagenOriginal = img.getImage();
		        Image imagenRedimensionada = imagenOriginal.getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH);
		        ImageIcon imgredimensionada = new ImageIcon(imagenRedimensionada);
				foto.setIcon(imgredimensionada);
				
				info.setText("Titulo: "+peli.getTitulo()+"\nDirigida por: "+peli.getDirector()+"\nGenero: "+
						peli.getTipo().toString()+"\nDuracion: "+peli.getDuracion()+"h");
				
				horarios.removeAllItems();
				
				String horas = "";
				for (Sala sala : cartelera.getCartelera()) {
					for (String string : sala.getHorarios().keySet()) {
						if (sala.getHorarios().get(string).equals(peli) && !horas.contains(string)) {
							horas += string+";"; 
						}
					}
				}
				String[] h = horas.split(";");
				for (int i = 0; i < h.length; i++) {
					horarios.addItem(h[i]);
				}
				pcombo.add(horarios);
				pcombo.add(btnasientos);
				enc = true;
			}else pos++;
		}
		
	}

	
}
