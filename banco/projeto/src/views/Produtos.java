package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import models.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Produtos extends JDialog {
	private JTextField textCodigo;
	private JTextField textFornecedor;
	private JTextField textId;
	private JTable table;
	private JTextField textProduto;
	private JTextField textCusto;
	private JTextField textFabricante;
	private JTextField textEstoquemin;
	private JTextField textEstoque;
	private JTextField textLocal;
	private JTextField textBarcode;
	private JLabel lblNewLabel_3;
	private JDateChooser dateEntrada;
	private JDateChooser dateValidade;
	private JTextField textLucro;
	private JComboBox cboUnidade;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnDeletar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Produtos() {
		setTitle("Produtos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 821, 488);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		lblNewLabel.setBounds(10, 11, 64, 45);
		getContentPane().add(lblNewLabel);
		
		textBarcode = new JTextField();
		textBarcode.setBounds(84, 23, 232, 20);
		getContentPane().add(textBarcode);
		textBarcode.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Código");
		lblNewLabel_1.setBounds(20, 79, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		textCodigo = new JTextField();
		textCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProdutoBarcode();
				}
		});
		textCodigo.setBounds(84, 76, 115, 20);
		getContentPane().add(textCodigo);
		textCodigo.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProduto();
			}
		});
		btnPesquisar.setBounds(212, 75, 104, 23);
		getContentPane().add(btnPesquisar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Fornecedores", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(326, 11, 467, 159);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textFornecedor = new JTextField();
		textFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarFornecedor();
			}
		});
		textFornecedor.setBounds(20, 21, 204, 20);
		panel.add(textFornecedor);
		textFornecedor.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Produtos.class.getResource("/img/search.png")));
		lblNewLabel_2.setBounds(234, 17, 24, 24);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(357, 24, 46, 14);
		panel.add(lblNewLabel_3);
		
		textId = new JTextField();
		textId.setColumns(10);
		textId.setBounds(378, 21, 46, 20);
		panel.add(textId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 52, 426, 96);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"ID", "Fornecedor"
			}
		));
		scrollPane.setViewportView(table);
		
		textProduto = new JTextField();
		textProduto.setColumns(10);
		textProduto.setBounds(84, 120, 232, 20);
		getContentPane().add(textProduto);
		
		JLabel lblNewLabel_1_1 = new JLabel("Produto");
		lblNewLabel_1_1.setBounds(20, 123, 46, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Descrição");
		lblNewLabel_1_1_1.setBounds(20, 199, 64, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		textaDescricao = new JTextArea();
		textaDescricao.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textaDescricao.setFont(new Font("Monospaced", Font.BOLD, 14));
		textaDescricao.setBounds(84, 194, 232, 106);
		getContentPane().add(textaDescricao);
		
		JLabel lblNewLabel_4 = new JLabel("Entrada");
		lblNewLabel_4.setBounds(348, 199, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		dateEntrada = new JDateChooser();
		dateEntrada.setBounds(348, 220, 141, 20);
		getContentPane().add(dateEntrada);
		
		JLabel lblNewLabel_4_1 = new JLabel("Validade");
		lblNewLabel_4_1.setBounds(530, 199, 54, 14);
		getContentPane().add(lblNewLabel_4_1);
		
		dateValidade = new JDateChooser();
		dateValidade.setBounds(530, 220, 141, 20);
		getContentPane().add(dateValidade);
		
		JLabel lblNewLabel_4_2 = new JLabel("Custo");
		lblNewLabel_4_2.setBounds(337, 270, 46, 14);
		getContentPane().add(lblNewLabel_4_2);
		
		textCusto = new JTextField();
		textCusto.setColumns(10);
		textCusto.setBounds(382, 267, 107, 20);
		getContentPane().add(textCusto);
		
		textLucro = new JTextField();
		textLucro.setColumns(10);
		textLucro.setBounds(564, 267, 86, 20);
		getContentPane().add(textLucro);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Lucro");
		lblNewLabel_4_2_1.setBounds(518, 270, 46, 14);
		getContentPane().add(lblNewLabel_4_2_1);
		
		JLabel lblNewLabel_5 = new JLabel("%");
		lblNewLabel_5.setBounds(660, 270, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fabricante");
		lblNewLabel_6.setBounds(20, 323, 64, 14);
		getContentPane().add(lblNewLabel_6);
		
		textFabricante = new JTextField();
		textFabricante.setColumns(10);
		textFabricante.setBounds(84, 320, 232, 20);
		getContentPane().add(textFabricante);
		
		JLabel lblNewLabel_6_1 = new JLabel("Estoque");
		lblNewLabel_6_1.setBounds(30, 354, 64, 14);
		getContentPane().add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Estoque Minimo");
		lblNewLabel_6_1_1.setBounds(158, 354, 97, 14);
		getContentPane().add(lblNewLabel_6_1_1);
		
		textEstoquemin = new JTextField();
		textEstoquemin.setColumns(10);
		textEstoquemin.setBounds(252, 351, 64, 20);
		getContentPane().add(textEstoquemin);
		
		textEstoque = new JTextField();
		textEstoque.setColumns(10);
		textEstoque.setBounds(84, 351, 64, 20);
		getContentPane().add(textEstoque);
		
		JLabel lblNewLabel_6_1_2 = new JLabel("Unidade");
		lblNewLabel_6_1_2.setBounds(20, 400, 64, 14);
		getContentPane().add(lblNewLabel_6_1_2);
		
		cboUnidade = new JComboBox();
		cboUnidade.setModel(new DefaultComboBoxModel(new String[] {"", "UN", "PC", "CX", "KG", "g", "M", "CM"}));
		cboUnidade.setBounds(84, 396, 64, 22);
		getContentPane().add(cboUnidade);
		
		JLabel lblNewLabel_6_1_3 = new JLabel("Local");
		lblNewLabel_6_1_3.setBounds(158, 400, 64, 14);
		getContentPane().add(lblNewLabel_6_1_3);
		
		textLocal = new JTextField();
		textLocal.setColumns(10);
		textLocal.setBounds(191, 397, 125, 20);
		getContentPane().add(textLocal);
		
		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirProduto();
				}
		});
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxadd.png")));
		btnAdicionar.setBounds(382, 354, 68, 65);
		getContentPane().add(btnAdicionar);
		
		btnEditar = new JButton("");
		btnEditar.setToolTipText("Editar");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxupdate.png")));
		btnEditar.setBounds(516, 354, 68, 65);
		getContentPane().add(btnEditar);
		
		btnDeletar = new JButton("");
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletar.setContentAreaFilled(false);
		btnDeletar.setBorderPainted(false);
		btnDeletar.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxdel.png")));
		btnDeletar.setBounds(645, 354, 68, 65);
		getContentPane().add(btnDeletar);

	}// fim do construtor
	
	DAO dao = new DAO();
	private JTextArea textaDescricao;
	
	private void pesquisarFornecedor() {
		String read3 = "select idfor as ID, fantasia as Fornecedor from fornecedores where fantasia like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read3);
			pst.setString(1, textFornecedor.getText() + "%"); // Atenção "%"
			ResultSet rs = pst.executeQuery();
			//Uso da biblioteca rs2xml para "popular" a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private void pesquisarProduto( ) {
		String read = "select * from produtos where barcode = ?";
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, textCodigo.getText());
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				textBarcode.setText(rs.getString(2));
				textProduto.setText(rs.getString(3));
				textaDescricao.setText(rs.getString(4));
				textFabricante.setText(rs.getString(5));
				textEstoque.setText(rs.getString(8));
				textEstoquemin.setText(rs.getString(9));
				cboUnidade.setSelectedItem(rs.getString(10));
				textLocal.setText(rs.getString(11));
				textId.setText(rs.getString(12));
				
				//formatacao da data recebida pelo MySQL
				//Jcalendar - formatacao para exibicao
				String setarData = rs.getString(6);
				//apoio a logica
				
				Date dataFormatada = new SimpleDateFormat("yyy-MM-dd").parse(setarData);
				dateEntrada.setDate(dataFormatada);
				String setarData2 = rs.getString(7);
				Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarData2);
				dateValidade.setDate(dataFormatada2);
				textCusto.setText(rs.getString(12));
				textLucro.setText(rs.getString(13));
				
			} else {
				JOptionPane.showMessageDialog(null, "Produto não Cadastrado...");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void pesquisarProdutoBarcode() {
		//System.out.println("teste bot�o pesquisar produto");
		String read = "select * from produtos where barcode = ?";		
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, textBarcode.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				textCodigo.setText(rs.getString(1));
				textProduto.setText(rs.getString(3));
				textaDescricao.setText(rs.getString(4));
				textFabricante.setText(rs.getString(5));
				textEstoque.setText(rs.getString(8));
				textEstoquemin.setText(rs.getString(9));
				cboUnidade.setSelectedItem(rs.getString(10));
				textLocal.setText(rs.getString(11));
				textId.setText(rs.getString(14));
				//formata��o da data recebida pelo MySQL
				// JCalendar - formata��o para exibi��o
				String setarData = rs.getString(6);
				//apoio a l�gica
				//System.out.println(setarData);
				Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
				dateEntrada.setDate(dataFormatada);
				String setarData2 = rs.getString(7);
				Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarData2);
				dateValidade.setDate(dataFormatada2);
				textCusto.setText(rs.getString(12));
				textLucro.setText(rs.getString(13));
			} else {
				JOptionPane.showMessageDialog(null, "Produto n�o cadastrado");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void inserirProduto() {
		//validacao
		//....
		
		String insert = "insert into produtos (barcode, produto, descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(insert);
			pst.setString(1, textBarcode.getText());
			pst.setString(2, textProduto.getText());
			pst.setString(3, textaDescricao.getText());
			pst.setString(4, textFabricante.getText());
			//Formatar o valor do Jcalendar para insercao correta no banco
			SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
			String dataFormatada = formatador.format(dateValidade.getDate());
			
			pst.setString(5, dataFormatada);
			pst.setString(6, textEstoque.getText());
			pst.setString(7, textEstoquemin.getText());
			pst.setString(8, cboUnidade.getSelectedItem().toString());
			pst.setString(9, textLocal.getText());
			pst.setString(10, textCusto.getText());
			pst.setString(11, textLucro.getText());
			pst.setString(12, textId.getText());
			
			int confirma = pst.executeUpdate();
			
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "ERRO -- Produto não cadastrado!");
			}
			con.close();
			
			
			
			
			} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Metodo usado para setar os campos do formulário com os dados da tabela
	 */
	private void setarCampos() {
		int setar = table.getSelectedRow();
		textId.setText(table.getModel().getValueAt(setar, 0).toString());
		
	}
}// fim do codigo
