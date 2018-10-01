$(document).ready(function() {
  const num = 4;
  var main = $('#main');
  var valsarr = ['a','b','a','b'];
  for(let i = 0; i<num; i++) {
    var box = $('<div></div>');
    box.attr('class', 'box');
    box.attr('activated', 'false');
    box.attr('value', function(j, val) {
      return valsarr.pop(); 
    });
    box.text(i);
    main.append(box);
  }
  var activated = undefined;
  var switchValue = function(e) {
    var val = e.attr('value');
    e.attr('value', e.text());
    e.text(val);
    if(e.attr('activated') !== 'true') 
      e.attr('activated', 'true');
    else 
      e.attr('activated', 'false');
    return e;
  }
  main.children().on('click', function() {
    activated = switchValue($(this));
    $(this).parent().children().toArray().forEach(function(e) {
      var $target = $(e.target);
      var curText = activated.text();
      var tarText = $(e).text();
      if(curText === tarText) {
        activated.toggle();
        $(e).toggle();
      }
      else if ($(e).attr('activated') === 'true') {
        switchValue($(e));
        $(e).parent().children().toArray().forEach(function(e) {
          if($(e).attr('activated') === 'true') {
            switchValue($(e));
          }
        });
      }
    });
    
  });
});
