PLANTUML = vendor/plantuml.jar
SSRS = doc/SSRS/
SSRS_images = $(SSRS)images/
SSRS_doc = $(SSRS)SSRS_Team4.pdf
class_diagram_source = $(SSRS)ClassDiagrams/
class_diagram_output = $(SSRS_images)ClassDiagrams/
class_diagrams = $(notdir $(basename $(wildcard $(class_diagram_source)*.uml)))
class_diagrams_png = $(addprefix $(class_diagram_output), $(addsuffix .png, $(class_diagrams)))

all: SSRS class_diagrams

SSRS: $(SSRS_doc)

$(SSRS_doc): $(SSRS)SSRS_Team4.tex $(class_diagrams_png)
	latexmk -pdf -output-directory=$(SSRS) $<
	#clean up latex files
	latexmk -c -pdf -output-directory=$(SSRS) $<

class_diagrams: $(class_diagrams_png)

clean:
	rm -rf $(class_diagrams_png)
	rm -rf $(SSRS_doc)

%.pdf: %.tex
	latex -output-format=pdf $<

$(class_diagram_output)%.png: $(class_diagram_source)%.uml
	java -jar $(PLANTUML) -o $(realpath $(class_diagram_output)) $<
