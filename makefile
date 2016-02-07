PLANTUML = vendor/plantuml.jar
class_diagrams = doc/src/class-diagram.png\
	 doc/src/class-diagram2.png
design_doc = doc/design.pdf

all: doc

doc: $(design_doc)

$(design_doc): doc/src/design.tex $(class_diagrams)
	latexmk -cd -pdf -output-directory=doc $<
	#clean up latex files
	latexmk -c -cd -pdf -output-directory=doc $< 

clean:
	rm -rf $(class_diagrams)
	rm -rf $(design_doc)

%.pdf: %.tex
	latex -output-format=pdf $<

%.png: %.uml
	java -jar $(PLANTUML) $<
