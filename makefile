PLANTUML = vendor/plantuml.jar
class_diagrams = doc/SSRS/class-diagrams/
SSRS_doc = doc/SSRS/SSRS_Team4.pdf

all: SSRS

SSRS: $(SSRS_doc)

$(SSRS_doc): doc/SSRS/SSRS_Team4.tex $(patsubst %.uml,%.png,$(wildcard $(class_diagrams)*.uml))
	latexmk -cd -pdf -output-directory=doc/SSRS $<
	#clean up latex files
	latexmk -c -cd -pdf -output-directory=doc/SSRS $< 

clean:
	rm -rf $(wildcard $(class_diagrams)*.png)
	rm -rf $(design_doc)

%.pdf: %.tex
	latex -output-format=pdf $<

%.png: %.uml
	java -jar $(PLANTUML) $<
