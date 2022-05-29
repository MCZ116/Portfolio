/// <reference types="cypress"/>

describe("Para Bank Tests", () => {

    let username = "Cypress";
    let password = "12345zaqwe";
    let submit = '[type = "submit"]';

    let clickSubmit = () =>{
        cy.get(submit).click();
    }

    beforeEach(() =>{
        cy.visit("https://parabank.parasoft.com/parabank/index.htm");
    });

    it("Login Wrong User", () => {
        cy.url().should('include','/parabank/index.htm');
        cy.get('[name = "username"]').type(username).should('have.value',username);
        cy.get('[name = "password"]').type(password).should('have.value',password);
        clickSubmit();
        cy.get('.error').contains('The username and password could not be verified.');
    });

    it("Login Error When Empty", () => {
        clickSubmit();
        cy.get('.error').contains('Please enter a username and password.');
    });

    let contactName = 'Test Project';

    it('Contact Form Test',() =>{
        cy.get('.contact').click();
        cy.url().should('include','/parabank/contact.htm');
        cy.get('h1.title').contains('Customer Care');
        cy.get('#name').type(contactName).should('have.value','Test Project');
        cy.get('#email').type('fake@email.com').should('have.value','fake@email.com');
        cy.get('#phone').type('87654321').should('have.value','87654321');
        cy.get('#message').type('Test message').should('have.value','Test message')
        cy.get('[value="Send to Customer Care"]').should('have.value','Send to Customer Care').click();
        cy.get('#rightPanel').contains('A Customer Care Representative will be contacting you.');
        cy.get('#rightPanel').contains(`Thank you ${contactName}`);

    });

    it('Check Products Button Link',() =>{
        cy.get('.leftmenu').contains('Products').should('have.attr','href','http://www.parasoft.com/jsp/products.jsp');

    });

});