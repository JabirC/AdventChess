import { Component, ElementRef, Renderer2, ViewChild, AfterViewInit, HostListener, ChangeDetectorRef} from '@angular/core';
import { CommonModule } from '@angular/common';
import { pieceDragDirective} from '../../shared/directives/piece.directive'

@Component({
  selector: 'app-chessboard',
  standalone: true,
  imports: [CommonModule, pieceDragDirective],
  templateUrl: './chessboard.component.html',
  styleUrl: './chessboard.component.scss'
})
export class ChessboardComponent implements AfterViewInit {
  boundary = { top: 0, bottom: 100, left: 0, right: 100, scroll: 0, windowSize:0};
  boardState: string[][] = [];
  isWhite!: boolean;
  orientationWhite!: boolean;
  @ViewChild('chessboardContainer') chessboardContainer!: ElementRef;
  containerWidth!: number;
  
  constructor(private renderer: Renderer2, private cdr: ChangeDetectorRef) {
    // Initialize the boardState with a default chessboard configuration
    this.isWhite = false;
    this.isWhite? this.orientationWhite = true: this.orientationWhite = false;
    this.initializeBoard();
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

    ['--', '../../../assets/pieces/pawn-w.svg'],

  ]);

  isDarkSquare(row: number, col: number): boolean {
    return (row + col) % 2 !== 0;
  }

  ngAfterViewInit(): void {
    // this.containerWidth = this.chessboardContainer.nativeElement.clientWidth;
    this.setBoundary();
    this.cdr.detectChanges();
  }
  
  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    // Handle window resize event
    // this.containerWidth = this.chessboardContainer.nativeElement.clientWidth;
    this.setBoundary();
  }

  @HostListener('window:scroll', ['$event'])
  onScroll(event: Event): void {
    // Get the vertical scroll position
    const scrollPosition = window.scrollY;
    this.boundary.scroll = scrollPosition;
  }

  private setBoundary(): void {
    if (this.chessboardContainer) {
      this.containerWidth = this.chessboardContainer.nativeElement.clientWidth;
      this.boundary.bottom = this.containerWidth;
      this.boundary.right= this.containerWidth;
      this.boundary.windowSize = window.innerWidth;
    }
  }

  handleBoardChange(newCoordinates: number[], oldRow: number, oldCol: number) {
    const containerWidth = this.chessboardContainer.nativeElement.clientWidth;
    const squareSize = containerWidth / 8;

    const pieceName: string = this.boardState[this.getMapping(oldRow)][this.getMapping(oldCol)];
    this.boardState[this.getMapping(oldRow)][this.getMapping(oldCol)] = "--";
    this.boardState[this.getMapping(newCoordinates[0])][this.getMapping(newCoordinates[1])] = pieceName;
    console.log(this.boardState);
  }

  // Used to inverse the chessboard based on player color
  getMapping(index: number): number{
    const mappedIndex = this.orientationWhite? index: 7-index;
    return mappedIndex;
  }

  rotateBoard(){
    this.orientationWhite = !this.orientationWhite;
  }

}
