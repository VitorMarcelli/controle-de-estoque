package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;

import models.DAO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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
	public Usuarios() {
		setResizable(false);
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setTitle("Usu\u00E1rios");
		setBounds(100, 100, 534, 318);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(387, 27, 26, 14);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setBounds(412, 24, 53, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnBuscar.setBounds(238, 11, 48, 48);
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(31, 75, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(87, 72, 378, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(31, 27, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtLogin = new JTextField();
		txtLogin.setBounds(87, 24, 138, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(31, 123, 46, 14);
		getContentPane().add(lblNewLabel_3);

		btnAdicionar_1 = new JButton("");
		btnAdicionar_1.setEnabled(false);
		btnAdicionar_1.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnAdicionar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnAdicionar_1.setBounds(208, 193, 55, 55);
		getContentPane().add(btnAdicionar_1);

		JButton btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Verificar se o CheckBox esta selecionado
				//Para verificar se não esta selecionado, use "!" (NOT)
				if (chckbxAlterarSenha.isSelected()) {
					alterarUsuarioSenha();
				} else {
					alterarUsuario();
				}
				limpar();
			}
		});
		btnEditar.setBounds(292, 193, 55, 55);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setBounds(377, 193, 55, 55);
		getContentPane().add(btnExcluir);

		RestrictedTextField usuario = new RestrictedTextField(txtUsuario);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(31, 167, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(87, 166, 89, 22);
		getContentPane().add(cboPerfil);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(87, 120, 210, 20);
		getContentPane().add(txtSenha);
		
		chckbxAlterarSenha = new JCheckBox("Alterar a Senha");
		chckbxAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fazer o check na caixa Jcheckbox
				txtSenha.setEditable(true);
				txtSenha.setText(null);
				txtSenha.requestFocus();
				txtSenha.setBackground(Color.ORANGE);
			}
		});
		chckbxAlterarSenha.setVisible(false);
		chckbxAlterarSenha.setBounds(325, 119, 119, 23);
		getContentPane().add(chckbxAlterarSenha);

		usuario.setOnlyText(true);
		usuario.setLimit(50);
		usuario.setAcceptSpace(true);

	}// fim do construtor

	DAO dao = new DAO();
	private JLabel lblStatus;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Metodo responsavel pelo status
	 */

	private void status() {
		// System.out.println("teste do status");
		try {
			// Abrir a conexão
			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("erro de conexão");
			} else {
				System.out.println("banco conectado");
			}
			// Fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	} // FIM DO STATUS

	/**
	 * Pesquisa do usuario por id
	 */

	private void pesquisarUsuario() {
		// System.out.println("Teste do botão pesquisar");
		String read = "select * from usuarios where login = ?";

		try {

			Connection con = dao.conectar();

			PreparedStatement pst = con.prepareStatement(read);

			pst.setString(1, txtLogin.getText());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				txtId.setText(rs.getString(1));
				txtUsuario.setText(rs.getString(2));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
				cboPerfil.setSelectedItem(rs.getString(5));
				
				
				//exibir a caixa CheckBox
				
				chckbxAlterarSenha.setVisible(true);
				
				//desativar a edição da senha
				
				txtSenha.setEditable(false);

				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
				limparCampos();
				txtUsuario.requestFocus();
				btnAdicionar.setEnabled(true);
			}

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}// fim do metodo pesquisar usuario

	void adicionarContato() {

		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!");

			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login!");

			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha!");
		}

		else {
			String create = "insert into usuarios (usuario, login, senha, perfil) values (?, ?, ?, ?)";

			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());

				// Captura segura de senha

				String capturaSenha = new String(txtSenha.getPassword());
				pst.setString(3, capturaSenha);

				pst.setString(4, cboPerfil.getSelectedItem().toString());

				// execução e confirmação da query

				int confirma = pst.executeUpdate();

				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");
					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "ERRO - USUARIO NÃO ADICIONADO!");
				}

				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "ERRO! Usuário Existente - Tente outro Login!");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	} // FIM DO METODO ADICIONAR USUARIO

	private void excluirUsuario() {

		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where id = ?; ";

			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());

				int confirmaExcluir = pst.executeUpdate();

				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Contato excluido com sucesso!");
				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} // FIM DO METODO EXCLUIR

	private void alterarUsuario() {

		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!");

			txtUsuario.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login!");

			txtLogin.requestFocus();
		} else {
			String update = "update usuarios set usuario = ?, login = ?, perfil = ? where id = ?;";

			try {

				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());

				int confirma = pst.executeUpdate();

				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato Atualizado com sucesso!");

					limpar();
				} else {
					JOptionPane.showMessageDialog(null, " ERRO! Contato não atualizado!");
				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} // FIM DO METODO ALTERAR
	
	private void alterarUsuarioSenha() {

		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!");

			txtUsuario.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login!");

			txtLogin.requestFocus();
		} else {
			String update = "update usuarios set usuario = ?, login = ?, senha = ?, perfil = ? where id = ?;";

			try {

				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());

				String capturaSenha = new String(txtSenha.getPassword());
				pst.setString(3, capturaSenha);

				pst.setString(4, cboPerfil.getSelectedItem().toString());

				pst.setString(5, txtId.getText());

				int confirma = pst.executeUpdate();

				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato Atualizado com sucesso!");

					limpar();
				} else {
					JOptionPane.showMessageDialog(null, " ERRO! Contato não atualizado!");
				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} // FIM DO METODO ALTERAR

	private void limparCampos() {
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		txtSenha.setBackground(Color.WHITE);
		cboPerfil.setSelectedItem("");
		chckbxAlterarSenha.setSelected(false); // desmarcar a caixa check
		chckbxAlterarSenha.setVisible(false);
		txtSenha.setEditable(true);
	}

	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JButton btnAdicionar_1;
	private JComboBox cboPerfil;
	private JPasswordField txtSenha;
	private JCheckBox chckbxAlterarSenha;

	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		btnAdicionar_1.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}// fim do código
