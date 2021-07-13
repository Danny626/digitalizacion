package com.albo.util;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.albo.digitalizacion.model.General;

public class XmlGeneralUtil {

	public Document generaDocumentGeneral(List<General> listaGeneral) throws ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// raiz rowset
		Document document = builder.newDocument();

//		String cabecera = "<!DOCTYPE main ["
//				+ "<!ELEMENT main (DATA_RECORD*)>"
//				+ "<!ELEMENT DATA_RECORD (CNS_CODCONC?,CNS_TIPODOC,CNS_EMISOR,CNS_NOMARCH?,CNS_ADUTRA,CNS_NROTRA,CNS_FECHA_EMI,CNS_FECHA_PRO?,CNS_ESTADO)+>"
//				+ "<!ELEMENT CNS_CODCONC (#PCDATA)>"
//				+ "<!ELEMENT CNS_TIPODOC (#PCDATA)>"
//				+ "<!ELEMENT CNS_EMISOR (#PCDATA)>"
//				+ "<!ELEMENT CNS_NOMARCH (#PCDATA)>"
//				+ "<!ELEMENT CNS_ADUTRA (#PCDATA)>"
//				+ "<!ELEMENT CNS_NROTRA (#PCDATA)>"
//				+ "<!ELEMENT CNS_FECHA_EMI (#PCDATA)>"
//				+ "<!ELEMENT CNS_FECHA_PRO (#PCDATA)>"
//				+ "<!ELEMENT CNS_ESTADO (#PCDATA)>"
//				+ "]>";
//		
//		cabecera = escapeMetaCharacters(cabecera);
//		
////		Element rowHead = document.createElement(cabecera);
//		document.createAttribute(cabecera);

		Element rowSet = document.createElement("main");
		document.appendChild(rowSet);

		int contadorReg = 0;

		for (General g : listaGeneral) {
			// row
			Element row = document.createElement("DATA_RECORD");
			rowSet.appendChild(row);

			// codigo concesionario
			Element codConcesionario = document.createElement("CNS_CODCONC");
			codConcesionario.appendChild(document.createTextNode(g.getCnsCodConc()));
			row.appendChild(codConcesionario);

			// tipo documento
			Element tipoDoc = document.createElement("CNS_TIPODOC");
			tipoDoc.appendChild(document.createTextNode(g.getTipoDocumento().getCodigo()));
			row.appendChild(tipoDoc);

			// emisor
			Element emisor = document.createElement("CNS_EMISOR");
			emisor.appendChild(document.createTextNode(g.getCnsEmisor()));
			row.appendChild(emisor);

			// nombre archivo
			Element nombreArch = document.createElement("CNS_NOMARCH");
			nombreArch.appendChild(document.createTextNode(g.getArchivo().getNomArchivo()));
			row.appendChild(nombreArch);

			// codigo aduana
			Element codAduana = document.createElement("CNS_ADUTRA");
			codAduana.appendChild(document.createTextNode(g.getCnsAduTra()));
			row.appendChild(codAduana);

			// nro tramite
			Element nroTramite = document.createElement("CNS_NROTRA");
			nroTramite.appendChild(document.createTextNode(
					g.getArchivo().getNomArchivo().substring(0, g.getArchivo().getNomArchivo().length() - 8)));
			row.appendChild(nroTramite);

			// fecha emision
			Element fechaEmision = document.createElement("CNS_FECHA_EMI");
			fechaEmision.appendChild(document.createTextNode(g.getCnsFechaEmi().format(formatterDate) + " 00:00:00"));
			row.appendChild(fechaEmision);

			// fecha proceso
			Element fechaPro = document.createElement("CNS_FECHA_PRO");
			fechaPro.appendChild(document.createTextNode(g.getCnsFechaPro().format(formatterDate) + " 00:00:00"));
			row.appendChild(fechaPro);

			// estado
			Element estado = document.createElement("CNS_ESTADO");
			estado.appendChild(document.createTextNode(g.getCnsEstado()));
			row.appendChild(estado);
			contadorReg++;
		}

		System.out.println(contadorReg + " registros General exportados en Total.");

		return document;
	}

	public String escapeMetaCharacters(String inputString) {
		final String[] metaCharacters = { "\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*", "+", "?", "|", "<",
				">", "-", "&", "%" };

		for (int i = 0; i < metaCharacters.length; i++) {
			if (inputString.contains(metaCharacters[i])) {
				inputString = inputString.replace(metaCharacters[i], "\\" + metaCharacters[i]);
			}
		}
		return inputString;
	}
}
