import { Component } from '@angular/core';
import { ChessboardComponent } from '../chessboard/chessboard.component';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [ChessboardComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss'
})
export class GameComponent {
}
