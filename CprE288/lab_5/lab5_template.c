/**
 * lab5_template.c
 * 
 * Template file for CprE 288 lab 5
 *
 * @author Zhao Zhang, Chad Nelson, Zachary Glanz
 * @date 08/14/2016
 *
 * @author Phillip Jones, updated 6/4/2019
 */

#include "button.h"
#include "timer.h"
#include "lcd.h"

#include "cyBot_uart.h"  // Functions for communiticate between CyBot and Putty (via UART)
                         // PuTTy: Buad=115200, 8 data bits, No Flow Control, No Party,  COM1

#include "cyBot_Scan.h"  // For scan sensors 
#include "button.h"


#define REPLACEME 0


// Defined in button.c : Used to communicate information between the
// the interupt handeler and main.
extern volatile int button_event;
extern volatile int button_num;


int main(void) {
	button_init();
	init_button_interrupts();
	lcd_init();
	cyBot_uart_init();

	            // Don't forget to initialze the cyBot UART before trying to use it
	

    // (Uncomment ME for PuTTy to CyBot UART init part of lab) cyBot_uart_init_clean();  // Clean UART initialization, before running your UART GPIO init code

	// Complete this code for configuring the  (GPIO) part of UART initialization
      SYSCTL_RCGCGPIO_R |= 0x02;
      timer_waitMillis(1);            // Small delay before accessing device after turning on clock
      GPIO_PORTB_AFSEL_R |= 0x03;
      GPIO_PORTB_PCTL_R &= ~0x00000011;     // Force 0's in the disired locations
      GPIO_PORTB_PCTL_R |= 0x00000011;     // Force 1's in the disired locations
      GPIO_PORTB_DEN_R |= 0x03;
      GPIO_PORTB_DIR_R &= ~0x02;      // Force 0's in the disired locations
      GPIO_PORTB_DIR_R |= 0x02;      // Force 1's in the disired locataions
    
     cyBot_uart_init_last_half();  // Complete the UART device initialization part of configuration
	 int found_four = 0;
	 int found_three = 0;
	 int found_two = 0;
	 int found_one = 0;
	 char str[1000];
	 int i;
     //cyBOT_init_Scan();



	// YOUR CODE HERE
//	 while(1){
//	     //gpioe_handler();
//	     lcd_printf("%d", button_num);
//	 }



	 //Equation: y = -64.31x + 3351;
	 // x = (y-3351)/(-64.31);

//Part 2
	while(1)
	{
      gpioe_handler();

      if(button_num == 4 && found_four == 0){
          lcd_printf("Button: %d", button_num);
          sprintf(str, "Button: %d\n\r", button_num);
          for(i = 0; i<strlen(str);i++){
              cyBot_sendByte(str[i]);
          }
          found_four++;
          found_three = 0;
          found_two = 0;
          found_one = 0;
      }
      else if(button_num == 3 && found_three == 0){
                lcd_printf("Button: %d", button_num);
                sprintf(str, "Button: %d\n\r", button_num);
                         for(i = 0; i<strlen(str);i++){
                             cyBot_sendByte(str[i]);
                         }
                found_three++;
                found_four = 0;
                found_two = 0;
                found_one = 0;
            }
      else if(button_num == 2 && found_two == 0){
                lcd_printf("Button: %d", button_num);
                sprintf(str, "Button: %d\n\r", button_num);
                         for(i = 0; i<strlen(str);i++){
                             cyBot_sendByte(str[i]);
                         }
                found_two++;
                found_three = 0;
                found_four = 0;
                found_one = 0;
            }
      else if(button_num == 1 && found_one == 0){
                lcd_printf("Button: %d", button_num);
                sprintf(str, "Button: %d\n\r", button_num);
                         for(i = 0; i<strlen(str);i++){
                             cyBot_sendByte(str[i]);
                         }
                found_one++;
                found_three = 0;
                found_two = 0;
                found_four = 0;
            }
      }

//Part 3

	}
	

