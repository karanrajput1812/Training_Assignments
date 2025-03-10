import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { RoleService } from '../service/role.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class welcomeGuard implements CanActivate{
  constructor(private rs: RoleService) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    if(route.data[0] == this.rs.role)
      return true;
    else 
      return false;
  }
}