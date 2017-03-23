# Mail sender app
Test application for Egis Software

Java spring-boot for back-end
Angular-js for front-end

Application uses MailGun and SendGrid APIs for sending emails

# *Main task*: 
# Email Service

Create a service that accepts the necessary information and sends emails. It should provide an abstraction between two different email service providers. If one of the services goes down, your service can quickly failover to a different provider without affecting your customers.

Example Email Providers:

SendGrid - Simple Send Documentation
Mailgun - Simple Send Documentation
Mandrill - Simple Send Documentation
Amazon SES - Simple Send Documentation
All listed services are free to try and are pretty painless to sign up for, so please register your own test accounts on each.
