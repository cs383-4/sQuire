all: doc

doc: class-diagram.png

class-diagram.png: doc/class-diagram.uml
	java -jar vendor/plantuml.jar  $^
