import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url: string = 'https://jsonplaceholder.typicode.com/users';
  constructor(private http: HttpClient) { }

// this is going to return an observable array of users
getUsers(): Observable<User[]>{ // Userr is data structure that we made up
  return this.http.get<User[]>(this.url); // making get request to this.url
}

getUser(id: number): Promise<User>{ // this will actually return an observable even though we want a Promise, so we must do .toPromise()
  return this.http.get<User>(this.url+"/"+id).toPromise();
}
}
