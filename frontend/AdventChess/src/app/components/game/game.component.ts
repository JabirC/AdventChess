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
  username!: string;

  constructor(private route: ActivatedRoute, private router: Router, private webSocketService: WebSocketService) {
  }
  
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.mode = params['mode'];
      // console.log(this.mode);
    });
  }


}
