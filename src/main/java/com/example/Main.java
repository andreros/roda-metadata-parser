package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * Main class.
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //log.info("Starting application");
        System.out.println("Starting application");

        //Get context
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        MetadataParser metadataParser = new MetadataParser();

        //Parse EAD 2002 File
        //Get file from resources folder
        try {
            File ead2002File = new File(classLoader.getResource("ead2002.xml").getFile());
            metadataParser.parseEAD2002(ead2002File);
        } catch (NullPointerException e) {
            System.out.println("XML EAD 2002 file not found.");
        }

//        //Parse Dublin Core Simple 2002-12-12 File
//        //Get file from resources folder
//        try {
//            File dcSimpleDC20021212File = new File(classLoader.getResource("dc_SimpleDC20021212.xml").getFile());
//            metadataParser.parseDublinCoreSimple20021212(dcSimpleDC20021212File);
//        } catch (NullPointerException e) {
//            System.out.println("XML Dublin Core Simple 2002-12-12 file not found.");
//        }
//
//        //Parse Key-value File
//        //Get file from resources folder
//        try {
//            File keyValueFile = new File(classLoader.getResource("key-value.xml").getFile());
//            metadataParser.parseKeyValue(keyValueFile);
//        } catch (NullPointerException e) {
//            System.out.println("XML Key-value file not found.");
//        }

    }

}
