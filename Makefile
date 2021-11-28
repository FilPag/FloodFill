CLASSPATH = classes
SRC = src
PACKAGE = floodFill
MAINCLASS = Window


default:
		javac -d $(CLASSPATH) -cp $(CLASSPATH) $(SRC)/*.java

run: default
		java -cp $(CLASSPATH) $(PACKAGE).$(MAINCLASS)

clean:
	rm -r $(CLASSPATH)/*