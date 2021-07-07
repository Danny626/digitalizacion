package com.albo.util;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.albo.digitalizacion.model.Relacion;

public class XmlRelacionUtil {

	public Document generaDocumentRelacion(List<Relacion> listaRelacion) throws ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// raiz rowset
		Document document = builder.newDocument();

		Element rowSet = document.createElement("main");
		document.appendChild(rowSet);

		String nroTra1;
		String nroTra2;
		int contadorReg = 0;
		int errorNroTra1 = 0;
		int errorNroTra2 = 0;

		for (Relacion r : listaRelacion) {
			// row
			Element row = document.createElement("DATA_RECORD");
			rowSet.appendChild(row);

			// tipo documento1
			Element tipoDoc1 = document.createElement("CNS_TIPODOC1");
			tipoDoc1.appendChild(document.createTextNode(r.getTipoDocumento1().getCodigo()));
			row.appendChild(tipoDoc1);

			// codigo aduana1
			Element codAduana1 = document.createElement("CNS_ADUTRA1");
			codAduana1.appendChild(document.createTextNode(r.getCnsAduTra1()));
			row.appendChild(codAduana1);

			// nro tramite1
			Element nroTramite1 = document.createElement("CNS_NROTRA1");
			nroTra1 = r.getCnsNroTra1().replace(" ", "");
			StringBuilder nuevoNroTra1 = new StringBuilder();

			if (r.getTipoDocumento1().getCodigo().equals("960") || r.getTipoDocumento1().getCodigo().equals("B74")) {

				if (nroTra1.length() < 15) {
					String[] parts = r.getCnsNroTra1().split(" ");
					nuevoNroTra1.append(parts[0] + parts[1] + parts[2]);
					int difN = 7 - parts[3].length();

					while (difN > 0) {
						nuevoNroTra1.append("0");
						difN--;
					}

					nuevoNroTra1.append(parts[3]);
				}

				if (nuevoNroTra1.toString().length() != 15) {
					errorNroTra1++;
					nuevoNroTra1.append("ERROR");
					System.out.println("Error tamaño NROTRA1 (mayor a 15): " + r.getCnsNroTra1());
				}
			}

			nroTramite1.appendChild(document.createTextNode(nuevoNroTra1.toString()));
			row.appendChild(nroTramite1);

			// emisor1
			Element emisor1 = document.createElement("CNS_EMISOR1");
			emisor1.appendChild(document.createTextNode(r.getCnsEmisor1()));
			row.appendChild(emisor1);

			// fecha emision1
			Element fechaEmision1 = document.createElement("CNS_FECHAEMI1");
			fechaEmision1.appendChild(document.createTextNode(r.getCnsFechaEmi1().format(formatterDate) + "00:00:00"));
			row.appendChild(fechaEmision1);

			// tipo documento2
			Element tipoDoc2 = document.createElement("CNS_TIPODOC2");
			tipoDoc2.appendChild(document.createTextNode(r.getTipoDocumento2().getCodigo()));
			row.appendChild(tipoDoc2);

			// codigo aduana2
			Element codAduana2 = document.createElement("CNS_ADUTRA2");
			codAduana2.appendChild(document.createTextNode(r.getCnsAduTra2()));
			row.appendChild(codAduana2);

			// nro tramite2
			Element nroTramite2 = document.createElement("CNS_NROTRA2");
			StringBuilder nuevoNroTra2 = new StringBuilder();

			if (r.getTipoDocumento2().getCodigo().equals("932")) {
				nroTra2 = r.getCnsNroTra2().replace(" ", "");

				if (nroTra2.length() < 15) {
					String[] parts = r.getCnsNroTra2().split(" ");
					nuevoNroTra2.append(parts[0] + parts[1] + parts[2]);
					int difN = 7 - parts[3].length();

					while (difN > 0) {
						nuevoNroTra2.append("0");
						difN--;
					}

					nuevoNroTra2.append(parts[3]);
				}

				if (nuevoNroTra2.toString().length() != 15) {
					errorNroTra2++;
					nuevoNroTra2.append("ERROR");
					System.out.println("Error NROTRA2 (mayor a 15): " + r.getCnsNroTra2());
				}
			}

			if (r.getTipoDocumento2().getCodigo().equals("380")) {
				nroTra2 = r.getCnsNroTra2();
				String[] parts = nroTra2.split(" ");
				nuevoNroTra2.append(r.getFecPro().getYear());
				nuevoNroTra2.append(r.getCnsAduTra2());
				int difN = 10 - parts[2].length();

				while (difN > 0) {
					nuevoNroTra2.append("0");
					difN--;
				}

				nuevoNroTra2.append(parts[2]);

				if (nuevoNroTra2.toString().length() != 17) {
					errorNroTra2++;
					nuevoNroTra2.append("ERROR");
					System.out.println("Error NROTRA2: " + nuevoNroTra2.toString());
				}
			}

			nroTramite2.appendChild(document.createTextNode(nuevoNroTra2.toString()));
			row.appendChild(nroTramite2);

			// emisor2
			Element emisor2 = document.createElement("CNS_EMISOR2");
			emisor2.appendChild(document.createTextNode(r.getCnsEmisor2()));
			row.appendChild(emisor2);

			// fecha emision2
			Element fechaEmi2 = document.createElement("CNS_FECHAEMI2");
			fechaEmi2.appendChild(document.createTextNode(r.getCnsFechaEmi2().format(formatterDate) + "00:00:00"));
			row.appendChild(fechaEmi2);

			// estado
			Element estado = document.createElement("CNS_ESTADO");
			estado.appendChild(document.createTextNode(r.getCnsEstado()));
			row.appendChild(estado);

			contadorReg++;
		}

		System.out.println("NroTramite1 con error: " + errorNroTra1);
		System.out.println("NroTramite2 con error: " + errorNroTra2);
		System.out.println(contadorReg + " registros Relacion exportados en Total.");

		return document;
	}
}
