package br.com.fiap.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.CategoriaModel;

@Repository
public class CategoriaRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String FIND_ALL = "SELECT * FROM TB_CATEGORIA";
	private static final String FIND_BY_ID = "SELECT * FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";
	private static final String SAVE = "INSERT INTO TB_CATEGORIA (NOME_CATEGORIA) VALUES (?)";
	private static final String UPDATE = "UPDATE TB_CATEGORIA SET NOME_CATEGORIA = ? WHERE ID_CATEGORIA = ?";
	private static final String DELETE = "DELETE FROM TB_CATEGORIA WHERE ID_CATEGORIA = ?";

	public List<CategoriaModel> findAll() {

		List<CategoriaModel> categorias = jdbcTemplate.query(FIND_ALL,
				new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class));
		return categorias;
	}

	public CategoriaModel findById(long id) {

		CategoriaModel categoriaModel = jdbcTemplate.queryForObject(FIND_BY_ID,
				new BeanPropertyRowMapper<CategoriaModel>(CategoriaModel.class), id);

		return categoriaModel;
	}

	public void save(CategoriaModel categoriaModel) {
		jdbcTemplate.update(SAVE, categoriaModel.getNomeCategoria());
	}
	
	public void update(CategoriaModel categoriaModel) {
		jdbcTemplate.update(UPDATE, 
							categoriaModel.getNomeCategoria(),
							categoriaModel.getIdCategoria());
	}
	
	public void delete(long id) {
		jdbcTemplate.update(DELETE, id);
	}
}
