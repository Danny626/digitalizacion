package com.albo.digitalizacion.service;

import java.util.List;
import java.util.Optional;

public interface IService<T, V> {

//	Collection<T> findAll();
	List<T> findAll();

	Optional<T> findById(V id);

	T saveOrUpdate(T t);

	String deleteById(V id);
}
