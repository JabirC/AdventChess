import { Component } from '@angular/core';
import { ChessboardComponent } from '../chessboard/chessboard.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ChessboardComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  constructor(private router: Router){
  }
  gameMenu(){
    this.router.navigate(['/mode']);
  }
}
