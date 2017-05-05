package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * XML metadata parser class.
 */
public class MetadataParser {

    /**
     * Constructor.
     */
    public MetadataParser() {}

    /**
     * Method responsible for parsing an XML metadata file in the EAD2002 format.
     * @param file The filename of the file to be parsed.
     */
    public void parseEAD2002(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            String rootElement = doc.getDocumentElement().getNodeName();
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("EAD2002 Parser");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println(rootElement);

            // *********************************************************************************************************
            //archdesc element
            Node archdescNode = doc.getElementsByTagName("archdesc").item(0);
            System.out.println(".." + archdescNode.getNodeName());
            for (int i = 0; i < archdescNode.getAttributes().getLength(); i++) {
                Node archdescAttributeNode = archdescNode.getAttributes().item(i);

                if (archdescAttributeNode.getNodeName().equals("level")) {
                    System.out.println(".." + archdescNode.getNodeName() + " level: " + archdescAttributeNode.getChildNodes().item(0).getNodeValue());
                }
            }

            // *********************************************************************************************************
            //did element
            Node didNode = archdescNode.getChildNodes().item(1);
            System.out.println(".." + didNode.getNodeName());

            //did child nodes
            NodeList didNodeChildren = didNode.getChildNodes();

            for (int i = 0; i < didNodeChildren.getLength(); i++) {

                Node didNodeChild = didNodeChildren.item(i);

                if (didNodeChild.getNodeName().equals("unittitle")) {
                    System.out.println("....unittitle: " + didNodeChild.getChildNodes().item(0).getNodeValue());
                }

                if (didNodeChild.getNodeName().equals("unitid")) {
                    System.out.println("....unitid: " + didNodeChild.getChildNodes().item(0).getNodeValue());

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node unitidNode = didNodeChild.getAttributes().item(j);

                        if (unitidNode.getNodeName().equals("countrycode")) {
                            System.out.println("....unitid countrycode: " + unitidNode.getChildNodes().item(0).getNodeValue());
                        }

                        if (unitidNode.getNodeName().equals("repositorycode")) {
                            System.out.println("....unitid repositorycode: " + unitidNode.getChildNodes().item(0).getNodeValue());
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("unitdate")) {
                    System.out.println("....unitdate: " + didNodeChild.getChildNodes().item(0).getNodeValue());

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node unitidNode = didNodeChild.getAttributes().item(j);

                        if (unitidNode.getNodeName().equals("label")) {
                            System.out.println("....unitdate label: " + unitidNode.getChildNodes().item(0).getNodeValue());
                        }

                        if (unitidNode.getNodeName().equals("normal")) {
                            System.out.println("....unitdate normal: " + unitidNode.getChildNodes().item(0).getNodeValue());
                        }
                    }
                }
                if (didNodeChild.getNodeName().equals("physdesc")) {
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node physdescChildNode = didNodeChild.getChildNodes().item(j);

                        if (physdescChildNode.getNodeName().equals("extent")) {
                            System.out.println("....physdesc extent: " + physdescChildNode.getChildNodes().item(0).getNodeValue());
                        }

                        if (physdescChildNode.getNodeName().equals("dimensions")) {
                            System.out.println("....physdesc dimensions: " + physdescChildNode.getChildNodes().item(0).getNodeValue());
                        }

                        if (physdescChildNode.getNodeName().equals("physfacet")) {
                            System.out.println("....physdesc physfacet: " + physdescChildNode.getChildNodes().item(0).getNodeValue());
                        }

                        if (physdescChildNode.getNodeName().equals("#text")) {
                            String physdesc = physdescChildNode.getNodeValue().replaceAll("\\n", "").trim();
                            if (physdesc.length() > 0) {
                                System.out.println("....physdesc: " + physdesc);
                            }
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("repository")) {
                    System.out.println("....repository");

                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node repositoryChildNode = didNodeChild.getChildNodes().item(j);

                        if (repositoryChildNode.getNodeName().equals("corpname")) {
                            System.out.println("....repository corpname: " + repositoryChildNode.getChildNodes().item(0).getNodeValue());
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("langmaterial")) {
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node langmaterialChildNode = didNodeChild.getChildNodes().item(j);

                        if (langmaterialChildNode.getNodeName().equals("language")) {
                            System.out.println("....langmaterial language: " + langmaterialChildNode.getChildNodes().item(0).getNodeValue());
                        }

                        if (langmaterialChildNode.getNodeName().equals("#text")) {
                            String langmaterial = langmaterialChildNode.getNodeValue().replaceAll("\\n", "").trim();
                            if (langmaterial.length() > 0) {
                                System.out.println("....langmaterial: " + langmaterial);
                            }
                        }
                    }
                }
                if (didNodeChild.getNodeName().equals("note")) {
                    System.out.println("....note");

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node noteNode = didNodeChild.getAttributes().item(j);

                        if (noteNode.getNodeValue().equals("sourcesDescription")) {
                            System.out.println("....note sourcesDescription: " + didNodeChild.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                        }

                        if (noteNode.getNodeValue().equals("generalNote")) {
                            System.out.println("....note generalNote: " + didNodeChild.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("origination")) {
                    String origination = didNodeChild.getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim();
                    if (origination.length() > 0) {
                        System.out.println("....origination: " + origination);
                    }

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node originationNode = didNodeChild.getAttributes().item(j);

                        if (originationNode.getNodeValue().equals("creator")) {
                            System.out.println("....origination creator: " + didNodeChild.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                        }

                        if (originationNode.getNodeValue().equals("producer")) {
                            System.out.println("....origination producer: " + didNodeChild.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("materialspec")) {
                    System.out.println("....materialspec: " + didNodeChild.getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                }
            }

            // *********************************************************************************************************

            for (int i = 0; i < archdescNode.getChildNodes().getLength(); i++) {
                Node archdescChildNode = archdescNode.getChildNodes().item(i);

                if (archdescChildNode.getNodeName().equals("odd")) {
                    for (int j = 0; j < archdescChildNode.getAttributes().getLength(); j++) {
                        Node oddAttributeNode = archdescChildNode.getAttributes().item(j);

                        if (oddAttributeNode.getNodeValue().equals("levelOfDetail")) {
                            System.out.println("..odd levelOfDetail: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                        }

                        if (oddAttributeNode.getNodeValue().equals("statusDescription")) {
                            System.out.println("..odd statusDescription: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                        }
                    }

                }

                if (archdescChildNode.getNodeName().equals("scopecontent")) {
                    System.out.println("..scopecontent: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                }

                if (archdescChildNode.getNodeName().equals("arrangement")) {
                    System.out.println("..arrangement: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                }

                if (archdescChildNode.getNodeName().equals("appraisal")) {
                    System.out.println("..appraisal: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                }

                if (archdescChildNode.getNodeName().equals("acqinfo")) {
                    System.out.println("..acqinfo: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                }

                if (archdescChildNode.getNodeName().equals("accruals")) {
                    System.out.println("..accruals: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                }

                if (archdescChildNode.getNodeName().equals("custodhist")) {
                    System.out.println("..custodhist: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
                }

            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for parsing an XML metadata file in the Dublin Core Simple 2002-12-12 format.
     * @param file The filename of the file to be parsed.
     */
    public void parseDublinCoreSimple20021212(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            String rootElement = doc.getDocumentElement().getNodeName();
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Dublin Core Simple 2002-12-12 Parser");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Root element: " + rootElement);
        } catch (NullPointerException e) {
            System.out.println("XML file '" + file.getName() + "' not found.");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for parsing an XML metadata file in the Key-value format.
     * @param file The filename of the file to be parsed.
     */
    public void parseKeyValue(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            String rootElement = doc.getDocumentElement().getNodeName();
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Key-value Parser");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Root element: " + rootElement);
        } catch (NullPointerException e) {
            System.out.println("XML file '" + file.getName() + "' not found.");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
