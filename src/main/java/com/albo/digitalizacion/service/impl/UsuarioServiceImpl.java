package com.albo.digitalizacion.service.impl;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.albo.digitalizacion.dao.IUsuarioDAO;
import com.albo.digitalizacion.model.Usuario;
import com.albo.digitalizacion.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDao;

	@Override
	public Usuario saveOrUpdate(Usuario usuario) {
		return usuarioDao.saveAndFlush(usuario);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			usuarioDao.deleteById(id);
			jsonObject.put("message", "Usuario deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioDao.findById(id);
	}
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public Page<Usuario> listarPageable(Pageable pageable) {
		return usuarioDao.findAll(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("username").ascending()));
	}

	@Override
	public Usuario listarPorUsername(String username) {
		return usuarioDao.listarPorUsername(username);
	}

	@Override
	public Page<Usuario> buscarXUsername(Pageable pageable, String username) {
		return usuarioDao.buscarXNombre(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("username").ascending()),
				username);
	}

//	@Override
//	public byte[] leerArchivo(String pathFoto) {
//		Path path = Paths.get(pathFoto);
//		byte[] bArray = null;
//
//		try {
//			bArray = Files.readAllBytes(path);
//		} catch (IOException e) {
//			throw new RuntimeException("Error. El archivo no puede ser abierto porq no existe");
//		}
//		return bArray;
//	}

}
