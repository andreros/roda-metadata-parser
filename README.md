# README #

RODA Metadata Parser is an application that reads XML metadata files in the [EAD 2002](https://www.loc.gov/ead/eadschema.html), 
[Dublin Core 2002-12-12](http://dublincore.org/schemas/xmls/) or Key-value formats, the descriptive metadata formats 
supported by [RODA Repository](https://demo.roda-community.org/).

### Set up and Run this project ###

##### Dependencies #####

This project is a [maven project](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html). 
Any IDE supporting maven projects should automatically recognise and download the dependencies needed to run the application.

##### Test Files #####

An example of each XML descriptive metadata format supported by RODA is located inside the ```/src/main/resources/``` folder.
The ```dc_SimpleDC20021212.xml``` file contains metadata in the Dublin Core 2002-12-12 format, the ```ead2002.xml``` file in the 
EAD 2002 and the ```key-value.xml``` file in the Key-value format.

##### Log4J Logs Folder Configuration #####

1. Open the file ```/src/main/resources/log4j.properties```.
2. Edit the variable ```log``` and point it to an existing directory, i.e. ```log = /path/to/logs_directory```. 
This is the folder where the application's logs will be written to.


##### Running the project in IntelliJ #####


1. Open the project in [IntelliJ](https://www.jetbrains.com/idea/).
2. Navigate to the class ```/src/main/java/com.example/Main.java``` in the project file browser.
3. Right click the file ```Main.java``` and choose the option ```Run 'Main.main()'```.
4. The application output will be shown in the ```Run``` panel located in the bottom of the IDE window.


## Author

**André Rosa**

* <https://bitbucket.org/candrelsrosa>
* <https://facebook.com/candrelsrosa>
* <https://twitter.com/candrelsrosa>



## License

Code released under [the GPL 3.0 license](https://opensource.org/licenses/GPL-3.0). Docs released under [Creative Commons](https://creativecommons.org/licenses/by/4.0/legalcode).