import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ClienteService } from '../cliente.service';
import { Cliente } from '../model/Cliente';

@Component({
  selector: 'app-cliente-edit',
  templateUrl: './cliente-edit.component.html',
  styleUrls: ['./cliente-edit.component.scss']
})

export class ClienteEditComponent implements OnInit {

  cliente : Cliente;

  constructor(
    public dialogRef: MatDialogRef<ClienteEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private clienteService: ClienteService
  ) { }

  ngOnInit(): void {
    if (this.data.cliente != null) {
      this.cliente = Object.assign({}, this.data.cliente);
    }
    else {
      this.cliente = new Cliente();
    }
  }

  onSave() {
    this.clienteService.saveCliente(this.cliente).subscribe(result => {
      this.dialogRef.close();
    });    
  }  

  onClose() {
    this.dialogRef.close();
  }

}
