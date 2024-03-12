import { Routes } from '@angular/router';
import { AppComponent } from './components/app.component'
import { GameComponent } from './components/game/game.component'
import { HomeComponent } from './components/home/home.component';
import { ModeComponent } from './components/mode/mode.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'play', component: GameComponent },
    { path: 'mode', component: ModeComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: '**', redirectTo: '/home' }, // Handle 404 - Page Not Found
];
