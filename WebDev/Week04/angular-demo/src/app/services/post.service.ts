import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  

	URL: string = 'https://jsonplaceholder.typicode.com/posts';

	// Marking a field as 'private' makes it. Somehow, this field is saved even after the constructor is called. 
	constructor(private http: HttpClient) { }

	// Gets a json from a web service. Uses our custom 'Post' inteface in the modules folder. 
	getPosts(): Observable<Post[]> {
		return this.http.get<Post[]>(this.URL);
	}

	// Gets a json from a web service. Uses our custom 'Post' inteface in the modules folder. 
	getPost(id: number): Promise<Post> {
		return this.http.get<Post>(this.URL+"/"+id).toPromise();
	}
}
