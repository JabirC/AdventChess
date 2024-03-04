import { Component, ElementRef, Renderer2, ViewChild, AfterViewInit, NgZone, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CdkDrag, DragDropModule} from '@angular/cdk/drag-drop';
import { HighlightOnDragDirective } from '../../shared/directives/highlight-on-drag.directive'

@Component({
  selector: 'app-chessboard',
  standalone: true,
  imports: [CommonModule, CdkDrag, DragDropModule, HighlightOnDragDirective],
  templateUrl: './chessboard.component.html',
  styleUrl: './chessboard.component.scss'
})
export class ChessboardComponent implements AfterViewInit {
  boardState: string[][] = [];
  isDragging = false;
  @ViewChild('chessboardContainer') chessboardContainer!: ElementRef;
  
  constructor(private renderer: Renderer2, private ngZone: NgZone) {
    // Initialize the boardState with a default chessboard configuration
    this.initializeBoard();
  }

  ngAfterViewInit(): void {
    this.setSquareSizes();
    this.setPieceSizes();
    const squares = this.chessboardContainer.nativeElement.querySelectorAll('.square');
  }

  private setSquareSizes(): void {
    if (this.chessboardContainer) {
      const containerWidth = this.chessboardContainer.nativeElement.clientWidth;
      const squareSize = containerWidth / 8;

      const squares = this.chessboardContainer.nativeElement.querySelectorAll('.square');
      squares.forEach((square: HTMLElement) => {
        this.renderer.setStyle(square, 'width', `${squareSize}px`);
        this.renderer.setStyle(square, 'height', `${squareSize}px`);
      });
    }
  }

  private setPieceSizes(): void {
    if (this.chessboardContainer) {
      const containerWidth = this.chessboardContainer.nativeElement.clientWidth;
      const squareSize = containerWidth / 8;

      const pieces = this.chessboardContainer.nativeElement.querySelectorAll('.chess-piece');
      pieces.forEach((piece: HTMLElement) => {
        this.renderer.setStyle(piece, 'width', `${squareSize}px`);
        this.renderer.setStyle(piece, 'height', `${squareSize}px`);
      });
    }
  }
  private initializeBoard(): void {
    // Array representing the default starting position of pieces on a chessboard
    const defaultBoard: string[][] = [
      ['BR', 'BN', 'BB', 'BQ', 'BK', 'BB', 'BN', 'BR'],
      ['BP', 'BP', 'BP', 'BP', 'BP', 'BP', 'BP', 'BP'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['--', '--', '--', '--', '--', '--', '--', '--'],
      ['WP', 'WP', 'WP', 'WP', 'WP', 'WP', 'WP', 'WP'],
      ['WR', 'WN', 'WB', 'WQ', 'WK', 'WB', 'WN', 'WR'],
    ];

    // Copy the default board configuration to the boardState
    this.boardState = defaultBoard.map(row => [...row]);
  }

  keyValuePairs = new Map([
    ['BR', '../../../assets/pieces/rook-b.svg'],
    ['BN', '../../../assets/pieces/knight-b.svg'],
    ['BB', '../../../assets/pieces/bishop-b.svg'],
    ['BQ', '../../../assets/pieces/queen-b.svg'],
    ['BK', '../../../assets/pieces/king-b.svg'],
    ['BP', '../../../assets/pieces/pawn-b.svg'],

    ['WR', '../../../assets/pieces/rook-w.svg'],
    ['WN', '../../../assets/pieces/knight-w.svg'],
    ['WB', '../../../assets/pieces/bishop-w.svg'],
    ['WQ', '../../../assets/pieces/queen-w.svg'],
    ['WK', '../../../assets/pieces/king-w.svg'],
    ['WP', '../../../assets/pieces/pawn-w.svg'],
  ]);

  isDarkSquare(row: number, col: number): boolean {
    return (row + col) % 2 !== 0;
  }

  onDragStarted(event: Event, row: number, col:number, pieceName: string) {
    // event.preventDefault();
    // this.isDragging = true;
    const piece = this.chessboardContainer.nativeElement.querySelector(`.piece${row}${col}`);
    this.ngZone.runOutsideAngular(() => {
      // Execute the function after a minimal delay
      setTimeout(() => {
        // Code to add the 'hide' class
        this.ngZone.run(() => {
          this.renderer.addClass(piece, "hidden");
        });
      }, 0);
    });
    if (piece) {
      this.renderer.setStyle(piece, 'z-index', 2);
    } else {
      console.error('Piece element is null or undefined');
    }
  }

  onDragEnded(event: Event, row: number, col:number, pieceName: string) {
    event.preventDefault();
    this.isDragging = false;
    const piece = this.chessboardContainer.nativeElement.querySelector(`.piece${row}${col}`);
    this.renderer.removeClass(piece, "hidden");
    if (piece) {
      this.renderer.setStyle(piece, 'z-index', 1);
    } else {
      console.error('Piece element is null or undefined');
    }

    // this.boardState[row][col] = "WK";

  }

  

}
