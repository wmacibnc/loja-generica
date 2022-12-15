import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Categoria } from '../categoria/categoria';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators';

@Injectable()
export class CategoriaService {

  private categoriaBaseUrl: string;

  constructor(private http: HttpClient) {
    this.categoriaBaseUrl = 'http://localhost:8080/categoria';
  }

  public listar(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.categoriaBaseUrl + "/listar");
  }

  public save(categoria: Categoria) {
    return this.http.post<Categoria>(this.categoriaBaseUrl + "/salvar", categoria);
  }

  public excluir(categoria: Categoria) {
    return this.http.delete(`${this.categoriaBaseUrl}/remover/${categoria.id}`, { responseType: 'text' });
  }

  public buscaPorNome(nome: any): Observable<Categoria[]> {
    const url = `${this.categoriaBaseUrl}/consultar/${nome}`;
    return this.http.get<Categoria[]>(url);
  }

  public obter(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.categoriaBaseUrl}/${id}`).pipe(
      map(response => response)
    )
  }

}