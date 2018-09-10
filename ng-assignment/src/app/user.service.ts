import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private userUrl = "https://jsonplaceholder.typicode.com/users";
  getUsers(): Observable <User[]> {
    return this.http.get<User[]>(this.userUrl);
  }
}
