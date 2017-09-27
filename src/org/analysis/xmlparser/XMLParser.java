package org.analysis.xmlparser;

import org.analysis.cfg.CFGEdge;
import org.analysis.cfg.CFGVertex;
import org.analysis.cfg.IntraCFG;
import org.analysis.concolic_testing.TestCaseValue;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.nio.charset.Charset;

public class XMLParser {

	private IntraCFG cfg;
	private TestCaseValue sv;

	public XMLParser(IntraCFG cfg, TestCaseValue sv) {
		this.cfg = cfg;
		this.sv = sv;
	}

	private String refineString(String src) {
		String result = src;
		if (src.startsWith("%"))
			result = src.substring(1);
		if (src.contains("0x"))
			result = "op_addr_" + result;
		return result;
	}

	private String refineValue(Operand op, AbsoluteAddress addr) {
		String result = "";
		// if (op.getClass().getSimpleName().equals("Immediate"))
		// result = Integer.toString(((Immediate) op).getNumber().intValue());
		if (op.getClass().getSimpleName().equals("X86Register")
				|| op.getClass().getSimpleName().equals("X86RegisterPart"))
			result = op.toString().substring(1);
		else if (op.getClass().getSimpleName().equals("Immediate"))
			result = ((Immediate) op).getNumber().intValue() + "";
		else if (op.getClass().getSimpleName().equals("X86PCRelativeAddress"))
			result = ((X86PCRelativeAddress) op).getEffectiveValue(addr.getValue()) + "";
		else if (op.getClass().getSimpleName().equals("X86MemoryOperand"))
			result = "op_addr_" + ((X86MemoryOperand) op).toString();

		return result;
	}

