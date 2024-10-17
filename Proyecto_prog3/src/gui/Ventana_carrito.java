package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Butaca;
import domain.Cliente;
import domain.Entrada;

public class Ventana_carrito extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel pcentro,psur,pnorte,pfiltro,pizquierda;
	private JTextField filtrar;
	private JComboBox<String> combo;
	private JLabel titulo,filtro;
	private JButton btncartelera,btnañadir;
	private JTable tablacarrito;
	private DefaultTableModel modelocarrito;
	private JScrollPane scrollTabla;
	
	public Ventana_carrito(Cliente c){
		pfiltro = new JPanel(new GridLayout(1,2));
		pcentro = new JPanel(new BorderLayout());
		pnorte = new JPanel();
		psur = new JPanel();
		pizquierda = new JPanel();
		
		filtrar = new JTextField(20);
		
		filtro = new JLabel("Filtrar por: ");
		titulo = new JLabel("Carrito de la compra");
		
		String [] combotitles = {"NOMBRE DE PELICULA","SALA","FECHA","PRECIO"};
		combo = new JComboBox<String>(combotitles);
		combo.setSelectedItem(null);
		
		btnañadir = new JButton("Añadir al carro");
		btnañadir.addActionListener((e)-> {
			new Aniadir_carrito();
			dispose();
		});
		btncartelera = new JButton("Ver cartelera");
		btncartelera.addActionListener((e)-> {
			new Ventana_inicial();
			dispose();
		});
		
		modelocarrito = new DefaultTableModel();		
		tablacarrito = new JTable(modelocarrito);	
		tablacarrito.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if (row%2==0) {
					c.setBackground(Color.cyan);
				} else {
					c.setBackground(Color.white);
				}
				
				return c;
			}
		});;
		scrollTabla = new JScrollPane(tablacarrito);
		
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setBorder(new TitledBorder("Entradas"));
		
		String [] titulos = {"PELICULA","SALA","FILA BUTACA","COLUMNA BUTACA","BUTACA VIP","NUMERO DE ENTRADAS"};
		modelocarrito.setColumnIdentifiers(titulos);
		
		cargarTablaCarrito(c);	
		tablacarrito.setFillsViewportHeight(true);
		tablacarrito.getTableHeader().setReorderingAllowed(false);
		
		getContentPane().add(pcentro,BorderLayout.CENTER);
		getContentPane().add(pnorte,BorderLayout.NORTH);
		getContentPane().add(psur,BorderLayout.SOUTH);
		
		pnorte.add(titulo);

		
		pizquierda.add(filtro);
		pizquierda.add(filtrar);
		pizquierda.add(combo);

		filtrar.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrar(c);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrar(c);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrar(c);
			}
		});
		
		pfiltro.add(pizquierda);
		
		psur.add(btncartelera);
		psur.add(btnañadir);
		
		pcentro.add(pfiltro,BorderLayout.NORTH);
		pcentro.add(scrollTabla,BorderLayout.CENTER);		
		
		ImageIcon imagen = new ImageIcon("resource/images/icono.png");
		setIconImage(imagen.getImage());
		setTitle("DeustoCine");
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	public void cargarTablaCarrito(Cliente c) {
		
		if (c.getCarrito_de_compra()!=null) {
			modelocarrito.setRowCount(0);
			for (Entrada entrada: c.getCarrito_de_compra().keySet()) {
				Butaca butaca = entrada.getAsiento();
				boolean vip = butaca.isVip();
				if (vip) {
					Object [] fila = {entrada.getTitulo_peli(),entrada.getSala(),butaca.getFila(),butaca.getColumna(),"SI",c.getCarrito_de_compra().get(entrada)};
					modelocarrito.addRow(fila);
				} else {
					Object [] fila = {entrada.getTitulo_peli(),entrada.getSala(),butaca.getFila(),butaca.getColumna(),"NO",c.getCarrito_de_compra().get(entrada)};
					modelocarrito.addRow(fila);
				}
				
			}	
		}
				
	}
	
	public void filtrar(Cliente c) {
		if (c.getCarrito_de_compra()!=null) {
			if (combo.getSelectedItem().toString()!=null) {
				if (combo.getSelectedItem().toString().equals("NOMBRE DE PELICULA")) {
					modelocarrito.setRowCount(0);
					for (Entrada entrada: c.getCarrito_de_compra().keySet()) {
						if (entrada.getTitulo_peli().contains(filtrar.getText())) {
							Butaca butaca = entrada.getAsiento();
							boolean vip = butaca.isVip();
							if (vip) {
								Object [] fila = {entrada.getTitulo_peli(),entrada.getSala(),butaca.getFila(),butaca.getColumna(),"SI",c.getCarrito_de_compra().get(entrada)};
								modelocarrito.addRow(fila);
							} else {
								Object [] fila = {entrada.getTitulo_peli(),entrada.getSala(),butaca.getFila(),butaca.getColumna(),"NO",c.getCarrito_de_compra().get(entrada)};
								modelocarrito.addRow(fila);
							}
						}				
					}	
				} 
				else if(combo.getSelectedItem().toString().equals("SALA")) {
					modelocarrito.setRowCount(0);
					for (Entrada entrada: c.getCarrito_de_compra().keySet()) {
						if (String.valueOf(entrada.getSala()).contains(filtrar.getText())) {
							Butaca butaca = entrada.getAsiento();
							boolean vip = butaca.isVip();
							if (vip) {
								Object [] fila = {entrada.getTitulo_peli(),entrada.getSala(),butaca.getFila(),butaca.getColumna(),"SI",c.getCarrito_de_compra().get(entrada)};
								modelocarrito.addRow(fila);
							} else {
								Object [] fila = {entrada.getTitulo_peli(),entrada.getSala(),butaca.getFila(),butaca.getColumna(),"NO",c.getCarrito_de_compra().get(entrada)};
								modelocarrito.addRow(fila);
							}
						}				
					}	
				}
				else if(combo.getSelectedItem().toString().equals("FECHA")) {
					
				}
				else {
					
				}
			}
			
		}
	}	

}
