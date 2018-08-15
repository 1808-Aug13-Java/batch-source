#compile:
javac -d bin -sourcepath src -classpath bin src/com/revature/main/Demo.java

#run:
sudo java -classpath bin com.revature.main.Demo

You will find POLYMORPHISM in the following locations:
Demo.java lines: 19-26; 5-6; 

INHERITANCE:
Any of the child classes from Homininae

ABSTRACTION:
the use of abstract class - Homininae, which is superclass of the rest - exposes only user relevant functionality, namely in walk method(), by having abstract method. Optionally I would choose to have an Interface abstract all the essential functionality of classes but I couldn't think of more methods to make 

ENCAPSULATION:
Either hiding details in a wrapper (for example inside a class) or using access modifiers to restrict access to details - this can be seen in the use of protected and private access modifiers to force user to use the public methods

