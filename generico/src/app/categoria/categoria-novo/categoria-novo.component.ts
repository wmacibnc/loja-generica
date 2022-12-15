import { Component, OnInit } from '@angular/core';

import { Categoria } from '../categoria';
import { CategoriaService } from '../categoria.service';

import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-categoria-novo',
  templateUrl: './categoria-novo.component.html',
  styleUrls: ['./categoria-novo.component.css']
})
export class CategoriaNovoComponent implements OnInit {

  acao: string;

  categoria: Categoria = {
    id: null,
    nome: null,
    descricao: null
  };

  submitted = false;

  constructor(private categoriaService: CategoriaService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
  }

  salvar(): void {
    const data = {
      id: this.categoria.id,
      nome: this.categoria.nome,
      descricao: this.categoria.descricao
    };

    this.categoriaService.save(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  novaCategoria(): void {
    this.submitted = false;
    this.categoria = {
      id: null,
      nome: '',
      descricao: ''
    };
  }

  ngOnInit() {
    const edicao = this.activatedRoute.snapshot.paramMap.has('id');
    if (edicao) {
      this.acao = "Editar";
      const id = + this.activatedRoute.snapshot.paramMap.get('id');
      this.categoriaService.obter(id).subscribe(
        data => this.categoria = data
      )
    } else {
      this.acao = "Nova";
    }
  }

}
