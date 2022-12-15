import { Component, OnInit } from '@angular/core';
import { Categoria } from '../categoria';
import { CategoriaService } from '../categoria.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-categoria-lista',
  templateUrl: './categoria-lista.component.html',
  styleUrls: ['./categoria-lista.component.css']
})
export class CategoriaListaComponent implements OnInit {

  categorias: Categoria[];
  categoriaSelecionada: Categoria = {};
  currentIndex = -1;
  nome = '';

  constructor(private categoriaService: CategoriaService, private router: Router) {
  }

  ngOnInit() {
    this.categoriaService.listar().subscribe(data => {
      this.categorias = data;
    });
  }

  buscaPorNome(): void {
    this.categoriaSelecionada = {};
    this.currentIndex = -1;

    this.categoriaService.buscaPorNome(this.nome)
      .subscribe({
        next: (data) => {
          this.categorias = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }


  setCategoriaAtiva(categoria: Categoria, index: number): void {
    this.categoriaSelecionada = categoria;
    this.currentIndex = index;
    alert('Categoria: ' + categoria);
  }

  excluirCategoria(categoria: Categoria): void {
    this.categoriaService.excluir(categoria).subscribe(
      data => {
        alert('Categoria: ' + categoria.nome + " - Excluida com sucesso!");
        this.ngOnInit();
      }
    )
  }

  editarCategoria(categoria: Categoria): void {
    this.router.navigateByUrl(`/categoria-editar/${categoria.id}`);
  }



}