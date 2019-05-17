import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header.component';
import { NgbButtonsModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';

@NgModule({
    declarations: [HeaderComponent],
    exports: [
        HeaderComponent
    ],
    imports: [
        CommonModule,
        NgbButtonsModule,
        RouterModule
    ]
})
export class HeaderModule {
}
