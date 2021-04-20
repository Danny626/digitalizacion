package com.albo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.albo.soa.model.Inventario;
import com.albo.soa.service.IInventarioService;

@RestController
@RequestMapping("/digital")
public class DigitalizacionController {

	private static final Logger LOGGER = LogManager.getLogger(DigitalizacionController.class);

	public static final String ANSI_RESET = "\u001B[0m";

	public static final String ANSI_RED = "\u001B[31m";

	@Autowired
	private IInventarioService inventarioService;

	private Set<String> archivos = new HashSet<String>();

	@GetMapping(value = "/prueba", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inventarioPorParte() {

		Inventario inventario = new Inventario();
		inventario = inventarioService.buscarPorParte("422202133888EUKOJPCL1706279", "2021");

		JSONObject jo = new JSONObject(inventario);

		System.out.println(jo);

		return new ResponseEntity<String>(jo.toString(), HttpStatus.OK);
	}

	/**
	 * Proceso de digitalización
	 * 
	 * @param gestion           gestión del proceso de digitalización
	 * @param trimestre         trimestre del proceso de digitalización
	 * @param recinto           recinto q realiza la digitalización
	 * @param directorioOrigen  directorio donde se encuentran los archivos
	 *                          digitalizados
	 * @param directorioDestino directorio donde se copiaran los nuevos archivos
	 */
	@PostMapping(value = "/procesar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> procesar(@RequestParam("gestion") String gestion,
			@RequestParam("trimestre") String trimestre, @RequestParam("recinto") String recinto,
			@RequestParam("directorioOrigen") String directorioOrigen,
			@RequestParam("directorioDestino") String directorioDestino) {

		/* controlamos que los parametros de entrada no esten vacios */
		if (gestion == "" || trimestre == "" || recinto == "" || directorioOrigen == "" || directorioDestino == "") {
			return new ResponseEntity<>("Parametros de entrada incorrectos", HttpStatus.BAD_REQUEST);
		}

		// creamos el path de destino del proceso de digitalización para el recinto en
		// cuestión
		String pathDestino = this.creaPathDestino(recinto, trimestre, directorioDestino);

		if (pathDestino == "error") {
			System.exit(1);
		}

		try {

			// cargamos los archivos del directorio seleccionado
			this.archivos = this.listFilesUsingFilesList(directorioOrigen);

			// recorremos los archivos cargados para procesarlos
			for (String nombreArch : this.archivos) {
				// verificamos la nomenclatura
				// TODO mejorar o no la nomenclatura o solo mencionarlos
				if (!this.revisaNomenclatura(nombreArch)) {
					System.exit(1);
				}

				// copiamos y renombramos el archivo de acuerdo a su tipo
				// caso Inventarios
				if (nombreArch.charAt(0) == 'I') {
					this.procesarInventario(nombreArch, directorioOrigen, pathDestino);
				}

			}

			return new ResponseEntity<Boolean>(true, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/** Creamos el path de destino donde se encuentran los archivos renombrados **/
	public String creaPathDestino(String recinto, String trimestre, String directorioDestino) {
		String destino = directorioDestino + "//" + recinto + "//" + trimestre;
		File pathDestino = new File(destino);
		if (!pathDestino.exists()) {
			if (pathDestino.mkdirs()) {
				System.out.println("Path destino creado");
				return destino;
			} else {
				System.out.println("Error al crear path destino");
				return "error";
			}
		}
		return "existe";
	}

	/** función q revisa la nomenclatura del archivo **/
	public boolean revisaNomenclatura(String nombreArchivo) {
		// verificamos la longitud de la cadena
		if (nombreArchivo.length() != 15) {
			return false;
		}
		return true;
	}

	/**
	 * función q copia archivos de inventarios(I000117-901.TIF) renombrandolos
	 **/
	public void procesarInventario(String nombreArchivoOrigen, String pathOrigen, String pathDestino) {
		// buscamos el parte correspondiente al nro de inventario dado en el
		// nombreArchivoOrigen
		Inventario inventario = new Inventario();
		inventario = inventarioService.buscarPorNroInventario(nombreArchivoOrigen, pathOrigen, pathDestino);

		String nuevoNombre = "";

		try {
			Path origenPath = Paths.get(pathOrigen + "//" + nombreArchivoOrigen);
			Path destinoPath = Paths.get(pathDestino + "//" + nuevoNombre);

			// sobreescribir el fichero de destino si existe y lo copia
			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (FileNotFoundException ex) {
			LOGGER.log(Level.ERROR, ex.getMessage());
		} catch (IOException ex) {
			LOGGER.log(Level.ERROR, ex.getMessage());
		}
	}

	/**
	 * función q copia archivos de inventarios(I000117-901.TIF), certificados de
	 * salida(C000030-B74.tif) y constancias de entrega(S002928-932.TIF)
	 * renombrandolos
	 **/
	public void copiarRenombrarArchivo(String pathOrigen, String pathDestino, String nombreArchivoOrigen) {
		// caso Inventarios
		if (nombreArchivoOrigen.charAt(0) == 'I') {
			try {
				Path origenPath = Paths.get(pathOrigen + "//" + nombreArchivoOrigen);
				Path destinoPath = Paths.get(pathDestino + "//" + nombreArchivoOrigen);
				// sobreescribir el fichero de destino si existe y lo copia
				Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
			} catch (FileNotFoundException ex) {
				LOGGER.log(Level.ERROR, ex.getMessage());
			} catch (IOException ex) {
				LOGGER.log(Level.ERROR, ex.getMessage());
			}
		}

		// caso Certificados de salida
		if (nombreArchivoOrigen.charAt(0) == 'C') {

		}

		// caso Constancias de entrega
		if (nombreArchivoOrigen.charAt(0) == 'S') {

		}

	}

	/** función q devuelve un listado de los archivos en un directorio **/
	public Set<String> listFilesUsingFilesList(String dir) throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(dir))) {
			return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString)
					.collect(Collectors.toSet());
		}
	}

}
