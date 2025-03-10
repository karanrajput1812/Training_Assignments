import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {


  user_session: boolean = false;
  login_register: boolean = true;
  msg : string = "";
  userinfo : any = "";


  constructor() {
    console.log("Session object created......");
  }

  public user: { cid: number,username: string, password: string , accountNo: number }[] = [];

  addUser(obj: { cid: number,username: string, password: string , accountNo: number}) {
    this.user.push(obj);

    // this.login_register = false;

    console.log(this.user);
  }

  validateUser(obj: { username : string, password: string }) {
    for (let i = 0; i < this.user.length; i++) {
      if (this.user[i].username == obj.username && this.user[i].password == obj.password) {
        this.user_session = true;
        this.login_register = false;
        this.userinfo = this.user[i];
        return true;
      }
    }
    this.msg = "Invalid username or password";
    console.log(this.msg);
    return false;
  }


}
