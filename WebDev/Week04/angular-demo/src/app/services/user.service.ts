import { Injectable } from '@angular/core';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  	URL: string = 'https://jsonplaceholder.typicode.com/users';

	// Marking a field as 'private' makes it. This is a declared variable that is priate to this class. 
	constructor(private http: HttpClient) { }

	// Gets a json from a web service. Uses our custom 'User' inteface in the modules folder. 
	getUsers(): Observable<User[]> {
		return this.http.get<User[]>(this.URL);
	}

	// Gets a json from a web service. Uses our custom 'User' inteface in the modules folder. 
	getUser(id: number): Promise<User> {
		return this.http.get<User>(this.URL+"/"+id).toPromise();
	}
}
