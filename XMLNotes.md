# XML - eXtensible Markup Language 
* like HTML, not a programming language but a markup language
* designed to transport and store data in a way that is both human and machine readable
* language agnostic

```XML 
<?xml version="1.0" encoding="UTF-8"?>
<students>
    <student>
        <firstName>Veronica</firstName> <lastName>Jones</lastName>
    </student>
    <student>
        <firstName>Paul</firstName> <lastName>McCormick</lastName>
    </student>
    <student>
        <firstName>Lola</firstName> <lastName>Nunez</lastName>
    </student>
</students> 
``` 
 
```javascript
{students:[
    { firstName:"Veronica", lastName:"Jones" },
    { firstName:"Paul", lastName:"McCormick" },
    { firstName:"Lola", lastName:"Nunez" }
]}
```

```JSON
"{"student":[
    { "firstName":"Veronica", "lastName":"Jones" },
    { "firstName":"Paul", "lastName":"McCormick" },
    { "firstName":"Lola", "lastName":"Nunez" }
]}"
```
We have been using JSON over XML because:
- faster parsing
- more compatible w javascript
- less verbose
- more universal (tags won't change with different developers)


<br>

#### Well-Formed vs. Valid XML

<hr>

| Well Formed XML | Valid XML |
| --------------- | --------- |
| Follows basic syntactic rules <ul><li>begins with XML declaration</li><li>unique root element</li><li>starting and ending tag must match</li><li>elements are case sensitive</li><li>elements must be properly nested</li></ul> Well formed document is not necessarily valid | XML which follows a predefined structure  <ul><li>Document Type Definition</li><li>XML Schema Definition</li></ul> Valid XML will also be well formed | 

<br>

#### Document Type Definition (DTD)

<hr>

- written in its own DTD syntax
- define element names and relationships 
- PCDATA and CDATA - parsed character data and unparsed character data
    - PCDATA is text that will be parsed by a parser. Tags inside the text will be treated as markup and entities will be expanded.
    - CDATA is text that will not be parsed by a parser. Tags inside the text will not be treated as markup and entities will not be expanded.
- cant be more specific with types
    
#### XML Schema Definition (XSD)

<hr>

- written in XML
- can declare simple and complex types
- can declare number and order of child elements
- can declare default and fixed values for elements and attributes 

#### XML Namespace

<hr>

- anyone can create their own markup with their own tags
- namespace allows us to differentiate from people using the same tag name for different purposes
- namespace declaration xmlns:prefix = "namespace"
- unique identifier URI - so if you have a registered URL you can associate it with your namespace to prevent naming conflict
- URL doesn't necessarily have anything to do with the namespace itself, just a way to prevent name clashes

```XML 
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs=".../xmlSchema">
<employees>
    <employee>
        <firstName>John</firstName> <lastName>Doe</lastName>
    </employee>
    <employee>
        <firstName>Anna</firstName> <lastName>Smith</lastName>
    </employee>
    <employee>
        <firstName>Peter</firstName> <lastName>Jones</lastName>
    </employee>
</employees> 
``` 

<br>

#### Parsing XML

<hr>

**JAXP - Java API for XML Processing** 

<br>

| Document Object Model (DOM) | Simple API for XML Parsing |
| --------------------- | ------------------ |
| <ul><li>loads full xml file into memory and creates a tree representation of it</li><li>much faster for small/medium sized files because of in-memory retrieval</li><li>can get very expensive (memory-wise) for larger files</li></ul>|<ul><li>read only</li><li>event based - "streams" XML data without loading all of it into memory</li></ul> |

**marshalling**: converting java objects to xml

**unmarshalling**: converting xml to java objects
