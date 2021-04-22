package com.albo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

import com.albo.digitalizacion.dto.ArchivoResultado;
import com.albo.digitalizacion.model.Archivo;
import com.albo.digitalizacion.model.General;
import com.albo.digitalizacion.service.IArchivoService;
import com.albo.digitalizacion.service.IGeneralService;
import com.albo.soa.model.Inventario;
import com.albo.soa.service.IInventarioService;

@RestController
@RequestMapping("/digital")
public class DigitalizacionController {

	private static final Logger LOGGER = LogManager.getLogger(DigitalizacionController.class);

	public static final String ANSI_RESET = "\u001B[0m";

	public static final String ANSI_RED = "\u001B[31m";

	public static final String nitConcesionario = "120585022";

	@Autowired
	private IInventarioService inventarioService;

	@Autowired
	private IArchivoService archivoService;

	@Autowired
	private IGeneralService generalService;

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
			@RequestParam("fechaProceso") String fechaProceso, @RequestParam("mesesAtras") String mesesAtras,
			@RequestParam("directorioOrigen") String directorioOrigen,
			@RequestParam("directorioDestino") String directorioDestino) {

		/* controlamos que los parametros de entrada no esten vacios */
		if (gestion == "" || trimestre == "" || recinto == "" || directorioOrigen == "" || directorioDestino == ""
				|| fechaProceso == "" || mesesAtras == "") {
			return new ResponseEntity<>("Parametros de entrada incorrectos", HttpStatus.BAD_REQUEST);
		}

		// creamos el path de destino del proceso de digitalización para el recinto en
		// cuestión
		String pathDestino = this.creaPathDestino(recinto, trimestre, directorioDestino, gestion);

		if (pathDestino == "error") {
			System.exit(1);
			return new ResponseEntity<>("Error creando el Path Destino", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {

			// cargamos los archivos del directorio seleccionado
			List<String> listaArchivos = new ArrayList<String>();
			listaArchivos = this.listFilesUsingFilesList(directorioOrigen);
			Collections.sort(listaArchivos);

			// recorremos los archivos cargados para procesarlos
			for (String nombreArch : listaArchivos) {
				// verificamos la nomenclatura
				// TODO mejorar o no la nomenclatura o solo mencionarlos
				if (!this.revisaNomenclatura(nombreArch)) {
					System.exit(1);
					return new ResponseEntity<>("Error en la nomenclaturadel archivo: " + nombreArch,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// procesamos el archivo de acuerdo a su tipo
				// -- Inventario
				if (nombreArch.charAt(0) == 'I') {

					LocalDateTime fechaProcesoFin = this.fechaStringToDate(fechaProceso + " 23:59:59");

					ArchivoResultado archivoResultado = this.copiarRenombrarArchivoInventario(nombreArch,
							directorioOrigen, pathDestino, recinto, fechaProceso, Integer.parseInt(mesesAtras),
							fechaProcesoFin);

					Archivo archivo = this.registrarTablaArchivo(nombreArch, archivoResultado.getNuevoNombreArchivo(),
							fechaProcesoFin, directorioOrigen, pathDestino);

					General general = this.registrarTablaGeneral(archivoResultado.getTipoDocArchivo(),
							archivoResultado.getNuevoNombreArchivo(), archivoResultado.getInventario().getInvAduana(),
							archivoResultado.getInventario().getInvParte(),
							archivoResultado.getInventario().getInvFechaAnpr(), fechaProcesoFin, archivo);
				}

			}

			return new ResponseEntity<String>("Archivos procesados: " + listaArchivos.size(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/** Creamos el path de destino donde se encuentran los archivos renombrados **/
	public String creaPathDestino(String recinto, String trimestre, String directorioDestino, String gestion) {
		String destino = directorioDestino + "//" + gestion + "//" + trimestre + "//" + recinto;
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
		return destino;
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
	 * función q copia archivos de inventarios(I000117-901.TIF) con un nuevo nombre
	 * (invParte modificado)
	 **/
	public ArchivoResultado copiarRenombrarArchivoInventario(String nombreArchivoOrigen, String pathOrigen,
			String pathDestino, String invRecinto, String fechaProceso, Integer mesesAtras,
			LocalDateTime fechaProcesoFin) {

		// obtenemos el nroInv del nombreArchivoOrigen
		String[] nombreArchivoPartido = nombreArchivoOrigen.split("\\.");
		String[] numeroNombreArchivo = nombreArchivoPartido[0].split("-");
		String nroInventario = numeroNombreArchivo[0].replace("I", "");

		// armamos la fecha de proceso inicial, ajustandola n meses atrás
		LocalDateTime fechaProcesoInicio = this.fechaStringToDate(fechaProceso + " 00:00:00");
		fechaProcesoInicio = fechaProcesoInicio.minusMonths(mesesAtras);

		// buscamos el parte correspondiente al nro de inventario en un intervalo de
		// tiempo de inventarios registrados en bd (invFecha)
		Inventario inventario = new Inventario();
		inventario = inventarioService.buscarPorNroInventario(nroInventario, invRecinto, fechaProcesoInicio,
				fechaProcesoFin);

		// armamos el nuevo nombre q tendrá el archivo copiado
		String nuevoNombreArchivo = inventario.getInvGestion() + inventario.getInvAduana() + inventario.getInvNroreg()
				+ inventario.getInvEmbarque() + "-" + numeroNombreArchivo[1] + ".tif";

		// copiamos el archivo co su nuevo nombre
		try {
			Path origenPath = Paths.get(pathOrigen + "//" + nombreArchivoOrigen);
			Path destinoPath = Paths.get(pathDestino + "//" + nuevoNombreArchivo);

			// NOTA. sobreescribe el fichero de destino si ya existe en el destino
			Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (FileNotFoundException ex) {
			LOGGER.log(Level.ERROR, ex.getMessage());
		} catch (IOException ex) {
			LOGGER.log(Level.ERROR, ex.getMessage());
		}

		ArchivoResultado archivoResultado = new ArchivoResultado();
		archivoResultado.setInventario(inventario);
		archivoResultado.setNuevoNombreArchivo(nuevoNombreArchivo);
		archivoResultado.setTipoDocArchivo(numeroNombreArchivo[1]);

		return archivoResultado;
	}

	/**
	 * función que registra en la tabla Archivo de la bd Digitalización el archivo
	 * procesado
	 * 
	 * @return
	 */
	public Archivo registrarTablaArchivo(String nombreArchivoOrigen, String nuevoNombreArchivo,
			LocalDateTime fechaProceso, String pathOrigen, String pathDestino) {

		Archivo archivo = new Archivo();
		archivo.setNomArchivo(nuevoNombreArchivo);
		archivo.setFecPro(fechaProceso);
		archivo.setOrigen(pathOrigen + "//" + nombreArchivoOrigen);
		archivo.setDestin(pathDestino + "//" + nuevoNombreArchivo);

		return this.archivoService.saveOrUpdate(archivo);
	}

	/**
	 * función que registra en la tabla General de la bd Digitalización el archivo
	 * procesado
	 * 
	 * @return
	 */
	public General registrarTablaGeneral(String tipoDoc, String nuevoNombreArchivo, String codAduana, String tramite,
			LocalDateTime fechaEmision, LocalDateTime fechaProceso, Archivo archivo) {

		General general = new General();
		general.setCnsCodConc(nitConcesionario);
		general.setCnsTipoDoc(tipoDoc);
		general.setCnsEmisor(nitConcesionario);
		general.setArchivo(archivo);
		general.setCnsAduTra(codAduana);
		general.setCnsNroTra(tramite);
		general.setCnsFechaEmi(fechaEmision);
		general.setCnsFechaPro(fechaProceso);
		general.setCnsEstado("A");
		this.generalService.saveOrUpdate(general);

		return this.generalService.saveOrUpdate(general);
	}

	/**
	 * funcion q convierte un texto con la fecha a LocalDateTime
	 */
	public LocalDateTime fechaStringToDate(String cadenaFecha) {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime fechaconvertida = LocalDateTime.parse(cadenaFecha, formatoFecha);
		return fechaconvertida;
	}

	/**
	 * función q copia archivos de inventarios(I000117-901.TIF), certificados de
	 * salida(C000030-B74.tif) y constancias de entrega(S002928-932.TIF)
	 * renombrandolos
	 **/
//	public void copiarRenombrarArchivo(String pathOrigen, String pathDestino, String nombreArchivoOrigen) {
//		// caso Inventarios
//		if (nombreArchivoOrigen.charAt(0) == 'I') {
//			try {
//				Path origenPath = Paths.get(pathOrigen + "//" + nombreArchivoOrigen);
//				Path destinoPath = Paths.get(pathDestino + "//" + nombreArchivoOrigen);
//				// sobreescribir el fichero de destino si existe y lo copia
//				Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
//			} catch (FileNotFoundException ex) {
//				LOGGER.log(Level.ERROR, ex.getMessage());
//			} catch (IOException ex) {
//				LOGGER.log(Level.ERROR, ex.getMessage());
//			}
//		}
//
//		// caso Certificados de salida
//		if (nombreArchivoOrigen.charAt(0) == 'C') {
//
//		}
//
//		// caso Constancias de entrega
//		if (nombreArchivoOrigen.charAt(0) == 'S') {
//
//		}
//
//	}

	/** función q devuelve un listado de los archivos en un directorio **/
	public List<String> listFilesUsingFilesList(String dir) throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(dir))) {
			return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString)
					.collect(Collectors.toList());
		}
	}

}
