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
                    System.out.println(".." + archdescNode.getNodeName() + " level: " + archdescAttributeNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
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
                    System.out.println("....unittitle: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (didNodeChild.getNodeName().equals("unitid")) {
                    System.out.println("....unitid: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node unitidNode = didNodeChild.getAttributes().item(j);

                        if (unitidNode.getNodeName().equals("countrycode")) {
                            System.out.println("....unitid countrycode: " + unitidNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (unitidNode.getNodeName().equals("repositorycode")) {
                            System.out.println("....unitid repositorycode: " + unitidNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("unitdate")) {
                    System.out.println("....unitdate: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node unitidNode = didNodeChild.getAttributes().item(j);

                        if (unitidNode.getNodeName().equals("label")) {
                            System.out.println("....unitdate label: " + unitidNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (unitidNode.getNodeName().equals("normal")) {
                            System.out.println("....unitdate normal: " + unitidNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }
                if (didNodeChild.getNodeName().equals("physdesc")) {
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node physdescChildNode = didNodeChild.getChildNodes().item(j);

                        if (physdescChildNode.getNodeName().equals("extent")) {
                            System.out.println("....physdesc extent: " + physdescChildNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (physdescChildNode.getNodeName().equals("dimensions")) {
                            System.out.println("....physdesc dimensions: " + physdescChildNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (physdescChildNode.getNodeName().equals("physfacet")) {
                            System.out.println("....physdesc physfacet: " + physdescChildNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
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
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node repositoryChildNode = didNodeChild.getChildNodes().item(j);

                        if (repositoryChildNode.getNodeName().equals("corpname")) {
                            System.out.println("....repository corpname: " + repositoryChildNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("langmaterial")) {
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node langmaterialChildNode = didNodeChild.getChildNodes().item(j);

                        if (langmaterialChildNode.getNodeName().equals("language")) {
                            System.out.println("....langmaterial language: " + langmaterialChildNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
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
                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node noteNode = didNodeChild.getAttributes().item(j);

                        if (noteNode.getNodeValue().equals("sourcesDescription")) {
                            System.out.println("....note sourcesDescription: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (noteNode.getNodeValue().equals("generalNote")) {
                            System.out.println("....note generalNote: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
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
                            System.out.println("....origination creator: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (originationNode.getNodeValue().equals("producer")) {
                            System.out.println("....origination producer: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }

                if (didNodeChild.getNodeName().equals("materialspec")) {
                    System.out.println("....materialspec: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }
            }

            // *********************************************************************************************************

            for (int i = 0; i < archdescNode.getChildNodes().getLength(); i++) {
                Node archdescChildNode = archdescNode.getChildNodes().item(i);

                if (archdescChildNode.getNodeName().equals("odd")) {
                    for (int j = 0; j < archdescChildNode.getAttributes().getLength(); j++) {
                        Node oddAttributeNode = archdescChildNode.getAttributes().item(j);

                        if (oddAttributeNode.getNodeValue().equals("levelOfDetail")) {
                            System.out.println("..odd levelOfDetail: " + archdescChildNode.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (oddAttributeNode.getNodeValue().equals("statusDescription")) {
                            System.out.println("..odd statusDescription: " + archdescChildNode.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }

                }

                if (archdescChildNode.getNodeName().equals("scopecontent")) {
                    System.out.println("..scopecontent: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("arrangement")) {
                    System.out.println("..arrangement: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("appraisal")) {
                    System.out.println("..appraisal: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("acqinfo")) {
                    System.out.println("..acqinfo: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("accruals")) {
                    System.out.println("..accruals: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("custodhist")) {
                    System.out.println("..custodhist: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("processinfo")) {
                    System.out.println("..processinfo: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(1)
                            .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("originalsloc")) {
                    System.out.println("..originalsloc: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("altformavail")) {
                    System.out.println("..altformavail: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("relatedmaterial")) {
                    System.out.println("..relatedmaterial: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("accessrestrict")) {
                    System.out.println("..accessrestrict: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("userestrict")) {
                    System.out.println("..userestrict: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("otherfindaid")) {
                    System.out.println("..otherfindaid: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("phystech")) {
                    System.out.println("..phystech: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("bibliography")) {
                    System.out.println("..bibliography: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNode.getNodeName().equals("prefercite")) {
                    System.out.println("..prefercite: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
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

            // *********************************************************************************************************
            //simpledc root element
            System.out.println(rootElement);

            for (int i = 0; i < doc.getDocumentElement().getChildNodes().getLength(); i++) {
                Node simpledcNode = doc.getDocumentElement().getChildNodes().item(i);

                if (simpledcNode.getNodeName().equals("title")) {
                    System.out.println("..title: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("identifier")) {
                    System.out.println("..identifier: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("creator")) {
                    System.out.println("..creator: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("date")) {
                    System.out.println("..date: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("description")) {
                    System.out.println("..description: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("publisher")) {
                    System.out.println("..publisher: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("contributor")) {
                    System.out.println("..contributor: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("rights")) {
                    System.out.println("..rights: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("language")) {
                    System.out.println("..language: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("coverage")) {
                    System.out.println("..coverage: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("format")) {
                    System.out.println("..format: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("relation")) {
                    System.out.println("..relation: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("subject")) {
                    System.out.println("..subject: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("type")) {
                    System.out.println("..type: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (simpledcNode.getNodeName().equals("source")) {
                    System.out.println("..source: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }
            }


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

            // *********************************************************************************************************
            //metadata root element
            System.out.println(rootElement);

            for (int i = 0; i < doc.getDocumentElement().getChildNodes().getLength(); i++) {
                Node metadataNode = doc.getDocumentElement().getChildNodes().item(i);

                if (metadataNode.getNodeName().equals("field")) {

                    if (metadataNode.getAttributes().item(0).getNodeValue().equals("id")) {
                        System.out.println("..field id: " + metadataNode.getChildNodes().item(0)
                                .getNodeValue().replaceAll("\\n", "").trim());
                    }

                    if (metadataNode.getAttributes().item(0).getNodeValue().equals("title")) {
                        System.out.println("..field title: " + metadataNode.getChildNodes().item(0)
                                .getNodeValue().replaceAll("\\n", "").trim());
                    }

                    if (metadataNode.getAttributes().item(0).getNodeValue().equals("producer")) {
                        System.out.println("..field producer: " + metadataNode.getChildNodes().item(0)
                                .getNodeValue().replaceAll("\\n", "").trim());
                    }

                    if (metadataNode.getAttributes().item(0).getNodeValue().equals("date")) {
                        System.out.println("..field date: " + metadataNode.getChildNodes().item(0)
                                .getNodeValue().replaceAll("\\n", "").trim());
                    }
                }

            }

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
