package com.albo.service.impl.digitalizacion;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albo.dao.digitalizacion.IRolDAO;
import com.albo.model.digitalizacion.Rol;
import com.albo.service.digitalizacion.IRolService;

@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolDAO rolDao;

	@Override
	public List<Rol> findAll() {
		return rolDao.findAll();
	}

	@Override
	public Optional<Rol> findById(Long id) {
		return rolDao.findById(id);
	}

	@Override
	public Rol saveOrUpdate(Rol rol) {
		return rolDao.saveAndFlush(rol);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			rolDao.deleteById(id);
			jsonObject.put("message", "Rol deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
