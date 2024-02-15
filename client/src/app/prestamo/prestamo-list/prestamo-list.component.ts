import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { PrestamoEditComponent } from '../prestamo-edit/prestamo-edit.component';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';
import { Cliente } from 'src/app/cliente/model/Cliente';
import { Game } from 'src/app/game/model/Game';
import { ClienteService } from 'src/app/cliente/cliente.service';
import { GameService } from 'src/app/game/game.service';
import { PrestamoFilters } from '../model/PrestamoFilters';
import { MaxLengthValidator } from '@angular/forms';


@Component({
selector: 'app-prestamo-list',
templateUrl: './prestamo-list.component.html',
styleUrls: ['./prestamo-list.component.scss']
})

export class PrestamoListComponent implements OnInit {

    pageNumber: number = 0;
    pageSize: number = 5;
    totalElements: number = 0;

    games: Game[];
    clientes: Cliente[];
    filtros: PrestamoFilters = {};
    filterGame: Game;
    filterCliente: Cliente;
    filterFecha: Date;

    dataSource = new MatTableDataSource<Prestamo>();
    displayedColumns: string[] = ['id', 'game', 'cliente', 'fechaPrestamo', 'fechaDevolucion', 'action'];

    constructor(
        private gameService: GameService,
        private clienteService: ClienteService,
        private prestamoService: PrestamoService,
        public dialog: MatDialog,
    ) { }

    ngOnInit(): void {

        

        this.gameService.getGames().subscribe(
            games => this.games = games
        );
        
        this.clienteService.getClientes().subscribe(
            clientes => this.clientes = clientes
        );

        this.loadPage();
    
    }

    onCleanFilter(): void {

        this.filterCliente = null;
        this.filterFecha = null;
        this.filterGame = null;

        this.onSearch();
    }

    onSearch(): void {
    
        this.loadPage();

    }

    

    loadPage(event?: PageEvent) {

        let pageable : Pageable =  {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            sort: [{
                property: 'id',
                direction: 'ASC'
            }]
        }

        if (event != null) {
            pageable.pageSize = event.pageSize
            pageable.pageNumber = event.pageIndex;
        }

        this.filtros.game = this.filterGame?.id ?? null;
        this.filtros.cliente = this.filterCliente?.id ?? null;

        
        
        if(this.filterFecha == null){
            this.filtros.fecha = null;
        } else{
            const year = this.filterFecha?.getFullYear().toString();
            const month = (this.filterFecha?.getMonth() + 1).toString().padStart(2, '0'); 
            const day = this.filterFecha?.getDate().toString().padStart(2, '0');
            const formatDate = `${year}-${month}-${day}`;
            this.filtros.fecha = formatDate ?? null;
        }
        

        this.prestamoService.getPrestamos(pageable, this.filtros).subscribe(data => {
            this.dataSource.data = data.content;
            this.pageNumber = data.pageable.pageNumber;
            this.pageSize = data.pageable.pageSize;
            this.totalElements = data.totalElements;
        });


    }  

    createPrestamo() {      
        const dialogRef = this.dialog.open(PrestamoEditComponent, {
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });      
    }  

    deletePrestamo(prestamo: Prestamo) {    
        const dialogRef = this.dialog.open(DialogConfirmationComponent, {
            data: { title: "Eliminar prestamo", description: "Atención si borra el prestamo se perderán sus datos.<br> ¿Desea eliminarlo?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.prestamoService.deletePrestamos(prestamo.id).subscribe(result =>  {
                    this.ngOnInit();
                }); 
            }
        });
    }  
}