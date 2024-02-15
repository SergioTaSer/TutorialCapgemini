import { Injectable } from '@angular/core';
import { Prestamo } from './model/Prestamo';
import { PrestamoPage } from './model/PrestamoPage';

import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { HttpClient } from '@angular/common/http';
import { PrestamoFilters } from './model/PrestamoFilters';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  constructor(
    private http: HttpClient
  ) { }

  getPrestamos(pageable: Pageable, filterParams?: PrestamoFilters): Observable<PrestamoPage> {
    
    return this.http.post<PrestamoPage>('http://localhost:8080/prestamo', 
    {pageable:pageable,
    filterParams:filterParams});
  }

  savePrestamos(prestamo: Prestamo): Observable<void> {
    let url = 'http://localhost:8080/prestamo';
    if (prestamo.id != null) url += '/'+prestamo.id;

    return this.http.put<void>(url, prestamo);
  }

  deletePrestamos(idPrestamo: number): Observable<void> {
    return this.http.delete<void>('http://localhost:8080/prestamo/'+idPrestamo);
  }


}
