import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { GameListComponent } from './game/game-list/game-list.component';
import { ClienteListComponent } from './cliente/cliente-list/cliente-list.component';
import { PrestamoListComponent } from './prestamo/prestamo-list/prestamo-list.component';


const routes: Routes = [
    { path: '', redirectTo: '/games', pathMatch: 'full'},
    { path: 'categories', component: CategoryListComponent },
    { path: 'authors', component: AuthorListComponent },
    { path: 'games', component: GameListComponent },
    { path: 'clientes', component: ClienteListComponent},
    { path: 'prestamos', component: PrestamoListComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
