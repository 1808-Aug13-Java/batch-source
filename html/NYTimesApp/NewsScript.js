var baseUrl = "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=0b294e2043dd4cae88153d2bd2e6b5bc&q="

document.getElementById("submitBtn").addEventListener("click", searchArticles);

function searchArticles(){
    let input = document.getElementById("keywords").value;
    sendAjaxGet(baseUrl+input, displayArticles);
}

function sendAjaxGet(url, callback){
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            callback(this);

        }
    }

    xhr.open("GET", url);
    xhr.send();

}

function displayArticles(xhr){
    

    let articleList = document.getElementById("articleList");
    
    if(articleList){
        articleList.remove();
    }


    let row = document.createElement("div");
    row.id = "articleList";

    document.getElementById("articles").appendChild(row);


    let articlesArr = JSON.parse(xhr.responseText);

    for(article of articlesArr.response.docs){
        addRow(article.headline.main, article.web_url, article.multimedia[0].url, article.snippet);
    }

}

function addRow(headline, url, photo, snippet){

    let row = document.createElement("div");

    let cell1 = document.createElement("a");
    let cell2 = document.createElement("img");
    let cell3 = document.createElement("h4");

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(document.createElement("hr"));

    cell1.innerHTML =`<h4>${headline}</h4>`;
    cell1.href = url;
    cell2.src = "https://nytimes.com/"+photo;
    cell3.innerHTML = snippet;

    document.getElementById("articleList").appendChild(row);

}