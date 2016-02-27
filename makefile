PLANTUML = vendor/plantuml.jar
SSRS = doc/SSRS/
SSRS_images = $(SSRS)images/
SSRS_doc = $(SSRS)SSRS_Team4.pdf

use_casedescriptions = doc/use-case-descriptions/hw2-team4.pdf

class_diagram_source = $(SSRS)ClassDiagrams/
class_diagram_output = $(SSRS_images)ClassDiagrams/
class_diagrams = $(notdir $(basename $(wildcard $(class_diagram_source)*.uml)))
class_diagrams_png = $(addprefix $(class_diagram_output), $(addsuffix .png, $(class_diagrams)))

usecase_diagram_source = $(SSRS)UseCaseDiagrams/
usecase_diagram_output = $(SSRS_images)UseCaseDiagrams/
usecase_diagrams = $(notdir $(basename $(wildcard $(usecase_diagram_source)*.uml)))
usecase_diagrams_png = $(addprefix $(usecase_diagram_output), $(addsuffix .png, $(usecase_diagrams)))

sequence_diagram_source = $(SSRS)SequenceDiagrams/
sequence_diagram_output = $(SSRS_images)SequenceDiagrams/
sequence_diagrams = $(notdir $(basename $(wildcard $(sequence_diagram_source)*.uml)))
sequence_diagrams_png = $(addprefix $(sequence_diagram_output), $(addsuffix .png, $(sequence_diagrams)))


all: SSRS usecase_descriptions

SSRS: $(SSRS_doc)

print:
	echo $(sequence_diagrams_png)

usecase_descriptions: $(usecase_descriptions)

$(SSRS_doc): $(SSRS)SSRS_Team4.tex $(class_diagrams_png) $(usecase_diagrams_png) $(sequence_diagrams_png)
	latexmk -pdf -output-directory=$(SSRS) $<

clean:
	rm -rf $(class_diagrams_png)
	rm -rf $(usecase_diagrams_png)
	rm -rf $(sequence_diagrams_png)
	rm -rf $(SSRS_doc)
	rm -rf $(usecase_descriptions)
	latexmk -c -pdf -output-directory=$(SSRS) $(SSRS)SSRS_Team4.tex

%.pdf: %.tex
	pdflatex $<

$(class_diagram_output)%.png: $(class_diagram_source)%.uml
	java -jar $(PLANTUML) -o $(realpath $(class_diagram_output)) $<
$(usecase_diagram_output)%.png: $(usecase_diagram_source)%.uml
	java -jar $(PLANTUML) -o $(realpath $(usecase_diagram_output)) $<
$(sequence_diagram_output)%.png: $(sequence_diagram_source)%.uml
	java -jar $(PLANTUML) -o $(realpath $(sequence_diagram_output)) $<
