import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private httpClient: HttpClient) { }

  private userUrl = "https://jsonplaceholder.typicode.com/users";
  getUsers(): Observable <string[]> {
    return this.http.get<string[]>(this.userUrl);
  }
}
