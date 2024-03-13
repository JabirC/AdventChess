import { Component } from '@angular/core';
import { ChessboardComponent } from '../chessboard/chessboard.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [ChessboardComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss'
})
export class GameComponent {
  mode!: string;
  constructor(private route: ActivatedRoute, private router: Router) {}
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.mode = params['mode'];
      // console.log(this.mode);
    });
  }
}
