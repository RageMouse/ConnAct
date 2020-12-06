// https://docs.cypress.io/api/introduction/api.html

describe('Event Tests', () => {
  it('Open Event Page', () => {
    cy.visit('http://localhost:8081/#/myEvents')
    cy.contains('h1', 'My Events Page')
  })

  it('Open MyEvent Page', () => {
    cy.visit('http://localhost:8081/#/myEvents')
    cy.contains('.v-card__title', 'test2')
  })

  it('Open MyEvent Details Card', () => {
    cy.visit('http://localhost:8081/#/myEvents')
    cy.get('#detailsButton').click();
    cy.contains('.v-card__title', 'test2')
  })

  it('Open Events Page', () => {
    cy.visit('http://localhost:8081/#/event')
    cy.contains('h1', 'Events Page')
  })

  it('Open Event Details Card', () => {
    cy.visit('http://localhost:8081/#/event')
    cy.get('#detailsButton').click();
    cy.contains('.v-card__title', 'test2')
  })
})
