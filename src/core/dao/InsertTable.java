package core.dao;

import core.shell.controller.ShellControllerInterface;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InsertTable {

    private ShellControllerInterface shellController;
    private String message = null;
    private String dbName;
    private String tbName;
    private List<String> colNames;
    private List<String> colTypes;
    private List<String> literals;
    private int pos = 0;

    private String binDirectory;

    private String xmlDirectory;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    NodeList root;
    NodeList lineList;
    private int size;

    public InsertTable(ShellControllerInterface sc, String dbName) {
        this.shellController = sc;
        this.dbName = dbName;
        this.colNames = new ArrayList<>();
        this.colTypes = new ArrayList<>();
        this.literals = new ArrayList<>();

        try {
            this.dbf = DocumentBuilderFactory.newInstance();
            this.db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTbDirectories(String name) {
        if (CheckRepositories.tableExists(dbName, name)) {
            tbName = name;
            binDirectory = CheckRepositories.getTablePath(dbName, name);
            xmlDirectory = CheckRepositories.getTableMetaPath(dbName, name);

            listLine();
        } else {
            message += "Error. Não existe esta tabela no banco de dados.\n\n";
        }
    }

    public void setTbColumn(String column) {
        if (columnApproved(column)) {
            this.colNames.add(column);
            this.colTypes.add(getTypeColumn(column));
        } else {
            message += "Error. Coluna não existe nesta tabela";
        }
    }

    public void addLiteral(String text) {
        literals.add(text);
    }

    public void finishInsert() {
        if (message == null) {
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(binDirectory));

                int repeat = countValues();
                List<Element> elements = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    elements.add((Element) lineList.item(i));
                }

                Element line = (Element) lineList.item(0);
                Element raiz = (Element) line.getParentNode();

                for (int i = 0; i < elements.size(); i++) {
                    raiz.appendChild(elements.get(i));
                }

                for (int i = 0; i < repeat; i++) {
                    Element novo = doc.createElement("line");
                    for (int j = 0; j < colNames.size(); j++) {
                        NodeList nodeCol = line.getElementsByTagName(colNames.get(j));
                        Element oldCol = (Element) nodeCol.item(0);
                        Element col = (Element) nodeCol.item(0);
                        col.setTextContent(nextLiteral());
                        novo.appendChild(col);
//                line.replaceChild(oldCol, col);

                        switch (col.getAttribute("type")) {
                            case "int":
                                int var1 = Integer.parseInt(col.getTextContent());
                                out.writeInt(var1);
                                break;
                            case "float":
                                float var2 = Float.parseFloat(col.getTextContent());
                                out.writeFloat(var2);
                                break;
                        }
                        if (col.getAttribute("type").length() > 4) {
                            String str = col.getAttribute("type").substring(0, 4);
                            if ("char".equals(str)) {
                                char[] var3 = Arrays.copyOf(col.getTextContent().toCharArray(), 20);
                                for(char n : var3){
                                    out.write(n);
                                }
                            }
                        }
                    }
                    raiz.appendChild(novo);
                    out.close();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t;
            try {
                t = tf.newTransformer();

                DOMSource documentFont = new DOMSource(doc);

                StreamResult documentFinal = new StreamResult(xmlDirectory);

                t.transform(documentFont, documentFinal);
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
            }

            message = "Inserção com sucesso!!!";
            shellController.success(message);
        } else {
            shellController.failed(message);
        }
    }

    private void listLine() {
        try {
            doc = db.parse(xmlDirectory);

            root = doc.getElementsByTagName("xmlRoot");
            lineList = doc.getElementsByTagName("line");
            size = lineList.getLength();
        } catch (SAXException ex) {
            Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean columnApproved(String column) {
        boolean aux = false;

        Node node = lineList.item(0);
        NodeList nodes = node.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            if (column.equals(element.getTagName())) {
                aux = true;
            }
        }

        return aux;
    }

    private String getTypeColumn(String column) {
        String aux = null;

        Node node = lineList.item(0);
        NodeList nodes = node.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            if (column == element.getTagName()) {
                aux = element.getAttribute("type");
            }
        }

        return aux;
    }

    private String nextLiteral() {
        String str = literals.get(pos);
        pos++;
        return str;
    }

    private int countValues() {
        return literals.size() / colNames.size();
    }

}