	public void WriteXMLFile() {
		try {
			Document doc = new Document();
			Element root = new Element("CFG");
			doc.addContent(root);

			// root
			Element vertexs = new Element("Vertexs");
			root.addContent(vertexs);

			// root/Vertexs
			for (CFGVertex vertex : cfg.getCFGVertices().getListCFGVertex()) {
				// CFGVertex
				// if (vertex.getAddr().getValueOperand() == 4198408)
				// System.out.println();

				Element cfgvertex = new Element("CFGVertex");
				int type = vertex.getSize() > 0 ? vertex.getType()[0] : 0;
				cfgvertex.setAttribute(new Attribute("Type", String.valueOf(type)));
				cfgvertex.setAttribute(new Attribute("Address", String.valueOf(vertex.getAddr().getValue())));
				cfgvertex.setAttribute(new Attribute("HexAddress", String.valueOf(vertex.getAddr().toString())));

				vertexs.addContent(cfgvertex);

				// CFGVertex/Ins
				Element ins = new Element("Ins");

				String operator = vertex.getIns().getName();
				String operand = vertex.getIns().getOperandCount() > 0 ? refineValue(vertex.getIns().getOperand(0),
						vertex.getAddr()) : "";
				// String value = "";
				String value = vertex.getIns().getOperandCount() > 1 ? refineValue(vertex.getIns().getOperand(1),
						vertex.getAddr()) : "";
				String instruction = operator + " " + operand + ", " + value;

				if (operator.contains("cmp") || operator.contains("je") || operator.contains("ja")
						|| operator.contains("jg") || operator.contains("jl") || operator.contains("jge")
						|| operator.contains("jle") || operator.contains("jnl")) {
					operand = "";
					value = "";
				}
				if (type == 7 || operator.contains("jmp")) {
					value = vertex.getIns().getOperandCount() > 0 ? refineValue(vertex.getIns().getOperand(0),
							vertex.getAddr()) : "";
					operand = "";
				}

				ins.addContent(new Element("Content").setText(instruction));
				ins.addContent(new Element("Operand").setText(operand));
				ins.addContent(new Element("Operator").setText(operator));
				ins.addContent(new Element("Value").setText(value));

				cfgvertex.addContent(ins);

				// CFGVertex/EdgeOut
				Element edgeout = new Element("EdgeOut");
				for (CFGEdge edge : vertex.getOut()) {
					// CFGEdge
					Element cfgedge = new Element("CFGEdge");
					cfgedge.setAttribute(new Attribute("Destination", String.valueOf(edge.getDestination().getAddr()
							.getValue())));
					cfgedge.setAttribute(new Attribute("HexDestination", String.valueOf(edge.getDestination().getAddr()
							.toString())));
					// if (edge.getDestination().getAddr()
					// .getValueOperand() == 4198412)
					// System.out.println();
					edgeout.addContent(cfgedge);

					// CFGEdge/SymbolicExecution
					Element se = new Element("SymbolicExecution");
					for (int index = 0; index < edge.getCond().getConnectorSize(); index++) {
						// if
						String left = refineString(edge.getCond().getLeftC(index));
						// if (left.equals("al"))
						// System.out.println("Debug");
						String right = refineString(edge.getCond().getRightC(index));
						String con = refineString(edge.getCond().getConnector(index));
						String text = left + " " + con + " " + right;
						Element cond = new Element("string").setText(text);
						se.addContent(cond);
					}

					cfgedge.addContent(se);
				}
				cfgvertex.addContent(edgeout);

				// Element edgeIn = new Element("EdgeIn");
				// for (CFGEdge edge : vertex.getIn()) {
				// // CFGEdge
				// Element cfgedge = new Element("CFGEdge");
				// cfgedge.setAttribute(new Attribute("Destination",
				// String.valueOf(edge.getDestination().getAddr()
				// .getValueOperand())));
				// cfgedge.setAttribute(new Attribute("HexDestination",
				// String.valueOf(edge.getDestination().getAddr()
				// .toString())));
				// // if (edge.getDestination().getAddr()
				// // .getValueOperand() == 4198412)
				// // System.out.println();
				// edgeout.addContent(cfgedge);
				//
				// // CFGEdge/SymbolicExecution
				// Element se = new Element("SymbolicExecution");
				// for (int index = 0; index <
				// edge.getCond().getConnectorSize(); index++) {
				// //if
				// String left = refineString(edge.getCond().getLeftC(index));
				// //if (left.equals("al"))
				// // System.out.println("Debug");
				// String right = refineString(edge.getCond().getRightC(index));
				// String con =
				// refineString(edge.getCond().getConnector(index));
				// String text = left + " " + con + " " + right;
				// Element cond = new Element("string").setText(text);
				// se.addContent(cond);
				// }
				//
				// cfgedge.addContent(se);
				// }
				// cfgvertex.addContent(edgeIn);
			}

			// root/SymbolValues
			Element symbolvalues = new Element("SymbolValues");
			for (int i = 0; i < sv.getNumVar(); i++) {
				Element value = new Element("Symbol");
				value.addContent(new Element("Name").addContent(sv.getVarName(i)));
				value.addContent(new Element("Value").addContent(sv.getVarValue(i) + ""));
				symbolvalues.addContent(value);
			}
			root.addContent(symbolvalues);
			// root/SymbolValues/Symbol
			// root/SymbolValues/Symbol/Name
			// root/SymbolValues/Symbol/Value

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			FileWriter fw = new FileWriter("cfg.xml");
			xmlOutput.output(doc, fw);
			fw.close();
			// xmlOutput.output(doc, System.out);

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	public String readXML(String fileName) {
		String result = "";
		InputStream fis;
		BufferedReader br;
		String line;
		try {
			fis = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
				if (line.contains("address"))
					result = line;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));
		if (result.length() > 8)
			result = result.substring(8, result.length() - 1);

		br = null;
		fis = null;
		return result;
	}

	// public String ReadXMLFile() {
	//
	// String result = "";
	//
	// SAXBuilder builder = new SAXBuilder();
	// File xmlFile = new File("c:\\file.xml");
	//
	// try {
	//
	// Document document = (Document) builder.build(xmlFile);
	// Element rootNode = document.getRootElement();
	// List list = rootNode.getChildren("staff");
	//
	// for (int i = 0; i < list.size(); i++) {
	//
	// Element node = (Element) list.get(i);
	//
	// System.out.println("First Name : "
	// + node.getChildText("firstname"));
	// System.out.println("Last Name : "
	// + node.getChildText("lastname"));
	// System.out.println("Nick Name : "
	// + node.getChildText("nickname"));
	// System.out.println("Salary : " + node.getChildText("salary"));
	//
	// }
	//
	// } catch (IOException io) {
	// System.out.println(io.getMessage());
	// } catch (JDOMException jdomex) {
	// System.out.println(jdomex.getMessage());
	// }
	// return result;
	// }

	// public void ModifyXMLFile() {
	// try {
	// SAXBuilder builder = new SAXBuilder();
	// File xmlFile = new File("f:\\cfg.xml");
	//
	// Document doc = (Document) builder.build(xmlFile);
	// Element rootNode = doc.getRootElement();
	// // update staff id attribute
	// Element staff = rootNode.getChild("staff");
	// staff.getAttribute("id").setValue("2");
	//
	// // add new age element
	// Element age = new Element("age").setText("28");
	// staff.addContent(age);
	//
	// XMLOutputter xmlOutput = new XMLOutputter();
	// xmlOutput.setFormat(Format.getPrettyFormat());
	// xmlOutput.output(doc, System.out);
	// xmlOutput.output(doc, new FileWriter("c:\\file.xml"));
	// System.out.println("File updated!");
	// } catch (IOException io) {
	// io.printStackTrace();
	// } catch (JDOMException e) {
	// e.printStackTrace();
	// }
	// }

}
