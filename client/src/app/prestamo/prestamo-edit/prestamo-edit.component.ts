import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';
import { Game } from 'src/app/game/model/Game';
import { Cliente } from 'src/app/cliente/model/Cliente';
import { GameService } from 'src/app/game/game.service';
import { ClienteService } from 'src/app/cliente/cliente.service';

@Component({
selector: 'app-prestamo-edit',
templateUrl: './prestamo-edit.component.html',
styleUrls: ['./prestamo-edit.component.scss']
})
export class PrestamoEditComponent implements OnInit {

    games: Game[];
    clientes: Cliente[];
    prestamo : Prestamo;


    constructor(
        public dialogRef: MatDialogRef<PrestamoEditComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private prestamoService: PrestamoService,
        private gameService: GameService,
        private clienteService: ClienteService
    ) { }

    ngOnInit(): void {

        if (this.data.prestamo != null) {
            this.prestamo = Object.assign({}, this.data.prestamo);
        }
        else {
            this.prestamo = new Prestamo();
        }

        this.gameService.getGames().subscribe(
            games => {
                this.games = games;
            }
        );

        this.clienteService.getClientes().subscribe(
            clientes => {
                this.clientes = clientes;
            }
        );
    }

    onSave() {
        this.prestamoService.savePrestamos(this.prestamo).subscribe(result =>  {
            this.dialogRef.close();
        }); 
    }  

    onClose() {
        this.dialogRef.close();
    }

}