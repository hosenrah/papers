DOCUMENT_NAME 	= Smarter_Home
OUTPUT_DIR    	= output

# Build the LaTeX document.
allUbuntu: outputdir report ubuntu cleanup

# Build the LaTeX document.
allMac: outputdir report mac cleanup

# Remove output directory.
clean:
	rm -rf $(OUTPUT_DIR)

# cleanup tempfiles
cleanup:
	rm -f $(DOCUMENT_NAME).aux rm -f $(DOCUMENT_NAME).acn $(DOCUMENT_NAME).glo $(DOCUMENT_NAME).ist $(DOCUMENT_NAME).lof $(DOCUMENT_NAME).log $(DOCUMENT_NAME).lot $(DOCUMENT_NAME).lol $(DOCUMENT_NAME).out $(DOCUMENT_NAME).toc $(DOCUMENT_NAME).alg $(DOCUMENT_NAME).glg $(DOCUMENT_NAME).gls $(DOCUMENT_NAME).acr  $(DOCUMENT_NAME).pdf
	
# Create LaTeX output directory.
outputdir:
	$(shell mkdir $(OUTPUT_DIR) 2>/dev/null)

# Generate PDF output from LaTeX input files.
report:
	pdflatex -interaction=errorstopmode $(DOCUMENT_NAME) 
	makeglossaries -q  $(DOCUMENT_NAME).glo
	makeglossaries -q  $(DOCUMENT_NAME).acn
	#bibtex $(DOCUMENT_NAME).aux
	biber $(DOCUMENT_NAME).bcf
	biber $(DOCUMENT_NAME).bcf
	pdflatex -interaction=errorstopmode $(DOCUMENT_NAME) #> error.txt
	cp $(DOCUMENT_NAME).pdf $(OUTPUT_DIR)

ubuntu:
	see output/$(DOCUMENT_NAME).pdf

mac:
	open output/$(DOCUMENT_NAME).pdf

fixBiber:
	#in case of biber error delete cache folder (Source: http://tex.stackexchange.com/questions/140814/biblatex-biber-fails-with-a-strange-error-about-missing-recode-data-xml-file)
	rm -rf `biber --cache`