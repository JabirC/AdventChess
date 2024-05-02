import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  constructor(private router: Router){
  }

  navigateToPlay(){
    this.router.navigate(['/mode']);
  }

  navigateToWebsite(): void {
    window.open('https://github.com/JabirC/AdventChess', '_blank');
  }
}
