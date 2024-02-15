import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Cliente } from '../model/Cliente';
import { ClienteService } from '../cliente.service';
import { MatDialog } from '@angular/material/dialog';
import { ClienteEditComponent } from '../cliente-edit/cliente-edit.component';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.scss']
})
export class ClienteListComponent implements OnInit {

  // Creación de una instancia de MatTableDataSource que contendrá objetos de tipo Cliente
  dataSource = new MatTableDataSource<Cliente>();
  // Columnas de la tabla (identificadores del HTML)
  displayedColumns: string[] = ['id', 'name', 'action'];

  constructor(
    private clienteService: ClienteService,
    private dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.clienteService.getClientes().subscribe(
      clientes => this.dataSource.data = clientes
    );
  }

  createCliente(){
    const dialogRef = this.dialog.open(ClienteEditComponent, {
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  editCliente(cliente: Cliente) {
    const dialogRef = this.dialog.open(ClienteEditComponent, {
      data: { cliente: cliente }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit();
    });
  }

  deleteCliente(cliente: Cliente) {    

    const dialogRef = this.dialog.open(DialogConfirmationComponent, {
      data: { title: "Eliminar Usuario", description: "Atención si borra el usuario se perderán sus datos.<br> ¿Desea eliminarlo?" }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.clienteService.deleteCliente(cliente.id).subscribe(result => {
          this.ngOnInit();
        }); 
      }
    });
  }  

}
