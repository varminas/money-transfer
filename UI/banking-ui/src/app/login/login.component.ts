import { Component, OnInit } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { Router } from '@angular/router';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    form: FormGroup;
    loginError = undefined;

    constructor(private authService: AuthService, private router: Router) {
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            'username': new FormControl('', [Validators.required, Validators.minLength(5)]),
            'password': new FormControl('', [Validators.required, Validators.minLength(6)])
        });
    }

    login() {
        if (!this.isFormValid()) {
            return;
        }

        const username = this.form.getRawValue()['username'];
        const password = this.form.getRawValue()['password'];

        this.authService.authenticate({ username: username, password: password }, () => {
            this.router.navigateByUrl('/home');
        });
    }

    get usernameControl(): AbstractControl {
        return this.form.get('username');
    }

    get passwordControl(): AbstractControl {
        return this.form.get('password');
    }

    private isFormValid(): boolean {
        return this.form.valid;
    }
}
