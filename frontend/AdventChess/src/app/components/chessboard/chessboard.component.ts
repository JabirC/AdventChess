import { Component, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-chessboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './chessboard.component.html',
  styleUrl: './chessboard.component.scss'
})
export class ChessboardComponent {
  screenWidth: number = window.innerWidth;
  screenHeight: number = window.innerHeight;

  constructor() {
    this.getScreenWidth();
  }

  @HostListener('window:resize', ['$event'])
  getScreenWidth(event?: Event): void {
    this.screenWidth = window.innerWidth;
    this.screenHeight = window.innerHeight;
  }

  isDarkSquare(row: number, col: number): boolean {
    return (row + col) % 2 !== 0;
  }
}
