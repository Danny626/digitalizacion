package com.albo.util;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.albo.digitalizacion.model.Total;

public class XmlTotalUtil {

	public Document generaDocumentTotal(List<Total> listaTotal) throws ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// raiz rowset
		Document document = builder.newDocument();
		
//		String cabecera = "!DOCTYPE main ["
//				+ "<!ELEMENT main (DATA_RECORD*)>"
//				+ "<!ELEMENT DATA_RECORD (CNS_CODCON,CNS_TIPDOC,CNS_FECENV,CNS_ESTADO,CNS_CANTIDAD?)+>"
//				+ "<!ELEMENT CNS_CODCON (#PCDATA)>"
//				+ "<!ELEMENT CNS_TIPDOC (#PCDATA)>"
//				+ "<!ELEMENT CNS_FECENV (#PCDATA)>"
//				+ "<!ELEMENT CNS_ESTADO (#PCDATA)>"
//				+ "<!ELEMENT CNS_CANTIDAD (#PCDATA)>"
//				+ "]";
//		
//		cabecera = escapeMetaCharacters(cabecera);
//		
//		Element rowHead = document.createElement(cabecera);
//		document.appendChild(rowHead);

		Element rowSet = document.createElement("main");
		document.appendChild(rowSet);

		int contadorReg = 0;

		for (Total t : listaTotal) {
			// row
			Element row = document.createElement("DATA_RECORD");
			rowSet.appendChild(row);

			// codigo concesionario
			Element codConc = document.createElement("CNS_CODCON");
			codConc.appendChild(document.createTextNode(t.getCnsCodCon()));
			row.appendChild(codConc);

			// tipo documento
			Element tipoDoc = document.createElement("CNS_TIPDOC");
			tipoDoc.appendChild(document.createTextNode(t.getTipoDocumento().getCodigo()));
			row.appendChild(tipoDoc);

			// fecha envio
			Element fechaEnvio = document.createElement("CNS_FECENV");
			fechaEnvio.appendChild(document.createTextNode(t.getCnsFecEnv().format(formatterDate) + " 00:00:00"));
			row.appendChild(fechaEnvio);

			// estado
			Element estado = document.createElement("CNS_ESTADO");
			estado.appendChild(document.createTextNode(t.getCnsEstado()));
			row.appendChild(estado);

			// cantidad
			Element cantidad = document.createElement("CNS_CANTIDAD");
			cantidad.appendChild(document.createTextNode(t.getCnsCantidad().toString()));
			row.appendChild(cantidad);

			contadorReg++;
		}

		System.out.println(contadorReg + " registros Total exportados en Total.");

		return document;
	}
	
	public String escapeMetaCharacters(String inputString){
	    final String[] metaCharacters = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&","%"};

	    for (int i = 0 ; i < metaCharacters.length ; i++){
	        if(inputString.contains(metaCharacters[i])){
	            inputString = inputString.replace(metaCharacters[i],"\\"+metaCharacters[i]);
	        }
	    }
	    return inputString;
	}
}
