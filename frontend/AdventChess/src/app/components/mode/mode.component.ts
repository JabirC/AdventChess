import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mode',
  standalone: true,
  imports: [],
  templateUrl: './mode.component.html',
  styleUrl: './mode.component.scss'
})
export class ModeComponent {

  constructor(private router: Router){
  }

  startGame(mode: string){
    this.router.navigate(['/game/' + mode]);
  }

}
