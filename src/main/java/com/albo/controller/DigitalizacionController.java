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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.albo.compusoft.model.Factura;
import com.albo.compusoft.service.IFacturaService;
import com.albo.digitalizacion.dto.ArchivoResultadoDTO;
import com.albo.digitalizacion.dto.CantidadTipoErrorDTO;
import com.albo.digitalizacion.dto.ResultadoProcesoDTO;
import com.albo.digitalizacion.dto.TipoDocContGeneralDTO;
import com.albo.digitalizacion.model.Archivo;
import com.albo.digitalizacion.model.ErrorProceso;
import com.albo.digitalizacion.model.General;
import com.albo.digitalizacion.model.Relacion;
import com.albo.digitalizacion.model.TipoDocumento;
import com.albo.digitalizacion.model.TipoError;
import com.albo.digitalizacion.model.Total;
import com.albo.digitalizacion.service.IArchivoService;
import com.albo.digitalizacion.service.IErrorProcesoService;
import com.albo.digitalizacion.service.IGeneralService;
import com.albo.digitalizacion.service.IPrefijoService;
import com.albo.digitalizacion.service.IRelacionService;
import com.albo.digitalizacion.service.ITipoDocumentoService;
import com.albo.digitalizacion.service.ITipoErrorService;
import com.albo.digitalizacion.service.ITotalService;
import com.albo.soa.model.DocArchivo;
import com.albo.soa.model.Inventario;
import com.albo.soa.model.Recinto;
import com.albo.soa.service.IDocArchivoService;
import com.albo.soa.service.IInventarioService;
import com.albo.soa.service.IRecintoService;

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

	@Autowired
	private IRelacionService relacionService;

	@Autowired
	private IRecintoService recintoService;

	@Autowired
	private IDocArchivoService docArchivoService;

	@Autowired
	private IFacturaService facturaService;

	@Autowired
	private ITipoDocumentoService tipoDocumentoService;

	@Autowired
	private ITotalService totalService;

	@Autowired
	private IErrorProcesoService errorProcesoService;

	@Autowired
	private ITipoErrorService tipoErrorService;

	@Autowired
	private IPrefijoService prefijoService;

	@PostMapping(value = "/prueba", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> prueba(@RequestParam("fechaProceso") String fechaProceso,
			@RequestParam("mesesAtras") Integer mesesAtras) {

		/* controlamos que los parametros de entrada no esten vacios */
		if (mesesAtras == null) {
			return new ResponseEntity<>("Parametros de entrada incorrectos", HttpStatus.BAD_REQUEST);
		}

		// armamos la respuesta con los totales obtenidos del proceso
//		LocalDateTime fechaFinalProceso = this.fechaStringToDate("31-03-2021 23:59:59");
//		ResultadoProcesoDTO resultadoProceso = this.calculoTotalesProceso(fechaFinalProceso, 10944, "TAM01");

//		JSONObject jo = new JSONObject(resultadoProceso);

		// armamos la fecha de proceso inicial del trimestre
		// armamos la fecha de proceso inicial del trimestre
		LocalDateTime fechaInicioProceso = this.fechaStringToDate(fechaProceso + " 00:00:00");
		fechaInicioProceso = fechaInicioProceso.minusMonths(3);
		fechaInicioProceso = fechaInicioProceso.plusDays(2);
		LOGGER.info("fechaInicioProceso: " + fechaInicioProceso);

		// armamos la fecha de proceso inicial del trimestre para buscar inventarios
		LocalDateTime fechaInicioProcesoInventarios = fechaInicioProceso.minusMonths(mesesAtras);
		LOGGER.info("fechaInicioProcesoInventarios: " + fechaInicioProcesoInventarios);

		// armamos la fecha de proceso final del trimestre
		LocalDateTime fechaFinalProceso = this.fechaStringToDate(fechaProceso + " 23:59:59");
		LOGGER.info("fechaFinalProceso: " + fechaFinalProceso);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	/**
	 * servicio q obtiene el total de archivos procesados por recinto
	 * 
	 * @param recinto      recinto a consultar (CHB01)
	 * @param fechaProceso fecha final del proceso de digitalización (31-03-2021)
	 * @return retorna un objeto de tipo ResultadoProcesoDTO
	 */
	@GetMapping(value = "/resultadoRecinto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerResultadoProcesoPorRecinto(@RequestParam("recinto") String recinto,
			@RequestParam("fechaProceso") String fechaProceso) {

		/* controlamos que los parametros de entrada no esten vacios */
		if (recinto == "" || fechaProceso == "") {
			return new ResponseEntity<>("Parametros de entrada incorrectos", HttpStatus.BAD_REQUEST);
		}

		// armamos la fecha de proceso final del trimestre
		LocalDateTime fechaFinalProceso = this.fechaStringToDate(fechaProceso + " 23:59:59");

		// consultamos la cantidad de archivos procesados
		Integer cantidadProcesados = archivoService.buscarTotalRegistrosPorRecinto(recinto, fechaFinalProceso);

		// armamos la respuesta con los totales obtenidos del proceso
		ResultadoProcesoDTO resultadoProceso = this.calculoTotalesProceso(fechaFinalProceso, cantidadProcesados,
				recinto);

		return new ResponseEntity<ResultadoProcesoDTO>(resultadoProceso, HttpStatus.OK);
	}

	/**
	 * Proceso de digitalización (archivos)
	 * 
	 * @param gestion           gestión del proceso de digitalización
	 * @param trimestre         trimestre del proceso de digitalización
	 * @param recinto           recinto q realiza la digitalización
	 * @param fechaProceso      fecha del último día del trimestre del proceso de
	 *                          digitalización
	 * @param mesesAtras        cantidad de meses atrás en los que se revisará los
	 *                          inventarios
	 * @param directorioOrigen  directorio donde se encuentran los archivos
	 *                          digitalizados
	 * @param directorioDestino directorio donde se copiaran los nuevos archivos
	 * @param serialTramiteDim  con qué letra se procesará las duis, dims
	 */
	@PostMapping(value = "/procesarArchivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> procesarArchivos(@RequestParam("gestion") String gestion,
			@RequestParam("trimestre") String trimestre, @RequestParam("recinto") String recinto,
			@RequestParam("fechaProceso") String fechaProceso, @RequestParam("mesesAtras") Integer mesesAtras,
			@RequestParam("directorioOrigen") String directorioOrigen,
			@RequestParam("directorioDestino") String directorioDestino,
			@RequestParam("serialTramiteDim") String serialTramiteDim) {

		/* controlamos que los parametros de entrada no esten vacios */
		if (gestion == "" || trimestre == "" || recinto == "" || directorioOrigen == "" || directorioDestino == ""
				|| fechaProceso == "" || mesesAtras == null) {
			return new ResponseEntity<>("Parametros de entrada incorrectos", HttpStatus.BAD_REQUEST);
		}

		// creamos el path de destino del proceso de digitalización para el recinto en
		// cuestión
		String pathDestino = this.creaPathDestino(recinto, trimestre, directorioDestino, gestion);

		if (pathDestino == "error") {
			return new ResponseEntity<>("Error creando el Path Destino", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {

			// cargamos los archivos del directorio seleccionado
			List<File> listaArchivos = this.listaArchivosDirectorio(directorioOrigen);
			Collections.sort(listaArchivos);

			LOGGER.info("Cantidad archivos a procesar: " + listaArchivos.size());

			// buscamos el codigo de aduana del recinto
			Recinto recintoRes = new Recinto();
			recintoRes = recintoService.findById(recinto).get();

			// armamos la fecha de proceso inicial del trimestre
			LocalDateTime fechaInicioProceso = this.fechaStringToDate(fechaProceso + " 00:00:00");
			fechaInicioProceso = fechaInicioProceso.minusMonths(3);
			fechaInicioProceso = fechaInicioProceso.plusDays(2);
			LOGGER.info("fechaInicioProceso: " + fechaInicioProceso);

			// armamos la fecha de proceso inicial del trimestre para buscar inventarios
			LocalDateTime fechaInicioProcesoInventarios = fechaInicioProceso.minusMonths(mesesAtras);
			LOGGER.info("fechaInicioProcesoInventarios: " + fechaInicioProcesoInventarios);

			// armamos la fecha de proceso final del trimestre
			LocalDateTime fechaFinalProceso = this.fechaStringToDate(fechaProceso + " 23:59:59");
			LOGGER.info("fechaFinalProceso: " + fechaFinalProceso);

			int contadorProceso = 0;

			// recorremos los archivos cargados para procesarlos
			for (File file : listaArchivos) {
				String nombreArch = file.getName().toUpperCase();
				contadorProceso++;
				LOGGER.info(
						contadorProceso + " de " + listaArchivos.size() + "." + " Procesando archivo: " + nombreArch);

				// verificamos la nomenclatura
				// TODO mejorar o no la nomenclatura o solo mencionarlos
				String codError = this.revisaNomenclatura(nombreArch);

				if (!codError.equals("")) {
					// registramos el archivo en conflicto
					String tdoc = nombreArch.substring(8, 11);
					Archivo archivo = this.registrarArchivo(nombreArch, null, fechaFinalProceso, directorioOrigen, null,
							true, recinto);

					// buscamos el tipo de documento de acuerdo al codigo
					TipoDocumento tipoDocumento = tipoDocumentoService.findById(tdoc).get();

					// registramos el error
					ErrorProceso errorProceso = this.registrarErrorProceso(recinto, tipoDocumento, codError, archivo,
							fechaFinalProceso);

					LOGGER.error("Error al procesar: " + errorProceso.getArchivo().getOrigen() + " => "
							+ errorProceso.getTipoError().getCodError() + " "
							+ errorProceso.getTipoError().getDescripcion());

					continue;
				}

				// procesamos el archivo de acuerdo a su tipo
				// -- Inventario
				if (nombreArch.charAt(0) == 'I') {

					ArchivoResultadoDTO archivoResultado = this.copiarRenombrarArchivoInventario(nombreArch,
							directorioOrigen, pathDestino, recinto, fechaInicioProcesoInventarios, fechaFinalProceso);

					// verificamos si existió algún error al copiarRenombrarArchivo
					if (archivoResultado.getCodError() == null) {

						Archivo archivo = this.registrarArchivo(nombreArch, archivoResultado.getNuevoNombreArchivo(),
								fechaFinalProceso, directorioOrigen, pathDestino, false, recinto);

						General general = this.registrarGeneral(archivoResultado.getTipoDocumento(),
								archivoResultado.getNuevoNombreArchivo(),
								archivoResultado.getInventario().getInvAduana(),
								archivoResultado.getInventario().getInvParte(),
								archivoResultado.getInventario().getInvFechaAnpr(), fechaFinalProceso, archivo,
								recinto);

					} else {
						// registramos el archivo en conflicto y el error
						ErrorProceso errorProceso = this.registraErrorProceso(nombreArch, fechaFinalProceso,
								directorioOrigen, recinto, archivoResultado);

						LOGGER.error("Error al procesar: " + errorProceso.getArchivo().getOrigen() + " => "
								+ errorProceso.getTipoError().getCodError() + " "
								+ errorProceso.getTipoError().getDescripcion());
					}
				}

				// -- Certificados de salida
				if (nombreArch.charAt(0) == 'C') {
					ArchivoResultadoDTO archivoResultado = this.copiarRenombrarArchivoCertificadoSalida(nombreArch,
							directorioOrigen, pathDestino, recinto, fechaProceso, recintoRes.getRecCoda().toString(),
							fechaInicioProceso, fechaFinalProceso, gestion);

					// verificamos si existió algún error al copiarRenombrarArchivo
					if (archivoResultado.getCodError() == null) {

						Archivo archivo = this.registrarArchivo(nombreArch, archivoResultado.getNuevoNombreArchivo(),
								fechaFinalProceso, directorioOrigen, pathDestino, false, recinto);

						General general = this.registrarGeneral(archivoResultado.getTipoDocumento(),
								archivoResultado.getNuevoNombreArchivo(), archivoResultado.getCodAduana(),
								archivoResultado.getTramite(), archivoResultado.getFactura().getFactFecha(),
								fechaFinalProceso, archivo, recinto);

						String tramite2 = archivoResultado.getFactura().getFacturaPK().getE3Cod() + " "
								+ archivoResultado.getFactura().getFacturaPK().getE3ofSerie() + " "
								+ archivoResultado.getFactura().getFacturaPK().getFactNro();

						Relacion relacion = this.registrarRelacion(archivoResultado.getTipoDocumento(),
								archivoResultado.getCodAduana(), archivoResultado.getTramite(),
								archivoResultado.getFactura().getFactFecha(), archivoResultado.getTipoDocumento2(),
								archivoResultado.getFactura().getDestCod(), tramite2,
								archivoResultado.getFactura().getFactFecha(), fechaFinalProceso, recinto);
					} else {
						// registramos el archivo en conflicto y el error
						ErrorProceso errorProceso = this.registraErrorProceso(nombreArch, fechaFinalProceso,
								directorioOrigen, recinto, archivoResultado);

						LOGGER.error("Error al procesar: " + errorProceso.getArchivo().getOrigen() + " => "
								+ errorProceso.getTipoError().getCodError() + " "
								+ errorProceso.getTipoError().getDescripcion());
					}
				}

				// -- Constancia de entrega(Pase de salida)
				if (nombreArch.charAt(0) == 'S') {
					ArchivoResultadoDTO archivoResultado = this.copiarRenombrarArchivoConstanciaEntrega(nombreArch,
							directorioOrigen, pathDestino, recinto, gestion, recintoRes.getRecCoda().toString(),
							serialTramiteDim, fechaFinalProceso);

					// verificamos si existió algún error al copiarRenombrarArchivo
					if (archivoResultado.getCodError() == null) {

						Archivo archivo = this.registrarArchivo(nombreArch, archivoResultado.getNuevoNombreArchivo(),
								fechaFinalProceso, directorioOrigen, pathDestino, false, recinto);

						General general = this.registrarGeneral(archivoResultado.getTipoDocumento(),
								archivoResultado.getNuevoNombreArchivo(), archivoResultado.getCodAduana(),
								archivoResultado.getTramite(), archivoResultado.getDocArchivo().getDarFecha(),
								fechaFinalProceso, archivo, recinto);

						Relacion relacion = this.registrarRelacion(archivoResultado.getTipoDocumento2(),
								archivoResultado.getCodAduana(), archivoResultado.getTramite(),
								archivoResultado.getDocArchivo().getDarFecha(), archivoResultado.getTipoDocumento(),
								archivoResultado.getCodAduana(), archivoResultado.getTramite(),
								archivoResultado.getDocArchivo().getDarFecha(), fechaFinalProceso, recinto);
					} else {
						// registramos el archivo en conflicto y el error
						ErrorProceso errorProceso = this.registraErrorProceso(nombreArch, fechaFinalProceso,
								directorioOrigen, recinto, archivoResultado);

						LOGGER.error("Error al procesar: " + errorProceso.getArchivo().getOrigen() + " => "
								+ errorProceso.getTipoError().getCodError() + " "
								+ errorProceso.getTipoError().getDescripcion());
					}
				}

				// -- Parte de recepción
				if (nombreArch.charAt(0) == 'P') {
					ArchivoResultadoDTO archivoResultado = this.copiarRenombrarArchivoParteRecepcion(nombreArch,
							directorioOrigen, pathDestino, recinto, fechaInicioProcesoInventarios, fechaFinalProceso);

					// verificamos si existió algún error al copiarRenombrarArchivo
					if (archivoResultado.getCodError() == null) {

						Archivo archivo = this.registrarArchivo(nombreArch, archivoResultado.getNuevoNombreArchivo(),
								fechaFinalProceso, directorioOrigen, pathDestino, false, recinto);

						General general = this.registrarGeneral(archivoResultado.getTipoDocumento(),
								archivoResultado.getNuevoNombreArchivo(),
								archivoResultado.getInventario().getInvAduana(),
								archivoResultado.getInventario().getInvParte(),
								archivoResultado.getInventario().getInvFechaAnpr(), fechaFinalProceso, archivo,
								recinto);
					} else {
						// registramos el archivo en conflicto y el error
						ErrorProceso errorProceso = this.registraErrorProceso(nombreArch, fechaFinalProceso,
								directorioOrigen, recinto, archivoResultado);

						LOGGER.error("Error al procesar: " + errorProceso.getArchivo().getOrigen() + " => "
								+ errorProceso.getTipoError().getCodError() + " "
								+ errorProceso.getTipoError().getDescripcion());
					}
				}

			}

			LOGGER.info("Proceso finalizado");

			// armamos la respuesta con los totales obtenidos del proceso
			ResultadoProcesoDTO resultadoProceso = this.calculoTotalesProceso(fechaFinalProceso, contadorProceso,
					recinto);

			return new ResponseEntity<ResultadoProcesoDTO>(resultadoProceso, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Proceso de generación de los totales en bd
	 * 
	 * @param fechaProceso fecha del último día del trimestre del proceso de
	 *                     digitalización
	 */
	@PostMapping(value = "/procesarTotales", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> procesarTotales(@RequestParam("fechaProceso") String fechaProceso) {

		// armamos la fecha de proceso final del trimestre
		LocalDateTime fechaFinalProceso = this.fechaStringToDate(fechaProceso + " 23:59:59");
		LOGGER.info("fechaFinalProceso: " + fechaFinalProceso);

		// buscamos la cantidad de los diferentes tipos de documento registrados en
		// General
		List<TipoDocContGeneralDTO> generalDistintos = new ArrayList<>();
		generalDistintos = totalService.buscarDistintos(fechaFinalProceso);

		// registramos en Total cada tipo de documento distinto obtenido de General
		List<Total> totalResultado = new ArrayList<>();

		generalDistintos.forEach(elem -> {

			Total total = new Total();
			total.setCnsCodCon(nitConcesionario);
			total.setCnsFecEnv(fechaFinalProceso);
			total.setCnsEstado("A");
			total.setTipoDocumento(elem.getTipoDocumento());
			total.setCnsCantidad(elem.getCantidad());

			totalResultado.add(totalService.saveOrUpdate(total));

		});

		return new ResponseEntity<List<Total>>(totalResultado, HttpStatus.OK);
	}

	/** Buscar Factura **/
	public List<Factura> buscarFacturaPorNroReg(String reg, String recinto, String codAduana,
			LocalDateTime fechaInicioProceso, LocalDateTime fechaFinalProceso) {

		// nroReg tal como se encuentra en la tabla factura
//		String nroReg = codAduana + gestion + reg;

		// buscamos el registro facturado en la tabla factura de compusoft
		return facturaService.buscarPorNroReg(recinto, reg, fechaInicioProceso, fechaFinalProceso);
	}

	/** Creamos el path de destino donde se encuentran los archivos renombrados **/
	public String creaPathDestino(String recinto, String trimestre, String directorioDestino, String gestion) {
		String destino = directorioDestino + "//" + gestion + "//" + trimestre + "//" + recinto;
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

	/** función q revisa la nomenclatura del archivo **/
	public String revisaNomenclatura(String nombreArchivo) {

		// verificamos la longitud de la cadena (codError.E01)
		if (nombreArchivo.length() != 15) {
			return "E01";
		}

		// verificamos si el prefijo es válido (codError.E02)
		String prefijo = "" + nombreArchivo.charAt(0);
		if (prefijoService.findById(prefijo).isEmpty()) {
			return "E02";
		}

		// verificamos si el sufijo es válido (codError.E03)
		String sufijo = nombreArchivo.substring(8, 11);
		if (tipoDocumentoService.findById(sufijo).isEmpty()) {
			return "E03";
		}

		// verificamos si el nro. de registro es válido(sólo números) (codError.E04)
		String codigoPrefijo = nombreArchivo.substring(1, 7);
		if (!codigoPrefijo.matches("[0-9]+")) {
			return "E04";
		}

		// TODO tendría q tomarse los prefijos de la bd y no de esta forma
		// verificamos si existen incongruencias entre prefijo y sufijo (codError.E21)
		switch (prefijo) {
		case "I":
			if (!sufijo.equals("901")) {
				return "E21";
			}
			break;
		case "C":
			if (!sufijo.equals("B74")) {
				return "E21";
			}
			break;
		case "S":
			if (!sufijo.equals("932")) {
				return "E21";
			}
			break;
		case "P":
			if (!sufijo.equals("901")) {
				return "E21";
			}
			break;
		default:
			LOGGER.error("Error verificando codError.E21");
		}

		return "";
	}

	/**
	 * función q copia archivos de inventarios(I000117-901.TIF) con un nuevo nombre
	 * (invParte modificado)
	 **/
	public ArchivoResultadoDTO copiarRenombrarArchivoInventario(String nombreArchivoOrigen, String pathOrigen,
			String pathDestino, String recinto, LocalDateTime fechaInicioProceso, LocalDateTime fechaFinalProceso) {

		ArchivoResultadoDTO archivoResultado = new ArchivoResultadoDTO();

		// obtenemos el nroInv del nombreArchivoOrigen
		String[] nombreArchivoPartido = nombreArchivoOrigen.split("\\.");
		String[] numeroNombreArchivo = nombreArchivoPartido[0].split("-");
		String nroInventario = numeroNombreArchivo[0].replaceFirst("I", "");

		// buscamos el tipo de documento de acuerdo al codigo
		TipoDocumento tipoDocumento = tipoDocumentoService.findById(numeroNombreArchivo[1]).get();

		// buscamos el parte correspondiente al nro de inventario en un intervalo de
		// tiempo de inventarios registrados en bd (invFecha)
		List<Inventario> listInventario = new ArrayList<>();
		listInventario = inventarioService.buscarPorNroInventarioNoConsolidado(nroInventario, recinto,
				fechaInicioProceso, fechaFinalProceso);

		// si no existe el parte correspondiente (codError.E05)
		if (listInventario.size() == 0) {
			archivoResultado.setCodError("E05");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		// si existe más de un resultado en listInventario, devolvemos error
		// (codError.E19)
		if (listInventario.size() > 1) {
			archivoResultado.setCodError("E19");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		Inventario inventario = listInventario.get(0);

		// eliminamos los caracteres especiales q pudiera tener la primera parte del
		// nuevo nombre del archivo
		String nuevoNombreArchivoFisico = this.eliminaCaracteresEspeciales(inventario.getInvGestion()
				+ inventario.getInvAduana() + inventario.getInvNroreg() + inventario.getInvEmbarque());

		// armamos el nuevo nombre q tendrá el archivo copiado
		String nuevoNombreArchivo = nuevoNombreArchivoFisico + "-" + numeroNombreArchivo[1] + ".tif";

		// verificamos si el registro ya existe en General (codError.E06)
		Optional<General> optionalGeneral = this.buscaGeneralExistente(nitConcesionario, nitConcesionario,
				tipoDocumento, nuevoNombreArchivo, inventario.getInvAduana(), inventario.getInvParte(), recinto);

		if (optionalGeneral.isPresent()) {
			archivoResultado.setCodError("E06");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		// copiamos el archivo con su nuevo nombre
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

		archivoResultado.setInventario(inventario);
		archivoResultado.setNuevoNombreArchivo(nuevoNombreArchivo);
		archivoResultado.setTipoDocumento(tipoDocumento);

		return archivoResultado;

	}

	/**
	 * función q copia archivos de certificados de salida(C000022-B74.TIF) con un
	 * nuevo nombre
	 **/
	public ArchivoResultadoDTO copiarRenombrarArchivoCertificadoSalida(String nombreArchivoOrigen, String pathOrigen,
			String pathDestino, String recinto, String fechaProceso, String codAduana, LocalDateTime fechaInicioProceso,
			LocalDateTime fechaFinalProceso, String gestion) {

		ArchivoResultadoDTO archivoResultado = new ArchivoResultadoDTO();

		// obtenemos el nroCertificado del nombreArchivoOrigen
		String[] nombreArchivoPartido = nombreArchivoOrigen.split("\\.");
		String[] numeroNombreArchivo = nombreArchivoPartido[0].split("-");
		String nroArchivo = numeroNombreArchivo[0].replaceFirst("C", "");

		// buscamos el tipo de documento de acuerdo al codigo
		TipoDocumento tipoDocumento = tipoDocumentoService.findById(numeroNombreArchivo[1]).get();

		// eliminamos los caracteres especiales q pudiera tener la primera parte del
		// nuevo nombre del archivo
		String nuevoNombreArchivoFisico = this
				.eliminaCaracteresEspeciales(gestion + codAduana + "C" + "0" + nroArchivo);

		// armamos el nuevo nombre q tendrá el archivo copiado
		String nuevoNombreArchivo = nuevoNombreArchivoFisico + "-" + numeroNombreArchivo[1] + ".tif";

		// verificamos si el registro ya existe en General (codError.E06)
		// armamos el tramite de acuerdo a como va en la tabla general
		String tramite = gestion + " " + codAduana + " C 0" + nroArchivo;
		Optional<General> optionalGeneral = this.buscaGeneralExistente(nitConcesionario, nitConcesionario,
				tipoDocumento, nuevoNombreArchivo, codAduana, tramite, recinto);

		if (optionalGeneral.isPresent()) {
			archivoResultado.setCodError("E06");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		// Verificamos si existe un registro factura para el numero de factura que viene
		// en el nombre del archivo (codError.E13)
		List<Factura> listFactura = new ArrayList<>();
		listFactura = this.buscarFacturaPorNroReg("%" + nroArchivo, recinto, codAduana, fechaInicioProceso,
				fechaFinalProceso);

		// verficamos si existe respuesta factura (codError.E13)
		if (listFactura.size() == 0) {
			archivoResultado.setCodError("E13");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		// si existe más de un resultado en listFactura, devolvemos error
		// (codError.E19)
		if (listFactura.size() > 1) {
			archivoResultado.setCodError("E19");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		Factura factura = listFactura.get(0);

		// verificamos si el registro ya existe en Relacion (codError.E15)
		String tramite2 = factura.getFacturaPK().getE3Cod() + " " + factura.getFacturaPK().getE3ofSerie() + " "
				+ factura.getFacturaPK().getFactNro();

		// buscamos el tipo de documento2 de acuerdo al codigo de factura
		TipoDocumento tipoDocumento2 = new TipoDocumento();
		if (factura.getFacturaPK().getDocCod().equals("FA")) {
			tipoDocumento2 = tipoDocumentoService.findById("380").get();
		}

		// buscamos si la relación ya existe registrada (codError.E15)
		Optional<Relacion> optionalRelacion = this.buscaRelacionExistente(codAduana, tramite, nitConcesionario,
				factura.getFactFecha(), factura.getDestCod(), tramite2, nitConcesionario, factura.getFactFecha(),
				tipoDocumento, tipoDocumento2, recinto);

		if (optionalRelacion.isPresent()) {
			archivoResultado.setCodError("E15");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		// copiamos el archivo con su nuevo nombre
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

		archivoResultado.setNuevoNombreArchivo(nuevoNombreArchivo);
		archivoResultado.setTipoDocumento(tipoDocumento);
		archivoResultado.setGestion(gestion);
		archivoResultado.setCodAduana(codAduana);
		archivoResultado.setNroArchivo(nroArchivo);
		archivoResultado.setTramite(tramite);
		archivoResultado.setFactura(factura);
		archivoResultado.setTipoDocumento2(tipoDocumento2);
		archivoResultado.setTramite2(tramite2);

		return archivoResultado;
	}

	/**
	 * función q copia archivos de constancias de entrega(S000099-932.TIF) con un
	 * nuevo nombre
	 **/
	public ArchivoResultadoDTO copiarRenombrarArchivoConstanciaEntrega(String nombreArchivoOrigen, String pathOrigen,
			String pathDestino, String recinto, String gestion, String codAduana, String serialTramiteDim,
			LocalDateTime fechaFinalProceso) {

		ArchivoResultadoDTO archivoResultado = new ArchivoResultadoDTO();

		// obtenemos el nroConstanciaentrega del nombreArchivoOrigen
		String[] nombreArchivoPartido = nombreArchivoOrigen.split("\\.");
		String[] numeroNombreArchivo = nombreArchivoPartido[0].split("-");
		String nroConstanciaEntrega = numeroNombreArchivo[0].replaceFirst("S", "");

		// buscamos el tipo de documento de acuerdo al codigo
		TipoDocumento tipoDocumento2 = tipoDocumentoService.findById(numeroNombreArchivo[1]).get();

		// buscamos la declaración única aduanera(dui, dim) correspondiente al nro de
		// salida dado en el nombre del archivo
		Optional<DocArchivo> docArchivoOptional = docArchivoService.buscarPorNroSalida(nroConstanciaEntrega, recinto,
				Integer.valueOf(gestion));

		// verificamos si existe un registro respuesta (codError.E08)
		if (!docArchivoOptional.isPresent()) {
			archivoResultado.setCodError("E08");
			archivoResultado.setTipoDocumento(tipoDocumento2);
			return archivoResultado;
		}

		// eliminamos los caractéres especiales del darCod
		String codSalida = this.eliminaCaracteresEspeciales(docArchivoOptional.get().getDocArchivoPK().getDarCod());
		codSalida = codSalida.substring(7);

		// verificamos si el registro doc_archivo (dar_cod) pertenece a una DUA
		// válida(dui C, due C, dim D) (codError.E08)
		if (codSalida.charAt(0) != 'C' && codSalida.charAt(0) != 'D' || !codSalida.substring(1).matches("[0-9]+")) {
			archivoResultado.setCodError("E08");
			archivoResultado.setTipoDocumento(tipoDocumento2);
			return archivoResultado;
		}

		// si el serialTramiteDim viene con C, cambiamos las Dim's de D a C
		if (serialTramiteDim.equals("C") && codSalida.charAt(0) == 'D') {
			codSalida = codSalida.substring(1);
			codSalida = "C" + codSalida;
		}

		// Arreglo para los casos D0 a D2
		if (codSalida.substring(0, 2).equals("D0")) {
			int endIndex = 1;

			for (int i = 1; i < codSalida.length(); i++) {
				if (codSalida.charAt(i) == '0') {
					endIndex++;
				} else {
					break;
				}
			}

			codSalida = "D" + codSalida.substring(endIndex);
		}

		// si el tamaño del codSalida es menor a 8 caracteres, le agregamos un 0 extra
		while (codSalida.length() < 8 && codSalida.charAt(0) == 'C') {
			codSalida = codSalida.replaceFirst("C", "C0");
		}

		while (codSalida.length() < 8 && codSalida.charAt(0) == 'D') {
			codSalida = codSalida.replaceFirst("D2", "D20");
		}

		// obtenemos solo la parte del nro de archivo con sus ceros (0000099)
		String nroArchivo = codSalida.substring(1);

		// armamos el nuevo nombre q tendrá el archivo copiado
		String nuevoNombreArchivo = gestion + codAduana + codSalida + "-" + numeroNombreArchivo[1] + ".tif";

		// verificamos si el registro ya existe en General (codError.E06)
		// armamos el tramite de acuerdo a como va en la tabla general
		String tramite = gestion + " " + codAduana + " " + serialTramiteDim + " " + nroArchivo;
		Optional<General> optionalGeneral = this.buscaGeneralExistente(nitConcesionario, nitConcesionario,
				tipoDocumento2, nuevoNombreArchivo, codAduana, tramite, recinto);

		if (optionalGeneral.isPresent()) {
			archivoResultado.setCodError("E06");
			archivoResultado.setTipoDocumento(tipoDocumento2);
			return archivoResultado;
		}

		// verificamos si el registro ya existe en Relacion (codError.E15)
		TipoDocumento tipoDocumento1 = new TipoDocumento();
		// verificamos si el documento de salida es una dui o dim para asignarle la
		// codificación pertinente
		if (codSalida.charAt(0) == 'D' || codSalida.charAt(0) == 'C') {
			// buscamos el tipo de documento1 de acuerdo al codigo de DUA valida
			// (dui,due,dim)
			tipoDocumento1 = tipoDocumentoService.findById("960").get();
		}

		// buscamos si la relación ya existe registrada (codError.E15)
		Optional<Relacion> optionalRelacion = this.buscaRelacionExistente(codAduana, tramite, nitConcesionario,
				docArchivoOptional.get().getDarFecha(), codAduana, tramite, nitConcesionario,
				docArchivoOptional.get().getDarFecha(), tipoDocumento1, tipoDocumento2, recinto);

		if (optionalRelacion.isPresent()) {
			archivoResultado.setCodError("E15");
			archivoResultado.setTipoDocumento(tipoDocumento2);
			return archivoResultado;
		}

		// copiamos el archivo con su nuevo nombre
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

		archivoResultado.setNuevoNombreArchivo(nuevoNombreArchivo);
		archivoResultado.setTipoDocumento(tipoDocumento2);
		archivoResultado.setCodAduana(codAduana);
		// TODO verificar si adjuntamos aqui la gestion actual o la gestión de
		// dar_gestion
		archivoResultado.setGestion(gestion);
		archivoResultado.setNroArchivo(nroArchivo);
		archivoResultado.setDocArchivo(docArchivoOptional.get());
		archivoResultado.setTramite(tramite);
		archivoResultado.setTipoDocumento2(tipoDocumento1);

		return archivoResultado;
	}

	/**
	 * función q copia archivos de partes de recepcion(P000206-901.tif) con un nuevo
	 * nombre (invParte modificado)
	 **/
	public ArchivoResultadoDTO copiarRenombrarArchivoParteRecepcion(String nombreArchivoOrigen, String pathOrigen,
			String pathDestino, String recinto, LocalDateTime fechaProcesoInicio, LocalDateTime fechaFinalProceso) {

		ArchivoResultadoDTO archivoResultado = new ArchivoResultadoDTO();

		// obtenemos el nroInv del nombreArchivoOrigen
		String[] nombreArchivoPartido = nombreArchivoOrigen.split("\\.");
		String[] numeroNombreArchivo = nombreArchivoPartido[0].split("-");
		String nroInventario = numeroNombreArchivo[0].replaceFirst("P", "");

		// buscamos el tipo de documento de acuerdo al codigo
		TipoDocumento tipoDocumento = tipoDocumentoService.findById(numeroNombreArchivo[1]).get();

		// buscamos el parte correspondiente al nro de inventario en un intervalo de
		// tiempo de inventarios registrados en bd (invFecha)
		List<Inventario> listInventario = new ArrayList<>();
		listInventario = inventarioService.buscarPorNroInventarioConsolidado(nroInventario, recinto, fechaProcesoInicio,
				fechaFinalProceso, "CON");

		// si no existe el parte correspondiente (codError.E05)
		if (listInventario.size() == 0) {
			archivoResultado.setCodError("E05");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		// si existe más de un resultado en listInventario, devolvemos error
		// (codError.E19)
		if (listInventario.size() > 1) {
			archivoResultado.setCodError("E19");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		Inventario inventario = listInventario.get(0);

		// eliminamos los caracteres especiales q pudiera tener la primera parte del
		// nuevo nombre del archivo
		String nuevoNombreArchivoFisico = this.eliminaCaracteresEspeciales(inventario.getInvGestion()
				+ inventario.getInvAduana() + inventario.getInvNroreg() + inventario.getInvEmbarque());

		// armamos el nuevo nombre q tendrá el archivo copiado
		String nuevoNombreArchivo = nuevoNombreArchivoFisico + "-" + numeroNombreArchivo[1] + ".tif";

		// verificamos si el registro ya existe en General (codError.E06)
		Optional<General> optionalGeneral = this.buscaGeneralExistente(nitConcesionario, nitConcesionario,
				tipoDocumento, nuevoNombreArchivo, inventario.getInvAduana(), inventario.getInvParte(), recinto);

		if (optionalGeneral.isPresent()) {
			archivoResultado.setCodError("E06");
			archivoResultado.setTipoDocumento(tipoDocumento);
			return archivoResultado;
		}

		// copiamos el archivo con su nuevo nombre
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

		archivoResultado.setInventario(inventario);
		archivoResultado.setNuevoNombreArchivo(nuevoNombreArchivo);
		archivoResultado.setTipoDocumento(tipoDocumento);

		return archivoResultado;
	}

	/**
	 * función que registra en la tabla Archivo de la bd Digitalización el archivo
	 * procesado
	 * 
	 * @return
	 */
	public Archivo registrarArchivo(String nombreArchivoOrigen, String nuevoNombreArchivo, LocalDateTime fechaProceso,
			String pathOrigen, String pathDestino, Boolean onError, String recinto) {

		Archivo archivo = new Archivo();

		if (!onError) {
			archivo.setNomArchivo(nuevoNombreArchivo);
			archivo.setFecPro(fechaProceso);
			archivo.setOrigen(pathOrigen + "//" + nombreArchivoOrigen);
			archivo.setDestin(pathDestino + "//" + nuevoNombreArchivo);
			archivo.setRecinto(recinto);
		} else {
			archivo.setFecPro(fechaProceso);
			archivo.setOrigen(pathOrigen + "//" + nombreArchivoOrigen);
			archivo.setRecinto(recinto);
		}

		return this.archivoService.saveOrUpdate(archivo);
	}

	/**
	 * función que modifica un registro en la tabla Archivo de la bd Digitalización
	 * 
	 * @return
	 */
	public Archivo modificarArchivo(Archivo archivo) {

		return this.archivoService.saveOrUpdate(archivo);
	}

	/**
	 * función que registra en la tabla General de la bd Digitalización el archivo
	 * procesado
	 * 
	 * @return
	 */
	public General registrarGeneral(TipoDocumento tipoDoc, String nuevoNombreArchivo, String codAduana, String tramite,
			LocalDateTime fechaEmision, LocalDateTime fechaProceso, Archivo archivo, String recinto) {

		General general = new General();
		general.setCnsCodConc(nitConcesionario);
		general.setTipoDocumento(tipoDoc);
		general.setCnsEmisor(nitConcesionario);
		general.setArchivo(archivo);
		general.setCnsAduTra(codAduana);
		general.setCnsNroTra(tramite);
		general.setCnsFechaEmi(fechaEmision);
		general.setCnsFechaPro(fechaProceso);
		general.setCnsEstado("A");
		general.setRecinto(recinto);

		return this.generalService.saveOrUpdate(general);
	}

	/**
	 * función que registra en la tabla Relación de la bd Digitalización el archivo
	 * procesado
	 * 
	 * @return
	 */
	public Relacion registrarRelacion(TipoDocumento tipoDoc1, String codAdu1, String tra1, LocalDateTime fechaEmi1,
			TipoDocumento tipoDoc2, String codAdu2, String tra2, LocalDateTime fechaEmi2,
			LocalDateTime fechaFinalProceso, String recinto) {

		Relacion relacion = new Relacion();
		relacion.setTipoDocumento1(tipoDoc1);
		relacion.setCnsAduTra1(codAdu1);
		relacion.setCnsNroTra1(tra1);
		relacion.setCnsEmisor1(nitConcesionario);
		relacion.setCnsFechaEmi1(fechaEmi1);
		relacion.setTipoDocumento2(tipoDoc2);
		relacion.setCnsAduTra2(codAdu2);
		relacion.setCnsNroTra2(tra2);
		relacion.setCnsEmisor2(nitConcesionario);
		relacion.setCnsFechaEmi2(fechaEmi2);
		relacion.setCnsEstado("A");
		relacion.setFecPro(fechaFinalProceso);
		relacion.setRecinto(recinto);

		return this.relacionService.saveOrUpdate(relacion);
	}

	/**
	 * función que registra en la tabla ErrorProceso de la bd Digitalización un
	 * error ocurrido
	 * 
	 * @return
	 */
	public ErrorProceso registrarErrorProceso(String recinto, TipoDocumento tipoDocumento, String codError,
			Archivo archivo, LocalDateTime fecPro) {

		ErrorProceso errorProceso = new ErrorProceso();
		errorProceso.setArchivo(archivo);
		errorProceso.setE3Cod(recinto);
		errorProceso.setFecPro(fecPro);

		// buscamos el tipo de error
		TipoError tipoError = tipoErrorService.findById(codError).get();
		errorProceso.setTipoError(tipoError);

		errorProceso.setTipoDocumento(tipoDocumento);

		return errorProcesoService.saveOrUpdate(errorProceso);
	}

	/**
	 * funcion q convierte un texto con la fecha a LocalDateTime
	 */
	public LocalDateTime fechaStringToDate(String cadenaFecha) {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime fechaconvertida = LocalDateTime.parse(cadenaFecha, formatoFecha);
		return fechaconvertida;
	}

	/** función q devuelve un listado de los archivos en un directorio **/
	public List<File> listaArchivosDirectorio(String dir) throws IOException {
		try (Stream<Path> stream = Files.list(Paths.get(dir))) {
			return stream.sorted().filter(file -> !Files.isDirectory(file))
					.filter(Path -> Path.toString().toUpperCase().endsWith(".TIF")).map(Path::toFile)
					.collect(Collectors.toList());
		}
	}

	/**
	 * funcion q revisa si el registro ya existe en General return null si no existe
	 */
	public Optional<General> buscaGeneralExistente(String cnsCodConc, String cnsEmisor, TipoDocumento tipoDocumento,
			String nombreArchivoDestino, String cnsAduTra, String cnsNroTra, String recinto) {

		Optional<General> optionalGeneral = this.generalService.buscarExistente(cnsCodConc, cnsEmisor, tipoDocumento,
				nombreArchivoDestino, cnsAduTra, cnsNroTra, recinto);

		return optionalGeneral;
	}

	/**
	 * funcion q revisa si el registro ya existe en Relacion return null si no
	 * existe
	 */
	public Optional<Relacion> buscaRelacionExistente(String cnsAduTra1, String cnsNroTra1, String cnsEmisor1,
			LocalDateTime cnsFechaEmi1, String cnsAduTra2, String cnsNroTra2, String cnsEmisor2,
			LocalDateTime cnsFechaEmi2, TipoDocumento tipoDocumento1, TipoDocumento tipoDocumento2, String recinto) {

		Optional<Relacion> optionalRelacion = relacionService.buscarExistente(cnsAduTra1, cnsNroTra1, cnsEmisor1,
				cnsFechaEmi1, cnsAduTra2, cnsNroTra2, cnsEmisor2, cnsFechaEmi2, tipoDocumento1, tipoDocumento2,
				recinto);

		return optionalRelacion;
	}

	/**
	 * funcion q registra en Archivo el registro en conflicto
	 */
	public ErrorProceso registraErrorProceso(String nombreArch, LocalDateTime fechaFinalProceso,
			String directorioOrigen, String recinto, ArchivoResultadoDTO archivoResultado) {

		// registramos el archivo en conflicto
		Archivo archivo = this.registrarArchivo(nombreArch, null, fechaFinalProceso, directorioOrigen, null, true,
				recinto);

		// registramos el error
		ErrorProceso errorProceso = this.registrarErrorProceso(recinto, archivoResultado.getTipoDocumento(),
				archivoResultado.getCodError(), archivo, fechaFinalProceso);

		return errorProceso;
	}

	/*
	 * función que elimina todos lo caracteres no alfanumericos
	 */
	public String eliminaCaracteresEspeciales(String cadena) {
		cadena = cadena.replaceAll("[^A-Za-z0-9]+", "");
		return cadena;
	}

	/*
	 * función que obtiene los totales del resultado final del proceso
	 */
	public ResultadoProcesoDTO calculoTotalesProceso(LocalDateTime fechaFinalProceso, int totalArchivosProcesados,
			String recinto) {
		ResultadoProcesoDTO resultadoProceso = new ResultadoProcesoDTO();

		List<CantidadTipoErrorDTO> listaCantidadTipoError = tipoErrorService.buscarTotalPorTipoError(fechaFinalProceso,
				recinto);
		Integer totalRegistrosError = errorProcesoService.buscarTotalRegistrosError(fechaFinalProceso, recinto);
		Integer totalRegistrosGeneral = generalService.buscarTotalRegistrosGeneral(fechaFinalProceso, recinto);
		Integer totalRegistrosRelacion = relacionService.buscarTotalRegistrosRelacion(fechaFinalProceso, recinto);

		resultadoProceso.setListaCantidadTipoError(listaCantidadTipoError);
		resultadoProceso.setTotalArchivosProcesados(totalArchivosProcesados);
		resultadoProceso.setTotalRegistrosError(totalRegistrosError);
		resultadoProceso.setTotalRegistrosGeneral(totalRegistrosGeneral);
		resultadoProceso.setTotalRegistrosRelacion(totalRegistrosRelacion);

		return resultadoProceso;
	}

}
