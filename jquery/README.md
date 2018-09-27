# jQuery
* JavaScript library
* simplifies and adds additional functionality to JS
* basic syntax is `$(selector).action()`
  * `$` defines/assesses jQuery
  * the action is what is to be performed on the elements
* only a single global object
  * `$` and `jQuery` point to the same global object
* the `jQuery` object is actually a function
  * when you invoke the function, it traverses the DOM
  * the `jQuery` function has methods like `ajax`
* jQuery uses CSS selectors
  * Allow you to select and manipulate HTML elements
  * `$([element])`
  * `$(.[class])`
  * `$(#[id])`
* The Document Ready Event
  ```
  $(document).ready(function(){ 
		//jQuery method here
  });
  ```
  * This exists to prevent any jQuery code from running before the document is finished loading
	* Ex of actions that can fail if methods are run before doc is fully loaded - trying to hide an element that hasn’t been created yet

* the `ajax` method takes in an object
  * this object has members to configure the request
  * no more worrying about what browser you are in
  * this is one step... remember with plain JavaScript it was four steps

```
$.ajax({
  method   : "get",
  url      : "someUrl",
  success  : function(){},
  error    : function(){},
  complete : function(){}
});
```

