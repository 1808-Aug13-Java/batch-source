import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServeService {

  url = 'https://jsonplaceholder.typicode.com/users';
  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    console.log('getUsers Called');
    return this.http.get<User[]>(this.url);
  }
}
