import { Component, HostListener } from '@angular/core';
import { ChessboardComponent } from '../chessboard/chessboard.component';
import { Router, ActivatedRoute, UrlSegment } from '@angular/router';
import { WebSocketService } from '../../shared/services/websocket.service';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [ChessboardComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss'
})
export class GameComponent {
  mode!: string;
  gameTime!: Number;
  username!: string;

  constructor(private route: ActivatedRoute, private router: Router, private webSocketService: WebSocketService) {
  }
  
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.mode = params['mode'];
      this.gameTime = params['time'];
      console.log(this.gameTime);
      console.log(this.mode)
    });
  }


}
