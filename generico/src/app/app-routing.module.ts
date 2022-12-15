import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoriaListaComponent } from './categoria/categoria-lista/categoria-lista.component';
import { CategoriaNovoComponent } from './categoria/categoria-novo/categoria-novo.component';

const routes: Routes = [
    { path: 'categoria', component: CategoriaListaComponent },
    { path: 'categoria-novo', component: CategoriaNovoComponent },
    { path: 'categoria-editar/:id', component: CategoriaNovoComponent }

];

@NgModule({

    imports: [
        RouterModule.forRoot(routes)
    ],

    exports: [RouterModule],
})

export class AppRoutingModule { }
