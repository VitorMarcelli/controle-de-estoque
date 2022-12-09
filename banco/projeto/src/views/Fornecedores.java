package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import models.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fornecedores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRazaoSocial;
	private JTextField txtId;
	private JTextField txtIM;
	private JTextField txtCNPJ;
	private JTextField txtRazao;
	private JTextField txtFantasia;
	private JTextField txtIE;
	private JTextField txtSite;
	private JTextField txtFone;
	private JTextField txtContato;
	private JTextField txtEmail;
	private JTextField txtCEP;
	private JTextField textEndereco;
	private JTextField textNumero;
	private JTextField textComplemento;
	private JTextField textBairro;
	private JTextField textCidade;
	private JTextField textObs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Fornecedores dialog = new Fornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Fornecedores() {
		setBounds(100, 100, 840, 549);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Fornecedor");
			lblNewLabel.setBounds(10, 10, 81, 23);
			contentPanel.add(lblNewLabel);
		}

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setBounds(82, 11, 149, 20);
		contentPanel.add(txtRazaoSocial);
		txtRazaoSocial.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 147, 54, 23);
		contentPanel.add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setBounds(34, 148, 41, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("CNPJ");
		lblNewLabel_2.setBounds(185, 147, 59, 23);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("IE");
		lblNewLabel_2_1.setBounds(409, 147, 59, 23);
		contentPanel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("IM");
		lblNewLabel_2_2.setBounds(616, 147, 59, 23);
		contentPanel.add(lblNewLabel_2_2);

		txtIM = new JTextField();
		txtIM.setColumns(10);
		txtIM.setBounds(637, 148, 175, 20);
		contentPanel.add(txtIM);

		txtCNPJ = new JTextField();
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(224, 148, 175, 20);
		contentPanel.add(txtCNPJ);

		JLabel lblNewLabel_2_3 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_2_3.setBounds(10, 193, 116, 23);
		contentPanel.add(lblNewLabel_2_3);

		txtRazao = new JTextField();
		txtRazao.setColumns(10);
		txtRazao.setBounds(94, 194, 285, 20);
		contentPanel.add(txtRazao);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnBuscar.setBounds(94, 147, 81, 23);
		contentPanel.add(btnBuscar);

		JLabel lblNewLabel_2_3_1 = new JLabel("Nome Fantasia");
		lblNewLabel_2_3_1.setBounds(389, 193, 116, 23);
		contentPanel.add(lblNewLabel_2_3_1);

		txtFantasia = new JTextField();
		txtFantasia.setColumns(10);
		txtFantasia.setBounds(486, 194, 326, 20);
		contentPanel.add(txtFantasia);

		txtIE = new JTextField();
		txtIE.setColumns(10);
		txtIE.setBounds(431, 148, 175, 20);
		contentPanel.add(txtIE);

		JLabel lblNewLabel_2_3_2 = new JLabel("Site");
		lblNewLabel_2_3_2.setBounds(10, 233, 116, 23);
		contentPanel.add(lblNewLabel_2_3_2);

		txtSite = new JTextField();
		txtSite.setColumns(10);
		txtSite.setBounds(43, 234, 220, 20);
		contentPanel.add(txtSite);

		JLabel lblNewLabel_2_3_2_1 = new JLabel("Fone");
		lblNewLabel_2_3_2_1.setBounds(273, 233, 116, 23);
		contentPanel.add(lblNewLabel_2_3_2_1);

		txtFone = new JTextField();
		txtFone.setColumns(10);
		txtFone.setBounds(313, 234, 143, 20);
		contentPanel.add(txtFone);

		JLabel lblNewLabel_2_3_2_1_1 = new JLabel("Contato");
		lblNewLabel_2_3_2_1_1.setBounds(471, 233, 116, 23);
		contentPanel.add(lblNewLabel_2_3_2_1_1);

		txtContato = new JTextField();
		txtContato.setColumns(10);
		txtContato.setBounds(523, 234, 289, 20);
		contentPanel.add(txtContato);

		JLabel lblNewLabel_2_3_2_2 = new JLabel("Email");
		lblNewLabel_2_3_2_2.setBounds(10, 271, 116, 23);
		contentPanel.add(lblNewLabel_2_3_2_2);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(53, 272, 220, 20);
		contentPanel.add(txtEmail);

		JLabel lblNewLabel_2_3_2_1_2 = new JLabel("CEP");
		lblNewLabel_2_3_2_1_2.setBounds(293, 271, 116, 23);
		contentPanel.add(lblNewLabel_2_3_2_1_2);

		txtCEP = new JTextField();
		txtCEP.setColumns(10);
		txtCEP.setBounds(330, 272, 102, 20);
		contentPanel.add(txtCEP);

		JButton btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.setBounds(452, 271, 116, 23);
		contentPanel.add(btnBuscarCEP);

		JLabel lblNewLabel_2_3_3 = new JLabel("Endere\u00E7o");
		lblNewLabel_2_3_3.setBounds(10, 308, 116, 23);
		contentPanel.add(lblNewLabel_2_3_3);

		textEndereco = new JTextField();
		textEndereco.setColumns(10);
		textEndereco.setBounds(80, 309, 250, 20);
		contentPanel.add(textEndereco);

		JLabel lblNewLabel_2_3_2_1_2_1 = new JLabel("N\u00FAmero");
		lblNewLabel_2_3_2_1_2_1.setBounds(352, 308, 116, 23);
		contentPanel.add(lblNewLabel_2_3_2_1_2_1);

		textNumero = new JTextField();
		textNumero.setColumns(10);
		textNumero.setBounds(403, 309, 102, 20);
		contentPanel.add(textNumero);

		JLabel lblNewLabel_2_3_2_1_2_1_1 = new JLabel("Complemento");
		lblNewLabel_2_3_2_1_2_1_1.setBounds(523, 308, 116, 23);
		contentPanel.add(lblNewLabel_2_3_2_1_2_1_1);

		textComplemento = new JTextField();
		textComplemento.setColumns(10);
		textComplemento.setBounds(613, 309, 149, 20);
		contentPanel.add(textComplemento);

		JLabel lblNewLabel_2_3_3_1 = new JLabel("Bairro");
		lblNewLabel_2_3_3_1.setBounds(10, 349, 116, 23);
		contentPanel.add(lblNewLabel_2_3_3_1);

		textBairro = new JTextField();
		textBairro.setColumns(10);
		textBairro.setBounds(53, 350, 250, 20);
		contentPanel.add(textBairro);

		JLabel lblNewLabel_2_3_3_1_1 = new JLabel("Cidade");
		lblNewLabel_2_3_3_1_1.setBounds(330, 349, 116, 23);
		contentPanel.add(lblNewLabel_2_3_3_1_1);

		textCidade = new JTextField();
		textCidade.setColumns(10);
		textCidade.setBounds(375, 350, 250, 20);
		contentPanel.add(textCidade);

		JLabel lblNewLabel_2_3_2_1_2_2 = new JLabel("UF");
		lblNewLabel_2_3_2_1_2_2.setBounds(646, 349, 31, 23);
		contentPanel.add(lblNewLabel_2_3_2_1_2_2);

		cboUF = new JComboBox();
		cboUF.setBounds(672, 349, 69, 22);
		contentPanel.add(cboUF);

		JLabel lblNewLabel_2_3_3_1_2 = new JLabel("Observa\u00E7\u00F5es");
		lblNewLabel_2_3_3_1_2.setBounds(10, 398, 116, 23);
		contentPanel.add(lblNewLabel_2_3_3_1_2);

		textObs = new JTextField();
		textObs.setBounds(89, 399, 367, 100);
		contentPanel.add(textObs);
		textObs.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarForne();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(508, 426, 60, 60);
		contentPanel.add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/update.png")));
		btnEditar.setBounds(602, 426, 60, 60);
		contentPanel.add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(691, 426, 60, 60);
		contentPanel.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 44, 770, 92);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}// FIM DO CONSTRUTOR
	
	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnRead;
	private JComboBox cboUF;
	private JTable table;


	private void pesquisarUsuario () {
		String read = "select * from fornecedores where idFor = ?";
		
		try {
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setString(1, txtId.getText());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				txtCNPJ.setText(rs.getString(4));
				txtIE.setText(rs.getString(5));
				//txtIM.setText(rs.getString(12));
				txtRazao.setText(rs.getString(2));
				txtRazaoSocial.setText(rs.getString(2));
				txtFantasia.setText(rs.getString(3));
				txtSite.setText(rs.getString(16));
				txtFone.setText(rs.getString(14));
				txtContato.setText(rs.getString(9));
				txtEmail.setText(rs.getString(15));
				txtCEP.setText(rs.getString(6));
				textEndereco.setText(rs.getString(7));
				textNumero.setText(rs.getString(8));
				textComplemento.setText(rs.getString(9));
				textBairro.setText(rs.getString(10));
				textCidade.setText(rs.getString(11));
				cboUF.setSelectedItem(rs.getString(12));
				txtContato.setText(rs.getString(13));
				//txtWpp.setText(rs.getString(14));
				textObs.setText(rs.getString(18));
				
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);

				
			} else {
				JOptionPane.showMessageDialog(null, "ERRO! - Fornecedor não encontrado!");
				limparCampos();
				txtId.requestFocus();
				btnAdicionar.setEnabled(true);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	private void adicionarForne() {
		
	}
	
	private void limparCampos() {
		txtId.setText(null);
		txtCNPJ.setText(null);
		txtIE.setText(null);
		txtIM.setText(null);
		txtRazao.setText(null);
		txtFantasia.setText(null);
		txtSite.setText(null);
		txtFone.setText(null);
		txtContato.setText(null);
		txtEmail.setText(null);
		txtCEP.setText(null);
		textEndereco.setText(null);
		textNumero.setText(null);
		textComplemento.setText(null);
		textBairro.setText(null);
		textCidade.setText(null);
		cboUF.setSelectedItem(null);
		textObs.setText(null);
	}
	
	
	
	private void limpar(){
		txtId.setText(null);
		txtRazaoSocial.setText(null);
		txtCNPJ.setText(null);
		txtIE.setText(null);
		txtIM.setText(null);
		txtRazao.setText(null);
		txtFantasia.setText(null);
		txtSite.setText(null);
		
		
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}




