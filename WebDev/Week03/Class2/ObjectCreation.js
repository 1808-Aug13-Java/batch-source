
let p1 = {"name": "asdf", "age":5};

// Make a person function
function makePerson(name, age) {
	return {"name":name, "age", age};
}

let p2 = makePerson(name, age);

// Make a person constructor
function Person(name, age) {
	this.name = name;
	this.age = age;
}

// Call person constructor
let p3 = new Person("Mark", 24);

