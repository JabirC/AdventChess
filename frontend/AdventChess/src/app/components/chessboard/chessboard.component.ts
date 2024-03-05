import { Component, ElementRef, Renderer2, ViewChild, AfterViewInit, NgZone, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CdkDrag, DragDropModule} from '@angular/cdk/drag-drop';
import { HighlightOnDragDirective } from '../../shared/directives/highlight-on-drag.directive'
import { pieceDragDirective} from '../../shared/directives/piece.directive'

@Component({
  selector: 'app-chessboard',
  standalone: true,
  imports: [CommonModule, CdkDrag, DragDropModule, HighlightOnDragDirective, pieceDragDirective],
  templateUrl: './chessboard.component.html',
  styleUrl: './chessboard.component.scss'
})
export class ChessboardComponent implements AfterViewInit {
  boundary = { top: 0, bottom: 100, left: 0, right: 100, scroll: 0, windowSize:0};
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
    this.setBoundary();
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    // Handle window resize event
    this.setSquareSizes();
    this.setPieceSizes();
    this.setBoundary();
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    // Get the vertical scroll position
    const scrollPosition = window.scrollY;
    this.boundary.scroll = scrollPosition;
  }

  private setSquareSizes(): void {
    if (this.chessboardContainer) {
      const containerWidth = this.chessboardContainer.nativeElement.clientWidth;
      const squareSize = containerWidth / 8;

      const squares = this.chessboardContainer.nativeElement.querySelectorAll('.square');
      squares.forEach((square: HTMLElement) => {
        const rowString = square.getAttribute("row");
        const row = rowString ? parseInt(rowString, 10) : NaN;
        const colString = square.getAttribute("col");
        const col = colString ? parseInt(colString, 10) : NaN;

        this.renderer.setStyle(square, 'width', `${squareSize}px`);
        this.renderer.setStyle(square, 'height', `${squareSize}px`);
        this.renderer.setStyle(square, 'top', `${ row * squareSize}px`);
        this.renderer.setStyle(square, 'left', `${ col * squareSize}px`);
      });
    }
  }

  private setPieceSizes(): void {
    if (this.chessboardContainer) {
      const containerWidth = this.chessboardContainer.nativeElement.clientWidth;
      const squareSize = containerWidth / 8;

      const pieces = this.chessboardContainer.nativeElement.querySelectorAll('.chess-piece');
      pieces.forEach((piece: HTMLElement) => {
        const rowString = piece.getAttribute("row");
        const row = rowString ? parseInt(rowString, 10) : NaN;
        const colString = piece.getAttribute("col");
        const col = colString ? parseInt(colString, 10) : NaN;

        this.renderer.setStyle(piece, 'width', `${squareSize}px`);
        this.renderer.setStyle(piece, 'height', `${squareSize}px`);
        this.renderer.setStyle(piece, 'top', `${ row * squareSize}px`);
        this.renderer.setStyle(piece, 'left', `${ col * squareSize}px`);
      });
    }
  }


  private setBoundary(): void {
    if (this.chessboardContainer) {
      const containerWidth = this.chessboardContainer.nativeElement.clientWidth;
      this.boundary.bottom = containerWidth;
      this.boundary.right= containerWidth;
      this.boundary.windowSize = window.innerWidth;
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


}
