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
                String didNodeName = didNodeChild.getNodeName();

                if (didNodeName.equals("unittitle")) {
                    System.out.println("....unittitle: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (didNodeName.equals("unitid")) {
                    System.out.println("....unitid: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node unitidNode = didNodeChild.getAttributes().item(j);
                        String unitidNodeName = unitidNode.getNodeName();

                        if (unitidNodeName.equals("countrycode")) {
                            System.out.println("....unitid countrycode: " + unitidNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (unitidNodeName.equals("repositorycode")) {
                            System.out.println("....unitid repositorycode: " + unitidNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }

                if (didNodeName.equals("unitdate")) {
                    System.out.println("....unitdate: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node unitdateNode = didNodeChild.getAttributes().item(j);
                        String unitdateNodeName = unitdateNode.getNodeName();

                        if (unitdateNodeName.equals("label")) {
                            System.out.println("....unitdate label: " + unitdateNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (unitdateNodeName.equals("normal")) {
                            System.out.println("....unitdate normal: " + unitdateNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }
                if (didNodeName.equals("physdesc")) {
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node physdescNode = didNodeChild.getChildNodes().item(j);
                        String physdescNodeName = physdescNode.getNodeName();

                        if (physdescNodeName.equals("extent")) {
                            System.out.println("....physdesc extent: " + physdescNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (physdescNodeName.equals("dimensions")) {
                            System.out.println("....physdesc dimensions: " + physdescNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (physdescNodeName.equals("physfacet")) {
                            System.out.println("....physdesc physfacet: " + physdescNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (physdescNodeName.equals("#text")) {
                            String physdesc = physdescNode.getNodeValue().replaceAll("\\n", "").trim();
                            if (physdesc.length() > 0) {
                                System.out.println("....physdesc: " + physdesc);
                            }
                        }
                    }
                }

                if (didNodeName.equals("repository")) {
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node repositoryNode = didNodeChild.getChildNodes().item(j);
                        String repositoryNodeName = repositoryNode.getNodeName();

                        if (repositoryNodeName.equals("corpname")) {
                            System.out.println("....repository corpname: " + repositoryNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }

                if (didNodeName.equals("langmaterial")) {
                    for (int j = 0; j < didNodeChild.getChildNodes().getLength(); j++) {
                        Node langmaterialNode = didNodeChild.getChildNodes().item(j);
                        String langmaterialNodeName = langmaterialNode.getNodeName();

                        if (langmaterialNodeName.equals("language")) {
                            System.out.println("....langmaterial language: " + langmaterialNode.getChildNodes().item(0)
                                    .getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (langmaterialNodeName.equals("#text")) {
                            String langmaterial = langmaterialNode.getNodeValue().replaceAll("\\n", "").trim();
                            if (langmaterial.length() > 0) {
                                System.out.println("....langmaterial: " + langmaterial);
                            }
                        }
                    }
                }
                if (didNodeName.equals("note")) {
                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node noteAttributeNode = didNodeChild.getAttributes().item(j);
                        String noteAttributeNodeValue = noteAttributeNode.getNodeValue();

                        if (noteAttributeNodeValue.equals("sourcesDescription")) {
                            System.out.println("....note sourcesDescription: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (noteAttributeNodeValue.equals("generalNote")) {
                            System.out.println("....note generalNote: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }

                if (didNodeName.equals("origination")) {
                    String origination = didNodeChild.getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim();
                    if (origination.length() > 0) {
                        System.out.println("....origination: " + origination);
                    }

                    for (int j = 0; j < didNodeChild.getAttributes().getLength(); j++) {
                        Node originationAttributeNode = didNodeChild.getAttributes().item(j);
                        String originationAttributeNodeName = originationAttributeNode.getNodeValue();

                        if (originationAttributeNodeName.equals("creator")) {
                            System.out.println("....origination creator: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (originationAttributeNodeName.equals("producer")) {
                            System.out.println("....origination producer: " + didNodeChild.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }
                }

                if (didNodeName.equals("materialspec")) {
                    System.out.println("....materialspec: " + didNodeChild.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }
            }

            // *********************************************************************************************************

            for (int i = 0; i < archdescNode.getChildNodes().getLength(); i++) {
                Node archdescChildNode = archdescNode.getChildNodes().item(i);
                String archdescChildNodeName = archdescChildNode.getNodeName();

                if (archdescChildNodeName.equals("odd")) {
                    for (int j = 0; j < archdescChildNode.getAttributes().getLength(); j++) {
                        Node oddAttributeNode = archdescChildNode.getAttributes().item(j);
                        String oddAttributeNodeName = oddAttributeNode.getNodeValue();

                        if (oddAttributeNodeName.equals("levelOfDetail")) {
                            System.out.println("..odd levelOfDetail: " + archdescChildNode.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }

                        if (oddAttributeNodeName.equals("statusDescription")) {
                            System.out.println("..odd statusDescription: " + archdescChildNode.getChildNodes().item(1)
                                    .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                        }
                    }

                }

                if (archdescChildNodeName.equals("scopecontent")) {
                    System.out.println("..scopecontent: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("arrangement")) {
                    System.out.println("..arrangement: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("appraisal")) {
                    System.out.println("..appraisal: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("acqinfo")) {
                    System.out.println("..acqinfo: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("accruals")) {
                    System.out.println("..accruals: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("custodhist")) {
                    System.out.println("..custodhist: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("processinfo")) {
                    System.out.println("..processinfo: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(1)
                            .getChildNodes().item(0).getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("originalsloc")) {
                    System.out.println("..originalsloc: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("altformavail")) {
                    System.out.println("..altformavail: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("relatedmaterial")) {
                    System.out.println("..relatedmaterial: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("accessrestrict")) {
                    System.out.println("..accessrestrict: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("userestrict")) {
                    System.out.println("..userestrict: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("otherfindaid")) {
                    System.out.println("..otherfindaid: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("phystech")) {
                    System.out.println("..phystech: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("bibliography")) {
                    System.out.println("..bibliography: " + archdescChildNode.getChildNodes().item(1).getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (archdescChildNodeName.equals("prefercite")) {
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
                String nodeName = simpledcNode.getNodeName();

                if (nodeName.equals("title")) {
                    System.out.println("..title: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("identifier")) {
                    System.out.println("..identifier: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("creator")) {
                    System.out.println("..creator: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("date")) {
                    System.out.println("..date: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("description")) {
                    System.out.println("..description: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("publisher")) {
                    System.out.println("..publisher: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("contributor")) {
                    System.out.println("..contributor: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("rights")) {
                    System.out.println("..rights: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("language")) {
                    System.out.println("..language: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("coverage")) {
                    System.out.println("..coverage: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("format")) {
                    System.out.println("..format: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("relation")) {
                    System.out.println("..relation: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("subject")) {
                    System.out.println("..subject: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("type")) {
                    System.out.println("..type: " + simpledcNode.getChildNodes().item(0)
                            .getNodeValue().replaceAll("\\n", "").trim());
                }

                if (nodeName.equals("source")) {
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

                    String attributeName = metadataNode.getAttributes().item(0).getNodeValue();

                    if (attributeName.equals("id")) {
                        System.out.println("..field id: " + metadataNode.getChildNodes().item(0)
                                .getNodeValue().replaceAll("\\n", "").trim());
                    }

                    if (attributeName.equals("title")) {
                        System.out.println("..field title: " + metadataNode.getChildNodes().item(0)
                                .getNodeValue().replaceAll("\\n", "").trim());
                    }

                    if (attributeName.equals("producer")) {
                        System.out.println("..field producer: " + metadataNode.getChildNodes().item(0)
                                .getNodeValue().replaceAll("\\n", "").trim());
                    }

                    if (attributeName.equals("date")) {
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
