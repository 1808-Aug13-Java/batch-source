import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  usersUrl = 'https://jsonplaceholder.typicode.com/users';

  constructor(private http: HttpClient) { }

  getUsers(){
    return this.http.get<User>(this.usersUrl);
  }
  // getUsers(): Observable<User[]>{
  //   return this.http.get<User[]>(this.url);
  // }

  // getUser(id: number): Promise<User>{
  //   return this.http.get<User>(this.url + "/" + id).toPromise();
  // }
}
