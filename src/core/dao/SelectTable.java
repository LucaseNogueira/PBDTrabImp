package core.dao;

import core.model.Database;
import core.model.Tables;
import core.shell.controller.ShellControllerInterface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SelectTable {

    private ShellControllerInterface shellController;
    private String dbName;
    private String xmlPath;
    private String message = null;

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    NodeList root;
    NodeList lineList;
    private int size;

    private Database banco;

    public SelectTable(ShellControllerInterface sc, String dbName) {
        this.dbName = dbName;
        this.shellController = sc;
        this.banco = new Database(dbName);

        try {
            this.dbf = DocumentBuilderFactory.newInstance();
            this.db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(InsertTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTableName(String text) {
        if (CheckRepositories.tableExists(dbName, text)) {
            this.xmlPath = CheckRepositories.getTableMetaPath(dbName, text);

            listLine();
        } else {
            message += "Error. Não existe esta tabela no banco de dados.\n\n";
        }
    }

    public void tableSelected() {
        if (message == null) {
            for (int i = 0; i < size; i++) {
                Node lineNode = lineList.item(i);

                Tables line = new Tables();

                if (lineNode.getNodeType()
                        == Node.ELEMENT_NODE) {
                    Element lineElement = (Element) lineNode;

                    NodeList colList = lineElement.getChildNodes();
                    for (int j = 0; j < colList.getLength(); j++) {
                        Element colElement = (Element) colList.item(j);

                        line.addColumnNames(colElement.getTagName());
                        if (colElement.getTextContent() == null) {
                            line.addColumnDatas("");
                        } else {
                            line.addColumnDatas(colElement.getTextContent());
                        }
                    }
                }

                banco.addTable(line);
            }

            shellController.showTable(banco);
        } else {
            shellController.failed(message);
        }

    }

    private void listLine() {
        try {
            doc = db.parse(xmlPath);

            root = doc.getElementsByTagName("xmlRoot");
            lineList = doc.getElementsByTagName("line");
            size = lineList.getLength();

        } catch (SAXException ex) {
            Logger.getLogger(InsertTable.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(InsertTable.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
