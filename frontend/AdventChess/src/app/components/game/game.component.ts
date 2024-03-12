import { Component } from '@angular/core';
import { ChessboardComponent } from '../chessboard/chessboard.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [ChessboardComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss'
})
export class GameComponent {

  constructor(private router: Router){
  }
  gameMenu(){
    this.router.navigate(['/mode']);
  }
}
