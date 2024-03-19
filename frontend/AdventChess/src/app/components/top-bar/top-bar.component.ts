import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [],
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.scss'
})
export class TopBarComponent {
  constructor(private router: Router){
  }
  gameMenu(){
    this.router.navigate(['/mode']);
  }
  home(){
    this.router.navigate(['/home']);
  }
}
