package com.albo.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import com.albo.digitalizacion.model.General;
import com.albo.digitalizacion.model.Relacion;
import com.albo.digitalizacion.model.Total;
import com.albo.digitalizacion.service.IGeneralService;
import com.albo.digitalizacion.service.IRelacionService;
import com.albo.digitalizacion.service.ITotalService;
import com.albo.util.XmlGeneralUtil;
import com.albo.util.XmlRelacionUtil;
import com.albo.util.XmlTotalUtil;

@RestController
@RequestMapping("/xml")
public class XmlController {

	private static final Logger LOGGER = LogManager.getLogger(DigitalizacionController.class);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String nitConcesionario = "120585022";

	@Autowired
	private IGeneralService generalService;

	@Autowired
	private IRelacionService relacionService;

	@Autowired
	private ITotalService totalService;

	@PostMapping(value = "/generarXML", produces = "application/json")
	public ResponseEntity<?> generaXML(@RequestParam("gestion") String gestion,
			@RequestParam("fechaProceso") String fechaProceso, @RequestParam("trimestre") String trimestre,
			@RequestParam("directorioDestino") String directorioDestino) {

		/* controlamos que los parametros de entrada no esten vacios */
		if (fechaProceso == "") {
			return new ResponseEntity<>("Parametros de entrada incorrectos", HttpStatus.BAD_REQUEST);
		}

		// armamos la fecha de proceso final del trimestre
		LocalDateTime fechaFinalProceso = this.fechaStringToDate(fechaProceso + " 23:59:59");
		LOGGER.info("fechaFinalProceso: " + fechaFinalProceso);

		// creamos el path de destino donde se guardarán los XML
		String pathDestino = this.creaPathDestino(trimestre, directorioDestino, gestion);

		if (pathDestino == "error") {
			return new ResponseEntity<>("Error creando el Path Destino", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// obtenemos el mes de la fecha de proceso
		String mesProceso = fechaProceso.substring(3, 5);

		// aumentamos ceros por delante para completar los 15 caracteres
		String nitConcesionario15Char = nitConcesionario;
		while (nitConcesionario15Char.length() < 15) {
			nitConcesionario15Char = "0" + nitConcesionario15Char;
		}

		// armamos los nombres de los archivos
		String nombreArchivoGeneral = "g" + gestion + mesProceso + nitConcesionario15Char;
		String nombreArchivoRelacion = "r" + gestion + mesProceso + nitConcesionario15Char;
		String nombreArchivoTotal = "t" + gestion + mesProceso + nitConcesionario15Char;

//		String resultGeneral = this.crearXmlGeneral(fechaFinalProceso, nombreArchivoGeneral, pathDestino + "//");

		String resultRelacion = this.crearXmlRelacion(fechaFinalProceso, nombreArchivoRelacion, pathDestino + "//");

		String resultTotal = this.crearXmlTotal(fechaFinalProceso, nombreArchivoTotal, pathDestino + "//");

//		if (resultGeneral == "ERROR") {
//			return new ResponseEntity<String>("Error generando el XML General", HttpStatus.OK);
//		}

		if (resultRelacion == "ERROR") {
			return new ResponseEntity<String>("Error generando el XML Relacion", HttpStatus.OK);
		}

		if (resultTotal == "ERROR") {
			return new ResponseEntity<String>("Error generando el XML Total", HttpStatus.OK);
		}

		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	/**
	 * funcion q convierte un texto con la fecha a LocalDateTime
	 */
	public LocalDateTime fechaStringToDate(String cadenaFecha) {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime fechaconvertida = LocalDateTime.parse(cadenaFecha, formatoFecha);
		return fechaconvertida;
	}

	/** Creamos el path de destino para los xml **/
	public String creaPathDestino(String trimestre, String directorioDestino, String gestion) {
		String destino = directorioDestino + "//" + gestion + "//" + trimestre;
		File pathDestino = new File(destino);
		if (!pathDestino.exists()) {
			if (pathDestino.mkdirs()) {
				LOGGER.info("Path destino creado");
				return destino;
			} else {
				LOGGER.error("Error al crear path destino");
				return "error";
			}
		}
		return destino;
	}

	// crea el archivo XML General
	public String crearXmlGeneral(LocalDateTime fechaFinalProceso, String nombreArchivoGeneral, String pathArchivo) {
		List<General> listaGeneralTrim = this.generalService.listarxFecha(fechaFinalProceso);

		if (listaGeneralTrim.isEmpty() || listaGeneralTrim.size() == 0 || listaGeneralTrim == null) {
			LOGGER.error("Error cargando lista: GENERAL");
			return "ERROR";
		}

		// generamos el objeto document XML
		XmlGeneralUtil xmlGeneralUtil = new XmlGeneralUtil();

		try {
			Document document = xmlGeneralUtil.generaDocumentGeneral(listaGeneralTrim);

			// creamos el archivo
			Source source = new DOMSource(document);

			Result result = new StreamResult(new java.io.File(pathArchivo + nombreArchivoGeneral + ".xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			LOGGER.error("Error creando archivo XML: GENERAL");
			e.printStackTrace();
			return "ERROR";
		}

		return "OK";
	}

	// crea el archivo XML Relacion
	public String crearXmlRelacion(LocalDateTime fechaFinalProceso, String nombreArchivoRelacion, String pathArchivo) {
		List<Relacion> listaRelacionTrim = this.relacionService.listarxFecha(fechaFinalProceso);

		if (listaRelacionTrim.isEmpty() || listaRelacionTrim.size() == 0 || listaRelacionTrim == null) {
			LOGGER.error("Error cargando lista: RELACION");
			return "ERROR";
		}

		// generamos el objeto document XML
		XmlRelacionUtil xmlRelacionUtil = new XmlRelacionUtil();

		try {
			Document document = xmlRelacionUtil.generaDocumentRelacion(listaRelacionTrim);

			// creamos el archivo
			Source source = new DOMSource(document);

			Result result = new StreamResult(new java.io.File(pathArchivo + nombreArchivoRelacion + ".xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			LOGGER.error("Error creando archivo XML: RELACION");
			e.printStackTrace();
			return "ERROR";
		}

		return "OK";
	}

	// crea el archivo XML Total
	public String crearXmlTotal(LocalDateTime fechaFinalProceso, String nombreArchivoTotal, String pathArchivo) {
		List<Total> listaTotalTrim = totalService.listaxFecha(fechaFinalProceso);

		if (listaTotalTrim.isEmpty() || listaTotalTrim.size() == 0 || listaTotalTrim == null) {
			LOGGER.error("Error cargando lista: TOTAL");
			return "ERROR";
		}

		// generamos el objeto document XML
		XmlTotalUtil xmlTotalUtil = new XmlTotalUtil();

		try {
			Document document = xmlTotalUtil.generaDocumentTotal(listaTotalTrim);

			// creamos el archivo
			Source source = new DOMSource(document);

			Result result = new StreamResult(new java.io.File(pathArchivo + nombreArchivoTotal + ".xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			LOGGER.error("Error creando archivo XML: TOTAL");
			e.printStackTrace();
			return "ERROR";
		}

		return "OK";
	}

}
