import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ChessboardComponent } from './chessboard/chessboard.component';
import { TopBarComponent } from './top-bar/top-bar.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ChessboardComponent, TopBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'AdventChess';
}
