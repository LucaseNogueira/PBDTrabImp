package core.dao;

import core.dao.utils.DatabaseTypes;
import core.dao.utils.NameVerification;
import core.shell.controller.ShellControllerInterface;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateTable {

    private ShellControllerInterface shellController;
    private String message;
    private String binPath;
    private String xmlPath;
    private String xmlRoot;
    List<String> colNames;
    List<String> colTypes;
    
    private File xmlFile;
    private File binFile;

    public CreateTable(ShellControllerInterface sc,String dbName) {
        this.shellController = sc;
        colNames = new ArrayList<>();
        colTypes = new ArrayList<>();
        
        this.binPath =  CheckRepositories.getStrDatabasePath(dbName);
        this.xmlPath = CheckRepositories.getStrMetadataPath(dbName);
    }
    
    public void createTableName(String name){
        if(NameVerification.nameApproved(name)){
            xmlFile = new File(xmlPath+"\\"+name+".xml");
            binFile = new File(binPath+"\\"+name+".dat");
            
            xmlRoot = name;
            
        }else{
            message += "Error. Invalidade com uma das sequintes diretrizes\n"
                    + "Suporte de no maximo 20 caracteres para o nome de tabelas!!!\n"
                    + "Suporte de caracteres e caracteres especiais não acentoados apenas!!!\n\n";
        }
    }

    public void createColumn(String column) {
        if(NameVerification.nameApproved(column)){
            colNames.add(column);
        }else{
            message += "Error. Invalidade com uma das sequintes diretrizes\n"
                    + "Suporte de no maximo 20 caracteres para o nome de colunas!!!\n"
                    + "Suporte de caracteres e caracteres especiais não acentoados apenas!!!\n\n";
        }
    }
    
    public void typeColumn(String type){
        if(DatabaseTypes.typeApproved(type)){
            colTypes.add(type);
        }else{
            message += "Error. Tipo de dado invalido.\n"
                    + "Tipos de dados aceitos (int,float,char[20])\n\n";
        }
    }

    public void createFiles(){
        if(message == null){
            try {
                for(String s : colNames){
                    System.out.println("Nome: "+s);
                }
                for(String s : colTypes){
                    System.out.println("Tipo: "+s);
                }
//                inicio criacao arquivo xml
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                
                Document docXml = db.newDocument();
                
                Element root = docXml.createElement(xmlRoot);
                docXml.appendChild(root);
                
                Element line = docXml.createElement("line");
                Attr id = docXml.createAttribute("genericId");
                id.setValue("1");
                
                line.setAttributeNode(id);
                root.appendChild(line);
                
                Element columns;
                Attr type;
                for(int i = 0; i < colNames.size(); i++){
                    columns = docXml.createElement(colNames.get(i));
                    type = docXml.createAttribute("type");
                    type.setValue(colTypes.get(i));
                    
                    columns.setAttributeNode(type);
                    line.appendChild(columns);
                }
                
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                
                DOMSource documentFont = new DOMSource(docXml);
                
                StreamResult documentFinal = new StreamResult(xmlFile);
                
                t.transform(documentFont, documentFinal);
//                fim criacao arquivo xml
                
                binFile.createNewFile();//criacao do arquivo binario
                
            } catch (IOException ex) {
                Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(CreateTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Deu Ruim");
        }
    }
}
