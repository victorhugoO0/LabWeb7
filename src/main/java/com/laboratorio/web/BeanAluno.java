package com.laboratorio.web;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.laboratorio.model.Aluno;
import com.laboratorio.util.ConnectionFactory;

@ManagedBean(name = "bAluno")
@RequestScoped
public class BeanAluno implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aluno aluno = new Aluno(); // 3

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String actionUpdate() {
		Connection connection = null;
		PreparedStatement psUpdate = null;
		String sql = "UPDATE aluno SET nome = ?, idade = ?, sexo = ?, peso = ?, altura = ? WHERE matricula = ?";

		try {
			connection = ConnectionFactory.getConnection();
			psUpdate = connection.prepareStatement(sql);
			psUpdate.setString(1, aluno.getNome());
			psUpdate.setInt(2, aluno.getIdade());
			psUpdate.setString(3, aluno.getSexo());
			psUpdate.setFloat(4, aluno.getPeso());
			psUpdate.setFloat(5, aluno.getAltura());
			psUpdate.setInt(6, aluno.getMatricula());
			psUpdate.executeUpdate();
			aluno = new Aluno();

		} catch (Exception e) {
			System.out.println("------------------------------");
			System.out.println("Erro na atualização");
			System.out.println("------------------------------");
			e.printStackTrace();

		}
		return "index";
	}

	public String actionInsert() {
		Connection connection = null;
		PreparedStatement psInsert = null;
		String sql = "INSERT INTO aluno (nome,sexo,idade,peso,altura) VALUES (?,?,?,?,?)";

		try {
			connection = ConnectionFactory.getConnection();
			psInsert = connection.prepareStatement(sql);
			psInsert.setString(1, aluno.getNome());
			psInsert.setString(2, aluno.getSexo());
			psInsert.setInt(3, aluno.getIdade());
			psInsert.setFloat(4, aluno.getPeso());
			psInsert.setFloat(5, aluno.getAltura());
			psInsert.executeUpdate();
			aluno = new Aluno();

		} catch (Exception e) {
			System.out.println("------------------------------");
			System.out.println("Erro no insert");
			System.out.println("------------------------------");
			e.printStackTrace();

		}

		return "index";
	}

	public String actionDelete() {
		Connection connection = null;
		PreparedStatement psDelete = null;
		String sql = "DELETE FROM aluno WHERE matricula = ?";

		try {
			connection = ConnectionFactory.getConnection();
			psDelete = connection.prepareStatement(sql);
			psDelete.setInt(1, aluno.getMatricula());
			psDelete.executeUpdate();
			aluno = new Aluno();

		} catch (Exception e) {
			System.out.println("------------------------------");
			System.out.println("Erro na exclusão");
			System.out.println("------------------------------");
			e.printStackTrace();
		}

		return "index";
	}

	public String actionSelectByMatricula() {
		Connection connection = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "SELECT matricula, nome, idade, sexo, peso, altura FROM aluno WHERE matricula = ?";

		try {
			connection = ConnectionFactory.getConnection();
			psSelect = connection.prepareStatement(sql);
			psSelect.setInt(1, aluno.getMatricula());
			rsSelect = psSelect.executeQuery();
			if (rsSelect.next()) {
				aluno = new Aluno();
				aluno.setMatricula(rsSelect.getInt("matricula"));
				aluno.setNome(rsSelect.getString("nome"));
				aluno.setIdade(rsSelect.getInt("idade"));
				aluno.setSexo(rsSelect.getString("sexo"));
				aluno.setPeso(rsSelect.getFloat("peso"));
				aluno.setAltura(rsSelect.getFloat("altura"));

			}

		} catch (Exception e) {
			System.out.println("------------------------------");
			System.out.println("Erro na seleção por matricula");
			System.out.println("------------------------------");
			e.printStackTrace();

		}

		return null;
	}

	public List<Aluno> getListagemAlunos() {
		Connection conexao = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "SELECT matricula, nome, idade, sexo, peso, altura FROM aluno ORDER BY nome";

		List<Aluno> lsAlunos = null;
		try {
			conexao = ConnectionFactory.getConnection();
			psSelect = conexao.prepareStatement(sql);
			rsSelect = psSelect.executeQuery();
			lsAlunos = new ArrayList<Aluno>();
			while (rsSelect.next()) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(rsSelect.getInt("matricula"));
				aluno.setNome(rsSelect.getString("nome"));
				aluno.setIdade(rsSelect.getInt("idade"));
				aluno.setSexo(rsSelect.getString("sexo"));
				aluno.setPeso(rsSelect.getFloat("peso"));
				aluno.setAltura(rsSelect.getFloat("altura"));
				lsAlunos.add(aluno);

			}
		} catch (Exception e) {
			System.out.println("------------------------------");
			System.out.println("Erro no select");
			System.out.println("------------------------------");
			e.printStackTrace();
		}
		return lsAlunos;
	}

}
